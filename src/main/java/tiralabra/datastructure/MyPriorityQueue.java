package tiralabra.datastructure;

import java.util.PriorityQueue;

public class MyPriorityQueue<T> {

    // TODO: Implement!
    private PriorityQueue<T> queue = new PriorityQueue<>();

    /**
     * Add new item to queue.
     *
     * @param item Item to add.
     */
    public void add(T item) {
        queue.add(item);
    }

    /**
     * Returns and removes first element of queue.
     *
     * @return First element of queue.
     */
    public T poll() {
        return queue.poll();
    }

    /**
     * Adds all elements from MyArrayDeque to queue.
     *
     * @param arr
     */
    public void addAll(MyArrayDeque<T> arr) {
        for (int i = 0; i < arr.size(); i++) {
            queue.add(arr.get(i));
        }
    }

    /**
     * Checks if queue is empty.
     *
     * @return True is queue is empty.
     */
    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
