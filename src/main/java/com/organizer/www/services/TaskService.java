package com.organizer.www.services;

import com.organizer.www.exceptions.EntityNotFoundException;
import com.organizer.www.models.bindings.TaskBindingDTO;
import com.organizer.www.models.views.TaskViewDTO;
import java.util.List;

public interface TaskService {
    List<TaskViewDTO> getAllActiveTasks();
    TaskViewDTO saveTask(TaskBindingDTO taskBindingDTO);
    TaskViewDTO editTask(Long taskId, TaskBindingDTO taskBindingDTO) throws EntityNotFoundException;
    Long deleteTask(Long taskId) throws EntityNotFoundException;
}
