package com.roadmap;

import com.roadmap.controller.TaskOperationController;
import com.roadmap.presenter.Presenter;
import com.roadmap.presenter.impl.CliPresenter;
import com.roadmap.repository.TaskRepository;
import com.roadmap.repository.impl.TaskRepositoryImpl;
import com.roadmap.service.TaskService;
import com.roadmap.service.impl.TaskServiceImpl;
import com.roadmap.entity.Task;
import com.roadmap.enums.CliCommand;

import java.io.IOException;
import java.util.Arrays;

public class TaskCli {

    public static void main(String[] args) throws IOException {
        String command = args[0];
        String[] arguments = Arrays.copyOfRange(args, 1, args.length);
        CliCommand cmd = CliCommand.valueOf(command);

        final TaskRepository repository = new TaskRepositoryImpl();
        final TaskService service = new TaskServiceImpl(repository);
        final Presenter<Task> presenter = new CliPresenter();
        final TaskOperationController controller = new TaskOperationController(service, presenter);
        controller.execute(cmd, arguments);
    }
}
