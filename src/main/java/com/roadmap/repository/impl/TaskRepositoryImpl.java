package com.roadmap.repository.impl;

import com.roadmap.entity.Task;
import com.roadmap.enums.TaskStatus;
import com.roadmap.repository.TaskRepository;
import com.roadmap.util.JsonFileHandler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TaskRepositoryImpl implements TaskRepository {

    private final Map<Integer, Task> taskByIdMap;

    public TaskRepositoryImpl() throws IOException {
        taskByIdMap = JsonFileHandler.readJsonFile().stream().collect(Collectors.toMap(Task::getId, task -> task));
    }

    @Override
    public void save(Task task) {
        if (task.getId() == null) {
            Optional<Integer> optId = taskByIdMap.keySet().stream().max(Integer::compareTo);
            if (optId.isPresent()) {
                task.setId(optId.get() + 1);
            } else {
                task.setId(0);
            }
            taskByIdMap.put(task.getId(), task);
        } else {
            Task foundTask = findById(task.getId());
            if (foundTask != null) {
                foundTask.setDescription(task.getDescription());
                foundTask.setStatus(task.getStatus());
                foundTask.setUpdatedAt(LocalDate.now());
                taskByIdMap.put(foundTask.getId(), foundTask);
            }
        }
    }

    @Override
    public List<Task> findAll() {
        return taskByIdMap.values().stream().toList();
    }

    @Override
    public List<Task> findByStatus(TaskStatus status) {
        return taskByIdMap.values().stream().filter(task -> task.getStatus().equals(status)).collect(Collectors.toList());
    }

    @Override
    public Task findById(int id) {
        return taskByIdMap.get(id);
    }

    @Override
    public void delete(int id) {
        taskByIdMap.remove(id);
    }

    @Override
    public void persist() throws IOException {
        JsonFileHandler.writeJsonFile(taskByIdMap.values().stream().toList());
    }
}
