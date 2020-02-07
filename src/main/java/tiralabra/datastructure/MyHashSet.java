package tiralabra.datastructure;

public class MyHashSet<T> {

    private MyHashMap<T, Boolean> map = new MyHashMap<>();

    /**
     * Adds value to set.
     *
     * @param value Value to add.
     */
    public void add(T value) {
        map.put(value, true);
    }

    /**
     * Checks if set contains given value.
     *
     * @param value
     * @return true if value is included in set.
     */
    public boolean contains(T value) {
        return map.get(value) != null;
    }

}
