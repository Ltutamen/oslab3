package ua.axiom.labs.oslab3.viewer;

import ua.axiom.labs.oslab3.controller.memory.KernelMemoryController;
import ua.axiom.labs.oslab3.model.TaskModel;
import ua.axiom.labs.oslab3.model.TaskQueue;
import ua.axiom.labs.oslab3.model.memtable.MemoryMappingTable;
import ua.axiom.labs.oslab3.model.memtable.MemoryTableRecord;

public class View {
    public void showMemorySnapshot(KernelMemoryController kernel) {
        MemoryMappingTable table = kernel.getMemoryController();

        System.out.println("/**\t\t memory snapshot\t\t**/");
        for (MemoryTableRecord record : table.getRecords()) {
            System.out.format(
                    "\t\tr::%6d;;\t%d;-> %d;\n",
                    record.getForTaskPID(),
                    record.getBeginMemoryPointer(),
                    record.getEndMemoryPointer()
            );
        }

    }

    public void showTaskAdd(TaskModel taskModel) {
        System.out.println("/**\t\t task addition\t\t**/ -> " + taskModel);

    }

    public void showTaskRemove(TaskModel taskModel) {
        System.out.println("/**\t\t task removal\t\t**/ -> " + taskModel);
    }

    public void showTaskQueued(TaskModel task, TaskQueue queue) {
        System.out.println("queued: " + task.taskId + ", qs: " + queue.size());
    }

    public void showTaskUnQueuedAttempt(TaskModel task, TaskQueue queue) {
        System.out.println("unqueued attempt: " + task.taskId + ", qs: " + queue.size());
    }


}
