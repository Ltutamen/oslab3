package ua.axiom.labs.oslab3.model.memtable;

import ua.axiom.labs.oslab3.model.config.Configuration;
import ua.axiom.labs.oslab3.model.TaskModel;

import java.lang.management.MemoryManagerMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Maps virtual memory to physical,
 * manages memory
 */
public class MemoryMappingTable {

    private final long physicalMemorySize;

    private final List<MemoryTableRecord> records = new ArrayList<>();

    public MemoryMappingTable(Configuration configuration) {
        this.physicalMemorySize = configuration.getPhysicalMemorySize();
    }

    public boolean isEnoughSpaceForNewAlloc(int bytes) {
        int lastMemPtr;
        if(records.size() == 0) {
            lastMemPtr = 0;
        }
        else {
            MemoryTableRecord lastRecord = records.get(records.size() - 1);
            lastMemPtr = lastRecord.getEndMemoryPointer();

        }

        return physicalMemorySize - lastMemPtr > bytes;
    }

    public int getShiftForTask(short taskID) {
        for(MemoryTableRecord record : records) {
            if(record.getForTaskPID() == taskID) {
                return record.getBeginMemoryPointer();
            }
        }

        throw new RuntimeException("Task with id: <" + taskID + "> does not have assigned memory record");
    }

    //  move out
    public void compressMemory() {
        for (int prevPointer = 0; prevPointer < records.size() - 1; ++prevPointer) {
            int nextPointer = prevPointer + 1;

            MemoryTableRecord previousRecord = records.get(prevPointer);
            MemoryTableRecord nextRecord = records.get(nextPointer);

            if(previousRecord.getEndMemoryPointer() != nextRecord.getBeginMemoryPointer()) {
                //  if there is skipped memory
                int skipSize = nextRecord.getBeginMemoryPointer() - previousRecord.getEndMemoryPointer();

                nextRecord.shiftDown(skipSize);
            }
        }
    }

    public void addTask(TaskModel task) {
        if(!isEnoughSpaceForNewAlloc(task.requiredMem)) {
            throw new RuntimeException();
        }

        int pointer;
        if(records.isEmpty()) {
            pointer = 0;
        }
        else {
            MemoryTableRecord lastRecord = records.get(records.size() - 1);
            pointer = lastRecord.getEndMemoryPointer();
        }

        MemoryTableRecord record = new MemoryTableRecord(task.taskId, pointer, pointer + task.requiredMem);
        records.add(record);
    }

    /**
     * removes memory record for task with ID
     * @param taskID from the table
     *
     */
    public void removeTask(short taskID) {
        records.removeIf(record -> record.getForTaskPID() == taskID);
    }

    public List<MemoryTableRecord> getRecords() {
        return new ArrayList<>(records);
    }

}
