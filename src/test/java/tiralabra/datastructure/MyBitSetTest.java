package tiralabra.datastructure;

import junit.framework.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MyBitSetTest {

    @Test
    public void allBitsAreZeroByDefault() {
        MyBitSet b = new MyBitSet(100);
        for (int i = 0; i < 100; i++) {
            assertEquals(false, b.get(i));
        }
    }

    @Test
    public void canSetBit() {
        MyBitSet b = new MyBitSet(100);
        b.set(5);
        b.set(6, true);
        assertEquals(true, b.get(5));
        assertEquals(true, b.get(6));
    }

    @Test
    public void canUnsetBit() {
        MyBitSet b = new MyBitSet(100);
        b.set(5);
        b.set(5, false);
        assertEquals(false, b.get(5));
    }

}
