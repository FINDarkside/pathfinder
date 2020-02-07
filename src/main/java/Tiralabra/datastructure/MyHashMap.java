package tiralabra.datastructure;

public class MyHashMap<KT, VT> {

    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private HashMapNode<KT, VT>[] nodes = new HashMapNode[DEFAULT_CAPACITY];
    private int nodeCount = 0;
    private int size = DEFAULT_CAPACITY;
    private final float loadFactor = DEFAULT_LOAD_FACTOR;
    private float threshold = DEFAULT_CAPACITY * loadFactor;

    public void put(KT key, VT value) {
        int bucket = getBucket(key);
        insertBucket(key, value, bucket);
        if (nodeCount > threshold) {
            grow();
        }
    }

    public VT get(KT key) {
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

    public VT getOrDefault(KT key, VT defaultValue) {
        var val = get(key);
        return val != null ? val : defaultValue;
    }

    private void insertBucket(KT key, VT value, int bucket) {
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

    private int getBucket(KT key) {
        int bucket = key.hashCode() % size;
        return bucket >= 0 ? bucket : -bucket;
    }

}
