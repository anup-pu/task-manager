package com.taskmanager.taskmanager;

import com.taskmanager.taskmanager.dto.TaskDTO;
import com.taskmanager.taskmanager.model.Priority;
import com.taskmanager.taskmanager.model.Status;
import com.taskmanager.taskmanager.model.Task;
import com.taskmanager.taskmanager.repository.TaskRepository;
import com.taskmanager.taskmanager.service.TaskServiceImpl;
import com.taskmanager.taskmanager.exception.TaskNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // ✅ Test for createTask()
    @Test
    public void testCreateTask() {
        TaskDTO dto = new TaskDTO();
        dto.setTitle("Learn Spring Boot");
        dto.setDescription("Finish controller and service layer");
        dto.setPriority("HIGH");
        dto.setDeadline(LocalDate.now().plusDays(2));

        Task savedTask = new Task(1L, "Learn Spring Boot", "Finish controller and service layer",
                Priority.HIGH, Status.TODO, dto.getDeadline());

        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        Task result = taskService.createTask(dto);

        assertEquals("Learn Spring Boot", result.getTitle());
        assertEquals(Priority.HIGH, result.getPriority());
        assertEquals(Status.TODO, result.getStatus());

        verify(taskRepository, times(1)).save(any(Task.class));
    }

    // ✅ Test for getTaskById()
    @Test
    public void testGetTaskById() {
        Task task = new Task(1L, "Review JUnit", "Study test annotations",
                Priority.MEDIUM, Status.TODO, LocalDate.now());

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Task result = taskService.getTaskById(1L);

        assertNotNull(result);
        assertEquals("Review JUnit", result.getTitle());
    }

    // ❌ Test for getTaskById() when not found
    @Test
    public void testGetTaskById_NotFound() {
        when(taskRepository.findById(100L)).thenReturn(Optional.empty());

        assertThrows(TaskNotFoundException.class, () -> {
            taskService.getTaskById(100L);
        });
    }
}
