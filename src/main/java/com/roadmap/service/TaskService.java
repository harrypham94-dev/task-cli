package com.roadmap.service;

import com.roadmap.entity.Task;
import com.roadmap.enums.TaskStatus;

import java.io.IOException;
import java.util.List;

public interface TaskService {
    void addTask(Task task);
    void updateTaskDescription(int id, String description);
    void deleteTask(int id);
    void updateTaskStatus(int id, TaskStatus status);
    List<Task> getTasks();
    List<Task> getTasksByStatus(final TaskStatus status);
    void persistTasks() throws IOException;
}
