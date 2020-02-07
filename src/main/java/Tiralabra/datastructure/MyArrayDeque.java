package tiralabra.datastructure;

public class MyArrayDeque<T> {

    private Object[] array = new Object[10];
    private int size = 10;
    private int itemCount = 0;
    private int arrayStart;

    public void add(T value) {
        if (itemCount == size) {
            grow();
        }
        int insertIndex = getRealIndexOf(itemCount++);
        array[insertIndex] = value;
    }

    public void addFirst(T value) {
        if (itemCount == size) {
            grow();
        }
        int insertIndex = getRealIndexOf(-1);
        array[insertIndex] = value;
        itemCount++;
        arrayStart = insertIndex;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }
        int realIndex = (index + arrayStart) % size;
        return (T) array[realIndex];
    }

    public T removeFirst() {
        var firstItem = array[arrayStart];
        array[arrayStart] = null;
        arrayStart = getRealIndexOf(1);
        itemCount--;
        return (T) firstItem;
    }

    public T removeLast() {
        int index = getRealIndexOf(itemCount - 1);
        var lastItem = array[index];
        array[index] = null;
        itemCount--;
        return (T) lastItem;
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
