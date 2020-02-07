package tiralabra.datastructure;

public class HashMapNode<KT, VT> {

    private KT key;
    private VT value;
    private HashMapNode next;

    public HashMapNode(KT key, VT value) {
        this.key = key;
        this.value = value;
    }

    public void setNext(HashMapNode next) {
        this.next = next;
    }

    public void setValue(VT value) {
        this.value = value;
    }

    public KT getKey() {
        return key;
    }

    public VT getValue() {
        return value;
    }

    public HashMapNode getNext() {
        return next;
    }

}
