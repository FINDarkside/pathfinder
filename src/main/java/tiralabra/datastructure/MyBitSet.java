package tiralabra.datastructure;

public class MyBitSet {

    private int[] data;

    public MyBitSet(int size) {
        data = new int[size / 32 + 1];
    }

    /**
     * Sets bit to 1.
     *
     * @param i Index of bit to set.
     */
    public void set(int i) {
        set(i, true);
    }

    /**
     * Sets or unsets bit.
     *
     * @param i Index of bit to set.
     * @param bit false: 0, true: 1.
     */
    public void set(int i, boolean bit) {
        int bucket = (i / 32);
        int bitIndex = i % 32;
        if (bit) {
            data[bucket] |= 1 << bitIndex;
        } else {
            data[bucket] &= ~(1 << bitIndex);
        }
    }

    /**
     * Returns value at given index.
     *
     * @param i
     * @return true if bit was 1, otherwise false.
     */
    public boolean get(int i) {
        int bucket = (i / 32);
        int bitIndex = i % 32;
        int val = (data[bucket] >> bitIndex) & 1;
        return val == 1;
    }
}
