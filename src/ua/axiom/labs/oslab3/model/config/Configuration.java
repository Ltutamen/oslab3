package ua.axiom.labs.oslab3.model.config;

public class Configuration {
    private int virtualMemorySize = 128;
    private int physicalMemorySize = 128;
    private short tasksToRun = 64;

    public Configuration() {
    }

    public int getVirtualMemorySize() {
        return virtualMemorySize;
    }

    public void setVirtualMemorySize(int virtualMemorySize) {
        this.virtualMemorySize = virtualMemorySize;
    }

    public int getPhysicalMemorySize() {
        return physicalMemorySize;
    }

    public void setPhysicalMemorySize(int physicalMemorySize) {
        this.physicalMemorySize = physicalMemorySize;
    }

    public short getTasksToRun() {
        return tasksToRun;
    }

    public void setTasksToRun(short tasksToRun) {
        this.tasksToRun = tasksToRun;
    }
}
