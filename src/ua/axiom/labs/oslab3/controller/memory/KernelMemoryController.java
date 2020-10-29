package ua.axiom.labs.oslab3.controller.memory;


import ua.axiom.labs.oslab3.model.*;
import ua.axiom.labs.oslab3.model.config.Configuration;
import ua.axiom.labs.oslab3.model.memtable.MemoryMappingTable;
import ua.axiom.labs.oslab3.viewer.View;


public class KernelMemoryController {
    private final TaskQueue taskQueue;
    private final View view;

    private final MemoryMappingTable memoryMappingTable;
    private final PhysicalMemoryController physicalMemoryController;

    /**
     *
     * @param configuration holds data about memory size
     * @param taskQueue holds queue of tasks to process
     */
    public KernelMemoryController(Configuration configuration, TaskQueue taskQueue, View view) {
        this.physicalMemoryController = new PhysicalMemoryController(configuration.getPhysicalMemorySize());
        this.taskQueue = taskQueue;
        this.view = view;

        this.memoryMappingTable = new MemoryMappingTable(configuration);
    }

    public void registerNewTask(TaskModel task) {
        if (!memoryMappingTable.isEnoughSpaceForNewAlloc(task.requiredMem)) {
            System.out.println("compressing");
            memoryMappingTable.compressMemory();
            if (!memoryMappingTable.isEnoughSpaceForNewAlloc(task.requiredMem)) {
                taskQueue.addTask(task);
                view.showTaskQueued(task, taskQueue);
                return;
            }
        }

        memoryMappingTable.addTask(task);
        view.showTaskAdd(task);
    }

    public byte getDataAt(short task, int vPointer) {
        int pPointer =  memoryMappingTable.getShiftForTask(task);
        pPointer += vPointer;
        return physicalMemoryController.getByteAtPhysicalAddress(pPointer);
    }

    public void finishTask(short taskId) {
        memoryMappingTable.removeTask(taskId);
    }

    public MemoryMappingTable getMemoryController() {
        return memoryMappingTable;
    }

    public PhysicalMemoryController getPhysicalMemoryController() {
        return physicalMemoryController;
    }
}
