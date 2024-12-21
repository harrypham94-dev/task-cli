package com.roadmap.repository;

import com.roadmap.entity.Task;
import com.roadmap.enums.TaskStatus;

import java.io.IOException;
import java.util.List;

public interface TaskRepository {
    List<Task> findAll();
    List<Task> findByStatus(final TaskStatus status);
    Task findById(int id);
    void save(Task task);
    void delete(int id);
    void persist() throws IOException;
}
