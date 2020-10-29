package ua.axiom.labs.oslab3.model;

import java.util.*;

/**
 * Class that holds tasks, that wait for memory to be freed to start execution
 */
public class TaskQueue {
    private Queue<TaskModel> tasks = new ArrayDeque<>();

    public Optional<TaskModel> getQueuedTask() {
        return Optional.ofNullable(tasks.poll());
    }

    public void addTask(TaskModel task) {
        tasks.add(task);
    }

    public int size() {
        return tasks.size();
    }
}
