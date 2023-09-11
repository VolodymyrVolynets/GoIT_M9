import java.util.Arrays;

public class MyArrayList <E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] arrData;
    private int size;

    public MyArrayList() {
        arrData = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    public void add(E value) {
        if (size >= arrData.length) {
            arrData = Arrays.copyOf(arrData, size + DEFAULT_CAPACITY);
        }

        arrData[size] = value;
        size++;
    }


    public void remove(int index) {
        rangeCheck(index);
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(arrData, index + 1, arrData, index, numMoved);
        }
        arrData[size - 1] = null;
        size--;
    }

    private void rangeCheck(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public void clear() {
        for (int i = 0; i < arrData.length; i++) {
            arrData[i] = null;
        }

        size = 0;
    }

    public int size() {
        return size;
    }

    public E get(int index) {
        rangeCheck(index);
        return (E) arrData[index];
    }
}

class MyArrayListTest {
    public static void main(String[] args) {
        MyArrayList<String> list = new MyArrayList<>();

        // Testing add
        list.add("A");
        list.add("B");
        list.add("C");

        System.out.println(list.size());  // Should print 3

        // Testing get
        System.out.println(list.get(1));  // Should print B

        // Testing remove
        list.remove(1);
        System.out.println(list.get(1));  // Should print C

        // Testing clear
        list.clear();
        System.out.println(list.size());  // Should print 0
    }
}