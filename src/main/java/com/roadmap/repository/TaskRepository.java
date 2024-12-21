package com.roadmap.repository;

import com.roadmap.entity.Task;
import com.roadmap.enums.TaskStatus;

import java.io.IOException;
import java.util.List;

public interface TaskRepository {
    void save(Task task);
    List<Task> findAll();
    List<Task> findByStatus(final TaskStatus status);
    void delete(int id);
    void update(Task task);
    void updateStatus(int id, TaskStatus status);
    void persist() throws IOException;
}
