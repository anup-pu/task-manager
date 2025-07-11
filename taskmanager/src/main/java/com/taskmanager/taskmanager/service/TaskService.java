package com.taskmanager.taskmanager.service;

import com.taskmanager.taskmanager.dto.TaskDTO;
import com.taskmanager.taskmanager.model.Task;

import java.util.List;

public interface TaskService {
    Task createTask(TaskDTO taskDTO);
    List<Task> getAllTasks();
    Task getTaskById(Long id);
    Task updateTask(Long id, TaskDTO taskDTO);
    void deleteTask(Long id);
    Task updateTaskStatus(Long id, String status);
}
