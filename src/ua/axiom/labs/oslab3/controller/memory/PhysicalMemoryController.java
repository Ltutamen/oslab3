package ua.axiom.labs.oslab3.controller.memory;

public class PhysicalMemoryController {
    //  model
    private final int[] memory;

    public PhysicalMemoryController(int memorySizeInBytes) {
        this.memory = new int[memorySizeInBytes / 4];
    }

    public byte getByteAtPhysicalAddress(long address) {
        return (byte)(memory[(int)address / 4] & (255 << (address % 4)));
    }

    public void putDataAtPhysicalAddress(long address, byte data) {
        memory[(int)address / 4] |= data << (address % 4);
    }

    public void copy(int physicalStartPinter, int physicalEndPointer, int size) {
        System.arraycopy(memory, physicalStartPinter, memory, physicalEndPointer, size);
    }
}
