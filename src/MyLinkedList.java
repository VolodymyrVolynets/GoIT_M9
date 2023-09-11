public class MyLinkedList<E> {

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public MyLinkedList() {
        size = 0;
    }

    public void add(E value) {
        if (head == null) {
            head = new Node<>(value);
            tail = head;
        } else {
            Node<E> newNode = new Node(value, tail);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    public void remove(int index) {
        rangeCheck(index);

         if (index == 0) { // Remove head node
             Node<E> nextNode = head.getNext();
             if (nextNode != null) { // if contains more than 1 elem
                 head = nextNode;
                 nextNode.setPrev(null);
             } else { // if contains only 1 elem
                 head = null;
                 tail = null;
             }
         } else if (index == size - 1) { // Remove tail
             tail = tail.getPrev(); // Change tail to a prev node
             tail.setNext(null); // And set new tail next node as null
         } else { // Remove Node in a middle
             Node<E> currentNode = getNode(index);
             Node<E> prevNode = currentNode.getPrev();
             Node<E> nextNode = currentNode.getNext();
             prevNode.setNext(nextNode);
             nextNode.setPrev(prevNode);
         }
         size--;
    }

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public E get(int index) {
        rangeCheck(index);

        return getNode(index).getItem();
    }

    private Node<E> getNode(int index) {
        rangeCheck(index);

        // Check place where better to start (Head or Tail)
        Node<E> currentNode;
        if (index < size / 2) { // if index closer to Head, start from Head
            currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }
        } else { // if index closer to Tail, start from Tail
            currentNode = tail;
            for (int i = size - 1; i > index; i--) {
                currentNode = currentNode.getPrev();
            }
        }
        return currentNode;
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private static class Node<E> {
        private E item;
        private Node<E> prev;
        private Node<E> next;

        public Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        public Node(E item) {
            this(item, null, null);
        }

        public Node(E item, Node<E> prev) {
            this(item, prev, null);
        }

        public E getItem() {
            return item;
        }

        public void setItem(E item) {
            this.item = item;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }
    }
}

class MyLinkedListTest {

    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();

        // Test adding elements
        list.add("One");
        list.add("Two");
        list.add("Three");
        System.out.println("After adding three elements: ");
        printList(list); // Expected output: One Two Three

        // Test removing an element in the middle
        list.remove(1);
        System.out.println("\nAfter removing the second element:");
        printList(list); // Expected output: One Three

        // Test removing the head
        list.remove(0);
        System.out.println("\nAfter removing the first element:");
        printList(list); // Expected output: Three

        // Test adding another element
        list.add("Four");
        System.out.println("\nAfter adding another element:");
        printList(list); // Expected output: Three Four

        // Test clear
        list.clear();
        System.out.println("\nAfter clearing the list:");
        printList(list); // Expected output: <empty>

        // Test adding elements after clearing
        list.add("Five");
        list.add("Six");
        System.out.println("\nAfter adding two more elements:");
        printList(list); // Expected output: Five Six
    }

    public static <E> void printList(MyLinkedList<E> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
        if(list.size() == 0) {
            System.out.print("<empty>");
        }
    }
}

