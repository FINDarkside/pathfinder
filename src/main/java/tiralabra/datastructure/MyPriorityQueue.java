package tiralabra.datastructure;

/**
 * Data structure supporting adding elements and retrieving and removing the least element in
 * logarithmic time.
 *
 * @author FINDarkside
 * @param <T>
 */
public class MyPriorityQueue<T extends Comparable> {

    private static final int DEFAULT_CAPACITY = 10;

    private Comparable[] items = new Comparable[DEFAULT_CAPACITY];
    private int itemCount = 0;

    /**
     * Add new item to queue.
     *
     * @param item Item to add.
     */
    public void add(T item) {
        if (itemCount == items.length - 1) {
            grow();
        }
        items[itemCount] = item;
        shiftUp(itemCount);
        itemCount++;

    }

    /**
     * Returns and removes first element of queue.
     *
     * @return First element of queue.
     */
    public T poll() {
        if (itemCount == 0) {
            return null;
        }
        T item = (T) items[0];
        items[0] = items[itemCount - 1];
        items[itemCount - 1] = null;
        itemCount--;
        shiftDown(0);
        return item;
    }

    /**
     * Adds all elements from MyArrayDeque to queue.
     *
     * @param arr
     */
    public void addAll(MyArrayDeque<T> arr) {
        for (int i = 0; i < arr.size(); i++) {
            add(arr.get(i));
        }
    }

    /**
     * Checks if queue is empty.
     *
     * @return True is queue is empty.
     */
    public boolean isEmpty() {
        return itemCount == 0;
    }

    /**
     * Returns amount of elements in queue.
     *
     * @return Amount of elements in queue.
     */
    public int size() {
        return itemCount;
    }

    /**
     * Shifts element at index i up in the binary tree until heap property is
     * satisfied.
     *
     * @param i Index to shift up in the tree.
     */
    private void shiftUp(int i) {
        while (i != 0 && items[parentIndex(i)].compareTo(items[i]) > 0) {
            swap(i, parentIndex(i));
            i = parentIndex(i);
        }
    }

    /**
     * Shifts element at index i down in the binary tree until heap property is
     * satisfied.
     *
     * @param i
     */
    private void shiftDown(int i) {
        while (leftChildIndex(i) < itemCount) {
            int minIndex = minChild(i);
            if (items[minIndex].compareTo(items[i]) >= 0) {
                break;
            }
            swap(i, minIndex);
            i = minIndex;
        }
    }

    /**
     * Returns index of the lesser child of item at index i. Should not be
     * called if i does not have any children.
     *
     * @param i
     * @return index of the lesser child of item at index i.
     */
    private int minChild(int i) {
        if (rightChildIndex(i) >= itemCount) {
            return leftChildIndex(i);
        }
        var leftChild = items[leftChildIndex(i)];
        var rightChild = items[rightChildIndex(i)];
        return leftChild.compareTo(rightChild) < 0 ? leftChildIndex(i) : rightChildIndex(i);
    }

    private int parentIndex(int i) {
        return (i - 1) / 2;
    }

    private int leftChildIndex(int i) {
        return i * 2 + 1;
    }

    private int rightChildIndex(int i) {
        return i * 2 + 2;
    }

    private void swap(int a, int b) {
        var tmp = items[a];
        items[a] = items[b];
        items[b] = tmp;
    }

    private void grow() {
        var newArr = new Comparable[items.length * 2];
        for (int i = 0; i < itemCount; i++) {
            newArr[i] = items[i];
        }
        items = newArr;
    }
}
