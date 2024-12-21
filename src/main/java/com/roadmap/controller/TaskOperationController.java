package com.roadmap.controller;

import com.roadmap.entity.Task;
import com.roadmap.enums.CliCommand;
import com.roadmap.enums.TaskStatus;
import com.roadmap.presenter.Presenter;
import com.roadmap.service.TaskService;

import java.io.IOException;
import java.util.List;

public class TaskOperationController {

    private TaskService service;
    private Presenter<Task> presenter;

    public TaskOperationController(TaskService service, Presenter<Task> presenter) {
        this.service = service;
        this.presenter = presenter;
    }

    public void execute(final CliCommand command, final String[] arguments) throws IOException {
        switch (command) {
            case list:
                listTasks(arguments);
                break;
            case add:
                addTask(arguments);
                break;
            case update:
                updateTask(arguments);
                break;
            case delete:
                deleteTask(arguments);
                break;
            case mark:
                markTask(arguments);
                break;
            default:
                break;
        }
        service.persistTasks();
    }

    public void listTasks(final String[] arguments) {
        if (arguments.length == 0) {
            getTasks();
        } else {
            TaskStatus status = TaskStatus.valueOf(arguments[0]);
            getTasksByStatus(status);
        }
    }

    public void getTasks() {
        List<Task> tasks = service.getTasks();
        presenter.showList(tasks);
    }

    public void getTasksByStatus(final TaskStatus status) {
        List<Task> tasks = service.getTasksByStatus(status);
        presenter.showList(tasks);
    }

    public void addTask(final String[] arguments) {
        String description = arguments[0];
        Task newtask = new Task();
        newtask.setDescription(description);
        service.addTask(newtask);
    }

    public void updateTask(final String[] arguments) {
        int id = Integer.parseInt(arguments[0]);
        String description = arguments[1];
        service.updateTaskDescription(id,description);
    }

    public void deleteTask(final String[] arguments) {
        int id = Integer.parseInt(arguments[0]);
        service.deleteTask(id);
    }

    public void markTask(final String[] arguments) {
        int id = Integer.parseInt(arguments[0]);
        TaskStatus status = TaskStatus.valueOf(arguments[1]);
        service.updateTaskStatus(id, status);
    }
}