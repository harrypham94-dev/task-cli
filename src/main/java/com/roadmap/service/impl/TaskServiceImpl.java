package com.roadmap.service.impl;

import com.roadmap.entity.Task;
import com.roadmap.enums.TaskStatus;
import com.roadmap.repository.TaskRepository;
import com.roadmap.service.TaskService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class TaskServiceImpl implements TaskService {

    final private TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void addTask(Task task) {
        task.setStatus(TaskStatus.TODO);
        task.setCreatedAt(LocalDate.now());
        task.setUpdatedAt(LocalDate.now());
        taskRepository.save(task);
    }

    @Override
    public void updateTask(Task task) {
        taskRepository.update(task);
    }

    @Override
    public void deleteTask(int id) {
        taskRepository.delete(id);
    }

    @Override
    public void updateTaskStatus(int id, TaskStatus status) {
        taskRepository.updateStatus(id, status);
    }

    @Override
    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Override
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    @Override
    public void persistTasks() throws IOException {
        taskRepository.persist();
    }
}
