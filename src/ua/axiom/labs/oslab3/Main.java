package ua.axiom.labs.oslab3;

import ua.axiom.labs.oslab3.controller.TaskController;
import ua.axiom.labs.oslab3.model.config.Configuration;

public class Main {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        TaskController taskController = new TaskController(configuration);
        taskController.run();
    }
}
