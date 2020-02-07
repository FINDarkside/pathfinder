package tiralabra.datastructure;

import java.util.Random;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyArrayDequeTest {

    @Test
    public void sizeReturnsCorrectNumber() {
        MyArrayDeque<Integer> arr = new MyArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            arr.add(i);
        }
        Assert.assertEquals(100, arr.size());
    }

    @Test
    public void removeFirstRemovesAndReturnsFirstItem() {
        MyArrayDeque<Integer> arr = new MyArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            arr.add(i);
        }
        Assert.assertEquals(0, (int) arr.removeFirst());
        Assert.assertEquals(99, arr.size());
        Assert.assertEquals(1, (int) arr.get(0));
    }

    @Test
    public void getReturnsCorrectItem() {
        MyArrayDeque<Integer> arr = new MyArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            arr.add(i);
        }
        Assert.assertEquals(50, (int) arr.get(50));
    }

    @Test
    public void removeLastRemovesAndReturnsLastItem() {
        MyArrayDeque<Integer> arr = new MyArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            arr.add(i);
        }
        Assert.assertEquals(99, (int) arr.removeLast());
        Assert.assertEquals(99, arr.size());
        Assert.assertEquals(98, (int) arr.get(arr.size() - 1));
    }

    @Test
    public void addFirstAddsToStartOfDeque() {
        MyArrayDeque<Integer> arr = new MyArrayDeque<>();
        for (int i = 0; i < 100; i++) {
            arr.addFirst(i);
        }
        Assert.assertEquals(99, (int) arr.get(0));
    }

    @Test
    public void worksCorrectlyAfterMultipleRemoveFirst() {
        MyArrayDeque<Integer> arr = new MyArrayDeque<>();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 100; j++) {
                arr.add(j);
            }
            for (int j = 0; j < 99; j++) {
                arr.removeFirst();
            }

        }
        Assert.assertEquals(10, (int) arr.size());
        for (int i = 0; i < 10; i++) {
            Assert.assertEquals(90 + i, (int) arr.get(i));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwOnTooLargeIndex() {
        MyArrayDeque<Integer> arr = new MyArrayDeque<>();
        arr.add(1);
        arr.add(2);
        arr.removeFirst();
        arr.get(1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void throwsOnNegativeIndex() {
        MyArrayDeque<Integer> arr = new MyArrayDeque<>();
        arr.add(1);
        arr.add(2);
        arr.removeFirst();
        arr.get(-1);
    }

}
