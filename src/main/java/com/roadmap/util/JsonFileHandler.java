package com.roadmap.util;

import com.roadmap.entity.Task;
import com.roadmap.enums.TaskStatus;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JsonFileHandler {

    private static String fileName = "tasks.json";

    public static List<Task> readJsonFile() throws IOException {
        String jsonString = Files.readString(Path.of(fileName));
        String[] jsons = jsonString
                .replaceAll("\\[\n", "")
                .replaceAll("\n\\]", "")
                .split("\n");
        List<Task> tasks = new ArrayList<>();
        for (String json : jsons) {
            String[] attributes = json.trim().replaceAll("\\{", "").replaceAll("\\}", "").split(",");
            List<String> attributeValues = Arrays.stream(attributes)
                    .map(attribute -> attribute.split(":")[1].replaceAll("\"", ""))
                    .collect(Collectors.toList());
            Task task = new Task();
            task.setId(Integer.parseInt(attributeValues.get(0)));
            task.setDescription(attributeValues.get(1));
            task.setStatus(TaskStatus.valueOf(attributeValues.get(2)));
            task.setCreatedAt(LocalDate.parse(attributeValues.get(3)));
            task.setUpdatedAt(LocalDate.parse(attributeValues.get(4)));
            tasks.add(task);
        }
        return tasks;
    }

    public static void writeJsonFile(List<Task> tasks) throws IOException {
        String jsonString = tasks.stream().map(Task::toString).collect(Collectors.joining(",\n"));
        StringBuilder builder = new StringBuilder();
        builder.append("[\n");
        builder.append(jsonString);
        builder.append("\n]");
        Files.write(Path.of(fileName), builder.toString().getBytes());
    }
}