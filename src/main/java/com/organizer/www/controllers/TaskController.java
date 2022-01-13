package com.organizer.www.controllers;

import com.organizer.www.exceptions.EntityNotFoundException;
import com.organizer.www.models.bindings.TaskBindingDTO;
import com.organizer.www.models.views.TaskViewDTO;
import com.organizer.www.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("")
    public ResponseEntity<?> getTasks() {
        List<TaskViewDTO> taskViewDTOList = this.taskService.getAllActiveTasks();
        return ResponseEntity.ok().body(taskViewDTOList);
    }

    @PostMapping("")
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskBindingDTO taskBindingDTO,HttpServletRequest request) throws URISyntaxException {
        Map<String, TaskViewDTO> response = new HashMap<>();
        response.put("created", this.taskService.saveTask(taskBindingDTO));
        return ResponseEntity.created(new URI(request.getServletPath())).body(response);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> editTask(@PathVariable Long id, @Valid @RequestBody TaskBindingDTO taskBindingDTO) throws EntityNotFoundException {
        Map<String, TaskViewDTO> response = new HashMap<>();
        response.put("edited", this.taskService.editTask(id, taskBindingDTO));
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) throws EntityNotFoundException {
        Map<String, Long> response = new HashMap<>();
        response.put("deleted", this.taskService.deleteTask(id));
        return ResponseEntity.ok().body(response);
    }
}
