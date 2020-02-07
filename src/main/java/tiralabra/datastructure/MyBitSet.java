package tiralabra.datastructure;

public class MyBitSet {

    private int[] data;

    public MyBitSet(int size) {
        data = new int[size / 32 + 1];
    }

    public void set(int i) {
        set(i, true);
    }

    public void set(int i, boolean bit) {
        int bucket = (i / 32);
        int bitIndex = i % 32;
        if (bit) {
            data[bucket] |= 1 << bitIndex;
        } else {
            data[bucket] &= ~(1 << bitIndex);
        }
    }

    public boolean get(int i) {
        int bucket = (i / 32);
        int bitIndex = i % 32;
        int val = (data[bucket] >> bitIndex) & 1;
        return val == 1;
    }
}
