package ua.axiom.labs.oslab3.model;

public class TaskModel {
    public final short taskId;
    public final int requiredMem;

    public TaskModel(int takenMemory, short taskId) {
        this.requiredMem = takenMemory;
        this.taskId = taskId;
    }

    @Override
    public String toString() {
        return "t:" + taskId+ "; m:" + requiredMem + ";";
    }
}
