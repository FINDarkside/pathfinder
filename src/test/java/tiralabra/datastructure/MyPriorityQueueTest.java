package tiralabra.datastructure;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class MyPriorityQueueTest {

    @Test
    public void returnsItemsInCorrectOrder() {
        MyPriorityQueue<Integer> q = new MyPriorityQueue<>();
        q.add(4);
        q.add(2);
        q.add(8);
        q.add(-1);
        q.add(5);

        assertEquals(-1, (int) q.poll());
        assertEquals(2, (int) q.poll());
        assertEquals(4, (int) q.poll());
        assertEquals(5, (int) q.poll());
        assertEquals(8, (int) q.poll());
    }

    @Test
    public void returnsSizeCorrectly() {
        MyPriorityQueue<Integer> q = new MyPriorityQueue<>();
        q.add(4);
        q.add(2);
        q.add(8);
        q.add(-1);
        q.add(5);

        for (int i = 0; i < 5; i++) {
            assertEquals(5 - i, q.size());
            q.poll();
        }
        assertEquals(0, q.size());
    }

    @Test
    public void isEmptyWorksCorrectly() {
        MyPriorityQueue<Integer> q = new MyPriorityQueue<>();
        assertEquals(true, q.isEmpty());
        q.add(4);
        q.add(2);
        assertEquals(false, q.isEmpty());
        q.poll();
        q.poll();
        assertEquals(true, q.isEmpty());
    }

    @Test
    public void canAddManyElements() {
        MyPriorityQueue<Integer> q = new MyPriorityQueue<>();
        for (int i = 0; i < 100; i++) {
            q.add(i);
        }
        for (int i = 0; i < 100; i++) {
            assertEquals(i, (int) q.poll());
        }
    }

    @Test
    public void pollOnEmptyQueueRetunsNull() {
        MyPriorityQueue<Integer> q = new MyPriorityQueue<>();
        assertEquals(null, q.poll());
    }

}
