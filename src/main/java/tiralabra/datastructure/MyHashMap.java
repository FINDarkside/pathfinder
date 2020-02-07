package tiralabra.datastructure;

public class MyHashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private HashMapNode<K, V>[] nodes = new HashMapNode[DEFAULT_CAPACITY];
    private int nodeCount = 0;
    private int size = DEFAULT_CAPACITY;
    private final float loadFactor = DEFAULT_LOAD_FACTOR;
    private float threshold = DEFAULT_CAPACITY * loadFactor;

    /**
     * Associates key with given value.
     *
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        int bucket = getBucket(key);
        insertBucket(key, value, bucket);
        if (nodeCount > threshold) {
            grow();
        }
    }

    /**
     * Gets the value associated with key.
     *
     * @param key
     * @return value associated with key.
     */
    public V get(K key) {
        int bucket = getBucket(key);
        var currentNode = nodes[bucket];
        while (currentNode != null) {
            if (currentNode.getKey().equals(key)) {
                return currentNode.getValue();
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    /**
     * Gets the value associated with key or default value if key does not
     * exist.
     *
     * @param key
     * @param defaultValue Value to return if key doesn't exist.
     * @return value associated with key or default value if key does not exist.
     */
    public V getOrDefault(K key, V defaultValue) {
        var val = get(key);
        return val != null ? val : defaultValue;
    }

    private void insertBucket(K key, V value, int bucket) {
        if (nodes[bucket] == null) {
            nodes[bucket] = new HashMapNode(key, value);
            nodeCount++;
        } else {
            HashMapNode currentNode = nodes[bucket];
            while (true) {
                if (currentNode.getKey().equals(key)) {
                    currentNode.setValue(value);
                    return;
                }
                if (currentNode.getNext() != null) {
                    currentNode = currentNode.getNext();
                } else {
                    break;
                }
            }
            currentNode.setNext(new HashMapNode(key, value));
            nodeCount++;
        }
    }

    private void grow() {
        size *= 2;
        threshold = size * loadFactor;

        var oldNodes = nodes;
        nodes = new HashMapNode[size];
        for (var node : oldNodes) {
            if (node == null) {
                continue;
            }
            var currentNode = node;
            while (currentNode != null) {
                put(currentNode.getKey(), currentNode.getValue());
                currentNode = currentNode.getNext();
            }
        }
    }

    private int getBucket(K key) {
        int bucket = key.hashCode() % size;
        return bucket >= 0 ? bucket : -bucket;
    }

}
