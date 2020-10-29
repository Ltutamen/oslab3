package ua.axiom.labs.oslab3.controller;

import ua.axiom.labs.oslab3.model.config.Configuration;
import ua.axiom.labs.oslab3.model.TaskModel;

import java.util.Random;

public class TaskProvider {
    private final Random random = new Random();
    private short previousID = 0;

    public TaskProvider(Configuration configuration) {
    }

    public TaskModel getNewTask() {
        return new TaskModel(12 + random.nextInt(20), previousID++);
    }
}
