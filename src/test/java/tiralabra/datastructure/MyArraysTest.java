package tiralabra.datastructure;

import org.junit.Assert;
import org.junit.Test;

public class MyArraysTest {

    @Test
    public void sortArray() {
        long[] arr = {5, 13, 1, -5, 55, 20};
        MyArrays.sort(arr);
        Assert.assertArrayEquals(arr, new long[]{-5, 1, 5, 13, 20, 55});
    }
}
