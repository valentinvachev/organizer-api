package com.organizer.www.services.impl;

import com.organizer.www.exceptions.EntityNotFoundException;
import com.organizer.www.models.Task;
import com.organizer.www.models.bindings.TaskBindingDTO;
import com.organizer.www.models.views.TaskViewDTO;
import com.organizer.www.repositories.TaskRepository;
import com.organizer.www.services.TaskService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, ModelMapper modelMapper) {
        this.taskRepository = taskRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TaskViewDTO> getAllActiveTasks() {
        return this.taskRepository.findAllByOrderByCreatedAt().stream()
                .map(task -> this.modelMapper.map(task,TaskViewDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TaskViewDTO saveTask(TaskBindingDTO taskBindingDTO) {
        Task taskToBeSaved = this.modelMapper.map(taskBindingDTO, Task.class);
        taskToBeSaved.setCreatedAt(LocalDateTime.now());
        return this.modelMapper.map(this.taskRepository.save(taskToBeSaved), TaskViewDTO.class);
    }

    @Override
    public TaskViewDTO editTask(Long taskId, TaskBindingDTO taskBindingDTO) throws EntityNotFoundException {
        Task taskDb = this.findTaskById(taskId);
        taskDb.setName(taskBindingDTO.getName());
        return this.modelMapper.map(this.taskRepository.save(taskDb), TaskViewDTO.class);
    }

    @Override
    public Long deleteTask(Long taskId) throws EntityNotFoundException {
        Task taskDb = this.findTaskById(taskId);
        this.taskRepository.delete(taskDb);
        return taskDb.getId();
    }

    private Task findTaskById(Long taskId) throws EntityNotFoundException {
        Task taskDb = this.taskRepository.findTaskById(taskId);

        if (taskDb == null) {
            throw new EntityNotFoundException("Task with this id does not exist");
        }

        return taskDb;
    }
}
