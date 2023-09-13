public class MyQueue<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public MyQueue() {
        size = 0;
    }

    public void add(E value) {
        Node<E> newNode = new Node<>(value);
        if (size == 0) { //if there is no elem in queue
            head = newNode;
        } else {
            tail.setNext(newNode);
        }
        tail = newNode;

        size++;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public E peek() {
        removeCheck();

        return head.getItem();
    }

    public E poll() {
        removeCheck();

        Node<E> currentNode = head;
        Node<E> nextNode = head.getNext();

        if (nextNode == null) {
            head = null;
            tail = null;
        } else {
            head = nextNode;
            currentNode.setNext(null);
        }
        size--;
        return currentNode.getItem();
    }

    private void removeCheck() {
        if (size == 0) {
            throw new IllegalStateException("Queue is empty.");
        }
    }

    private static final class Node<E> {
        private E item;
        private Node<E> next;

        public Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }

        public Node(E item) {
            this(item, null);
        }

        public E getItem() {
            return item;
        }

        public void setItem(E item) {
            this.item = item;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}

class MyQueueTest {

    public static void main(String[] args) {
        MyQueue<String> queue = new MyQueue<>();

        // Test add
        queue.add("One");
        System.out.println("Peek after adding 'One': " + queue.peek());

        // Test size
        queue.add("Two");
        System.out.println("Size after adding 'Two': " + queue.size());

        // Test peek
        System.out.println("Peek: " + queue.peek());

        // Test poll
        System.out.println("Poll: " + queue.poll());
        System.out.println("Poll again: " + queue.poll());

        // Test clear and size
        queue.add("Three");
        queue.clear();
        System.out.println("Size after clearing: " + queue.size());
    }
}

