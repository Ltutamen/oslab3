package ua.axiom.labs.oslab3.model.memtable;


public class MemoryTableRecord {
    private final short forTaskPID;

    //  lovest bound of the segment
    private int memoryRegionStart;
    //  pointer to the first memory position that does not belong to the record
    //  similar to the Collections: c.get(c.size()) produces error
    private int memoryRegionEnd;

    public MemoryTableRecord(short forTaskPID, int memoryRegionStart, int memoryRegionEnd) {
        this.forTaskPID = forTaskPID;
        this.memoryRegionStart = memoryRegionStart;
        this.memoryRegionEnd = memoryRegionEnd;
    }

    //  lover bound of the memory chunk
    public int getBeginMemoryPointer() {
        return memoryRegionStart;
    }

    //  upper bound of the memory chunk
    public int getEndMemoryPointer() {
        return memoryRegionEnd;
    }

    /**
     * Shifts current memory record down (to the lowest pointer value)
     * @param bytes to shift
     */
    public void shiftDown(int bytes) {
        memoryRegionEnd -= bytes;
        memoryRegionStart -= bytes;

        assert memoryRegionEnd >= memoryRegionStart && memoryRegionStart >=0;
    }

    public short getForTaskPID() {
        return forTaskPID;
    }
}
