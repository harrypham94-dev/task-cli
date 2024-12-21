package com.roadmap.repository.impl;

import com.roadmap.entity.Task;
import com.roadmap.enums.TaskStatus;
import com.roadmap.repository.TaskRepository;
import com.roadmap.util.JsonFileHandler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskRepositoryImpl implements TaskRepository {

    private final List<Task> tasks;

    public TaskRepositoryImpl() throws IOException {
        tasks = JsonFileHandler.readJsonFile();
    }

    @Override
    public void save(Task task) {
        final Optional<Integer> optId = tasks.stream().map(Task::getId).sorted(Comparator.reverseOrder()).findFirst();
        if (optId.isPresent()) {
            task.setId(optId.get() + 1);
        } else {
            task.setId(0);
        }
        tasks.add(task);
    }

    @Override
    public List<Task> findAll() {
        return tasks;
    }

    @Override
    public List<Task> findByStatus(TaskStatus status) {
        return tasks.stream().filter(task -> task.getStatus().equals(status)).collect(Collectors.toList());
    }

    @Override
    public void delete(int id) {
        tasks.removeIf(task -> task.getId() == id);
    }

    @Override
    public void update(Task task) {
        tasks.stream()
                .filter(foundTask -> foundTask.getId() == task.getId())
                .findFirst()
                .ifPresent(foundTask -> {
                    foundTask.setDescription(task.getDescription());
                    foundTask.setUpdatedAt(LocalDate.now());
                });
    }

    @Override
    public void updateStatus(int id, TaskStatus status) {
        tasks.stream()
                .filter(foundTask -> foundTask.getId() == id)
                .findFirst()
                .ifPresent(foundTask -> {
                    foundTask.setStatus(status);
                    foundTask.setUpdatedAt(LocalDate.now());
                });
    }

    @Override
    public void persist() throws IOException {
        JsonFileHandler.writeJsonFile(tasks);
    }
}
