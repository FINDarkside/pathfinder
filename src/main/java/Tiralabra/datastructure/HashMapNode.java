package tiralabra.datastructure;

public class HashMapNode<K, V> {

    private K key;
    private V value;
    private HashMapNode next;

    public HashMapNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void setNext(HashMapNode next) {
        this.next = next;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public HashMapNode getNext() {
        return next;
    }

}
