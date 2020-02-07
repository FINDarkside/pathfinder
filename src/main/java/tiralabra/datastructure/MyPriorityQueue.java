package tiralabra.datastructure;

import java.util.PriorityQueue;

public class MyPriorityQueue<T> {

    // TODO: Implement!
    private PriorityQueue<T> queue = new PriorityQueue<>();

    public void add(T item) {
        queue.add(item);
    }

    public T poll() {
        return queue.poll();
    }

    public void addAll(MyArrayDeque<T> arr) {
        for (int i = 0; i < arr.size(); i++) {
            queue.add(arr.get(i));
        }
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
