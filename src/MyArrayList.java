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
    }

    public void remove(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(arrData, index + 1, arrData, index, numMoved);
        }
        arrData[size - 1] = null;
        size--;
    }

    public void clear() {
        arrData = new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public E get(int index) {
        return (E) arrData[index];
    }
}

class Main {
    public static void main(String[] args) {
    }
}
