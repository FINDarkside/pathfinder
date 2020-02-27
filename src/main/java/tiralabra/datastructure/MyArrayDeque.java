package tiralabra.datastructure;

/**
 * Data structure allowing O(1) operations for removing and adding in either end
 * of the list. Accessing by index is O(1) as well.
 *
 * @param <T> type of elements in deque.
 */
public class MyArrayDeque<T> {

    private Object[] array = new Object[10];
    private int size = 10;
    private int itemCount = 0;
    private int arrayStart;

    /**
     * Adds new value to deque.
     *
     * @param value
     */
    public void add(T value) {
        if (itemCount == size) {
            grow();
        }
        int insertIndex = getRealIndexOf(itemCount++);
        array[insertIndex] = value;
    }

    /**
     * Adds all values from arr.
     *
     * @param arr
     */
    public void addAll(MyArrayDeque<T> arr) {
        for (int i = 0; i < arr.size(); i++) {
            this.add(arr.get(i));
        }
    }

    /**
     * Adds value to the start of deque.
     *
     * @param value
     */
    public void addFirst(T value) {
        if (itemCount == size) {
            grow();
        }
        int insertIndex = getRealIndexOf(-1);
        array[insertIndex] = value;
        itemCount++;
        arrayStart = insertIndex;
    }

    /**
     * Gets the last element of deque.
     *
     * @param index
     * @return Last element of deque.
     */
    public T get(int index) {
        if (index < 0 || index >= itemCount) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        int realIndex = (index + arrayStart) % size;
        return (T) array[realIndex];
    }

    /**
     * Removes and returns first element.
     *
     * @return First element of deque.
     */
    public T removeFirst() {
        var firstItem = array[arrayStart];
        array[arrayStart] = null;
        arrayStart = getRealIndexOf(1);
        itemCount--;
        return (T) firstItem;
    }

    /**
     * Removes and returns last element.
     *
     * @return Last element of deque.
     */
    public T removeLast() {
        int index = getRealIndexOf(itemCount - 1);
        var lastItem = array[index];
        array[index] = null;
        itemCount--;
        return (T) lastItem;
    }

    /**
     * Returns the amount of items in deque.
     *
     * @return amount of items in deque.
     */
    public int size() {
        return itemCount;
    }

    /**
     * Returns whether deque is empty.
     *
     * @return true if deque is empty, otherwise false.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    private void grow() {
        Object[] newArr = new Object[size * 2];
        for (int i = 0; i < size; i++) {
            newArr[i] = get(i);
        }
        array = newArr;
        size *= 2;
        arrayStart = 0;
    }

    private int getRealIndexOf(int i) {
        int realIndex = (arrayStart + i) % size;
        if (realIndex < 0) {
            return realIndex + size;
        }
        return realIndex;
    }

}
