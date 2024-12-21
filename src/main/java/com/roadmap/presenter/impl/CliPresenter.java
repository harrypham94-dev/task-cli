package com.roadmap.presenter.impl;

import com.roadmap.entity.Task;
import com.roadmap.presenter.Presenter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class CliPresenter implements Presenter<Task> {
    @Override
    public void showList(List<Task> items) {
        int maxIdColumn = items.stream().map(Task::getId).map(String::valueOf).map(String::length).max(Integer::compareTo).orElse(0);
        int maxDescriptionColumn = items.stream().map(Task::getDescription).map(String::length).max(Integer::compareTo).orElse(0);
        int maxStatusColumn = items.stream().map(Task::getStatus).map(Objects::toString).map(String::length).max(Integer::compareTo).orElse(0);
        int maxCreatedAtColumn = items.stream().map(Task::getCreatedAt).map(LocalDate::toString).map(String::length).max(Integer::compareTo).orElse(0);
        int maxUpdatedAtColumn = items.stream().map(Task::getUpdatedAt).map(LocalDate::toString).map(String::length).max(Integer::compareTo).orElse(0);
        int space = 5;
        StringBuilder sb = new StringBuilder();
        sb.append("%-").append(maxIdColumn + space).append("s")
                .append("%-").append(maxDescriptionColumn + space).append("s")
                .append("%-").append(maxStatusColumn + space).append("s")
                .append("%-").append(maxCreatedAtColumn + space).append("s")
                .append("%-").append(maxUpdatedAtColumn + space).append("s")
                .append("\n");
        System.out.format(sb.toString(), "ID", "DESCRIPTION", "STATUS", "CREATED AT", "UPDATED AT");
        items.forEach(item -> {
            System.out.format(sb.toString(), item.getId(), item.getDescription(), item.getStatus(), item.getCreatedAt(), item.getUpdatedAt());
        });
    }
}
