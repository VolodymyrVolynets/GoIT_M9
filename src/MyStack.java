public class MyStack<E> {
    Node<E> tail;
    int size;

    public MyStack() {
        size = 0;
    }

    public void push(E value) {
        if (size == 0) {
            tail = new Node<>(value);
        } else {
            Node<E> newNode = new Node<>(value, tail);
            tail = newNode;
        }
    }

    public void remove(int index) {
        rangeCheck(index);

        if (index == size - 1) { // if remove last elem
            tail = tail.getPrev();
        } else {
            Node<E> currentNode = tail;

            for (int i = size - 1; i > index + 1; i--) {
                currentNode = currentNode.getPrev();
            }

            Node<E> nodeToRemove = currentNode.getPrev();
            Node<E> prevNode = nodeToRemove.getPrev();

            // If removing node in a middle
            if (prevNode != null) {
                currentNode.setPrev(prevNode);
                nodeToRemove.setPrev(null);
            } else { //If removing first node
                currentNode.setPrev(null);
            }
        }
        size--;
    }

    public void clear() {
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public E peek() {
        removeCheck();

        return tail.getItem();
    }

    public E pop() {
        removeCheck();

        Node<E> firstToRemove = tail;
        tail = firstToRemove.getPrev();
        return firstToRemove.getItem();
    }

    private void removeCheck() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty.");
        }
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    private static final class Node<E> {
        private E item;
        private Node<E> prev;

        public Node(E item, Node<E> prev) {
            this.item = item;
            this.prev = prev;
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

        public Node<E> getPrev() {
            return prev;
        }

        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
    }
}

