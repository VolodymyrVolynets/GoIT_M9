import java.util.Arrays;

public class MyHashMap<K, V> {
    private final Node<K, V>[] table;
    private static final int BASE_SIZE = 10;
    int size;

    public MyHashMap() {
        size = 0;
        table = new Node[BASE_SIZE]; // I will use first digit of hash to detect bucket
    }

    public void put(K key, V value) {
        int bucketNum = getBucketNum(key.hashCode());

        if (table[bucketNum] == null) { // if bucket is empty
            table[bucketNum] = new Node<>(key, value);
            size++;
        } else {
            // iterate to find last node in bucket
            Node<K, V> currentNode = table[bucketNum];
            while (currentNode.getNext() != null && !currentNode.getKey().equals(key)) {
                currentNode = currentNode.getNext();
            }

            // check if duplicate found
            if (currentNode.getKey().equals(key))
                currentNode.setValue(value);
            else {
                currentNode.setNext(new Node<>(key, value));
                size++;
            }
        }
    }

    public void remove(K key) {
        if (!contains(key)) return; // if key not found return

        int bucketNum = getBucketNum(key.hashCode());

        if (table[bucketNum] == null) return; // if bucket is empty do nothing

        // iterate to find previous node and node to delete in bucket
        Node<K, V> currentNode = table[bucketNum];
        Node<K, V> prevNode = null;
        while (!currentNode.getKey().equals(key) && currentNode.getNext() != null) {
            prevNode = currentNode;
            currentNode = currentNode.getNext();
        }

        if (prevNode != null) // if previous node exist
            prevNode.setNext(currentNode.getNext());
        else  // if found first elem
            table[bucketNum] = currentNode.getNext(); // can be a node or null
        size--;
    }

    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public V get(K key) {
        int bucketNum = getBucketNum(key.hashCode());

        if (table[bucketNum] == null) return null; // if bucket is empty

        // iterate to find node
        Node<K, V> currentNode = table[bucketNum];
        while (!currentNode.getKey().equals(key) && currentNode.getNext() != null)
            currentNode = currentNode.getNext();

        // if found
        if (currentNode.getKey().equals(key)) {
           return currentNode.getValue();
        }

        return null;
    }

    private int getBucketNum(int hash) {
        return Math.abs(hash % 10);
    }

    private boolean contains(K key) {
        int bucketNum = getBucketNum(key.hashCode());

        if (table[bucketNum] == null) return false; // if bucket is empty

        // iterate to find node
        Node<K, V> currentNode = table[bucketNum];
        while (!currentNode.getKey().equals(key) && currentNode.getNext() != null)
            currentNode = currentNode.getNext();

        return currentNode.getKey().equals(key);
    }

    private static final class Node<K, V> {
        private final K key;
        private V value;
        private final int hash;
        private Node<K, V> next;

        public Node(K key, V value, Node<K, V> next) {
            this.hash = key.hashCode();
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value) {
            this(key, value, null);
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Node<K, V> getNext() {
            return next;
        }

        public void setNext(Node<K, V> next) {
            this.next = next;
        }

        public int getHash() {
            return hash;
        }
    }
}

class MyHashMapTest {
    public static void main(String[] args) {
        MyHashMap<String, Integer> hashMap = new MyHashMap<>();

        // Testing put
        hashMap.put("one", 1);
        hashMap.put("two", 2);
        hashMap.put("three", 3);

        // Testing get
        System.out.println("Value for 'one': " + hashMap.get("one"));  // Expected: 1
        System.out.println("Value for 'two': " + hashMap.get("two"));  // Expected: 2
        System.out.println("Value for 'three': " + hashMap.get("three"));  // Expected: 3
        System.out.println("Value for 'four': " + hashMap.get("four"));  // Expected: null

        // Testing size
        System.out.println("Size: " + hashMap.size());  // Expected: 3

        // Testing update (put with an existing key)
        hashMap.put("one", 11);
        System.out.println("Updated value for 'one': " + hashMap.get("one"));  // Expected: 11

        // Testing remove
        hashMap.remove("two");
        System.out.println("Value for 'two' after removal: " + hashMap.get("two"));  // Expected: null
        System.out.println("Size after removing 'two': " + hashMap.size());  // Expected: 2

        // Testing clear
        hashMap.clear();
        System.out.println("Size after clear: " + hashMap.size());  // Expected: 0
    }

}
