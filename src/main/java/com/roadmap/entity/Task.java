package com.roadmap.entity;

import com.roadmap.enums.TaskStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task {
    private int id;
    private String description;
    private TaskStatus status;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        String jsonFormatBuilder = "{" +
                "\"id\":%s" +
                ",\"description\":\"%s\"" +
                ",\"status\":\"%s\"" +
                ",\"createdAt\":\"%s\"" +
                ",\"updatedAt\":\"%s\"" +
                "}";
        return String.format(jsonFormatBuilder, id, description, status, createdAt, updatedAt);
    }
}
