package com.roadmap.service;

import com.roadmap.entity.Task;
import com.roadmap.enums.TaskStatus;
import com.roadmap.repository.TaskRepository;
import com.roadmap.service.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    private TaskService taskService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        taskService = new TaskServiceImpl(taskRepository);
    }

    @Test
    public void addTask() {
        // given
        Task task = new Task();
        task.setDescription("Task Description");

        // when
        taskService.addTask(task);

        // then
        assertEquals("Task Description", task.getDescription());
        assertEquals(TaskStatus.TODO, task.getStatus());
        assertEquals(LocalDate.now(), task.getCreatedAt());
        assertEquals(LocalDate.now(), task.getUpdatedAt());
        verify(taskRepository).save(task);
    }

    @Test
    public void updateTaskDescription_foundTask() {
        // given
        int id = 1;
        String description = "Task Description";
        Task task = new Task();
        task.setId(id);
        when(taskRepository.findById(eq(id))).thenReturn(task);

        // when
        taskService.updateTaskDescription(id, description);

        // then
        assertEquals("Task Description", task.getDescription());
        verify(taskRepository).save(task);
    }

    @Test
    public void updateTaskDescription_notFoundTask() {
        // given
        int id = 1;
        String description = "Task Description";

        // when

        // then
        assertThrows(RuntimeException.class, () -> taskService.updateTaskDescription(id, description));
    }

    @Test
    public void updateTaskStatus() {
        // given
        int id = 1;
        TaskStatus status = TaskStatus.COMPLETED;
        Task task = new Task();
        task.setId(id);
        when(taskRepository.findById(eq(id))).thenReturn(task);

        // when
        taskService.updateTaskStatus(id, status);

        // then
        assertEquals(status, task.getStatus());
        verify(taskRepository).save(task);
    }

    @Test
    public void deleteTask_foundTask() {
        // given
        int id = 1;
        Task task = new Task();
        task.setId(id);
        when(taskRepository.findById(eq(id))).thenReturn(task);

        // when
        taskService.deleteTask(id);

        // then
        verify(taskRepository).delete(id);
    }

    @Test
    public void deleteTask_notFoundTask() {
        // given
        int id = 1;

        // when

        // then
        assertThrows(RuntimeException.class, () -> taskService.deleteTask(id));
    }

    @Test
    public void getTasks() {
        // given

        // when
        taskService.getTasks();

        // then
        verify(taskRepository).findAll();
    }

    @Test
    public void getTasksByStatus() {
        // given
        TaskStatus status = TaskStatus.COMPLETED;

        // when
        taskService.getTasksByStatus(status);

        // then
        verify(taskRepository).findByStatus(status);
    }

    @Test
    public void persistTasks() throws IOException {
        // given

        // when
        taskService.persistTasks();

        // then
        verify(taskRepository).persist();
    }
}
