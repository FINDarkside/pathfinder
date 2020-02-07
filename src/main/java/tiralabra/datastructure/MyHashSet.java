package tiralabra.datastructure;

public class MyHashSet<T> {

    private MyHashMap<T, Boolean> map = new MyHashMap<>();

    public void add(T value) {
        map.put(value, true);
    }

    public boolean contains(T value) {
        return map.get(value) != null;
    }

}
