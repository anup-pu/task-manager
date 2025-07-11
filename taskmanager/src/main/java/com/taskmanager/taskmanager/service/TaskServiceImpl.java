package com.taskmanager.taskmanager.service;

import com.taskmanager.taskmanager.dto.TaskDTO;
import com.taskmanager.taskmanager.exception.TaskNotFoundException;
import com.taskmanager.taskmanager.model.Priority;
import com.taskmanager.taskmanager.model.Status;
import com.taskmanager.taskmanager.model.Task;
import com.taskmanager.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public Task createTask(TaskDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setPriority(Priority.valueOf(dto.getPriority().toUpperCase()));
        task.setStatus(Status.TODO); // default
        task.setDeadline(dto.getDeadline());
        return taskRepository.save(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id " + id));
    }

    @Override
    public Task updateTask(Long id, TaskDTO dto) {
        Task task = getTaskById(id);
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setPriority(Priority.valueOf(dto.getPriority().toUpperCase()));
        task.setDeadline(dto.getDeadline());
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task not found with id " + id);
        }
        taskRepository.deleteById(id);
    }

    @Override
    public Task updateTaskStatus(Long id, String status) {
        Task task = getTaskById(id);
        task.setStatus(Status.valueOf(status.toUpperCase()));
        return taskRepository.save(task);
    }
}
