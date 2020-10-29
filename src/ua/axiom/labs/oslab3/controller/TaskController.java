package ua.axiom.labs.oslab3.controller;

import ua.axiom.labs.oslab3.controller.memory.KernelMemoryController;
import ua.axiom.labs.oslab3.model.config.Configuration;
import ua.axiom.labs.oslab3.model.TaskModel;
import ua.axiom.labs.oslab3.model.TaskQueue;
import ua.axiom.labs.oslab3.viewer.View;

import javax.swing.text.html.Option;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

public class TaskController {
    private final KernelMemoryController memoryController;
    private final View view;

    private final Configuration configuration;
    private final TaskProvider taskProvider;
    private final TaskQueue taskQueue;

    public TaskController(Configuration configuration) {
        this.taskQueue = new TaskQueue();
        this.view = new View();
        this.memoryController = new KernelMemoryController(configuration, taskQueue, view);
        this.configuration = configuration;
        this.taskProvider = new TaskProvider(configuration);

    }

    public void run() {
        int tasksToRun = configuration.getTasksToRun();
        Random random = new Random();
        Set<TaskModel> runningTasks = new HashSet<>();

        for (int i = 0; i < tasksToRun ; ++i) {
            //  add from queue
            Optional<TaskModel> queuedTask = taskQueue.getQueuedTask();
            if(queuedTask.isPresent()) {
                memoryController.registerNewTask(queuedTask.get());
                view.showTaskUnQueuedAttempt(queuedTask.get(), taskQueue);
            }

            if(random.nextBoolean()) {
                TaskModel newTask = taskProvider.getNewTask();

                runningTasks.add(newTask);
                memoryController.registerNewTask(newTask);
            }

            //  removal
            if (random.nextBoolean() && i > 10) {
                try {
                    TaskModel toRemove = runningTasks.iterator().next();

                    runningTasks.remove(toRemove);
                    memoryController.finishTask(toRemove.taskId);
                    view.showTaskRemove(toRemove);
                } catch (Exception e) { }
            }

            view.showMemorySnapshot(memoryController);
        }



    }
}
