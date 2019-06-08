package util;

/**
 * Custom ArrayList implementation
 * @param <E> Type of the array list
 */
public class MyArrayList<E> {

    private static final int INITIAL_CAPACITY = 10;
    private int size = 0;
    private Object[] data = {};

    /**
     * Size of the list
     * @return Size as int
     */
    public int getSize() {
        return size;
    }

    /**
     *
     * Constructor
     */
    public MyArrayList() {
        data = new Object[INITIAL_CAPACITY];
    }

    /**
     *
     *  with initial size
     */
    public MyArrayList(int capacity) {
        data = new Object[capacity];
    }

    /**
     *
     * Constructor with array
     */
    public MyArrayList(E[] array) {
        data = new Object[array.length];
        for (int i = 0; i < array.length; i++) {
            data[i] = array[i];
        }
        this.size = array.length;
    }

    /**
     * Add an element in the ArrayList
     * @param e The element
     */
    public void add(E e) {
        if (size == data.length)
            ensureCapacity();
        data[size++] = e;
    }

    /**
     * Return an element of the ArrayList
     * @param index Index of the element to be returned
     * @return The element
     */
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + size);
        return (E) data[index];
    }

    /**
     * Return an element of the ArrayList
     * @param index Index of the element to be returned
     * @return The element
     */
    @SuppressWarnings("unchecked")
    public void set(int index, E value) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + size);
        data[index] = value;
    }

    /**
     * Remove an element from the ArrayList
     * @param index Index of the element to be removed
     * @return
     */
    public void remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size " + size);
        for (int i = index; i < size - 1; i++)
            data[i] = data[i + 1];
        size--;
    }

    /**
     * Double the capacity
     */
    private void ensureCapacity() {
        Object[] newData = new Object[data.length * 2];
        System.arraycopy(data, 0, newData, 0, data.length);
        this.data = newData;
    }

    /**
     * Create a string representation of the ArrayList
     * @return The representation
     */
    public E[] toArray() {
        Object[] value = new Object[size];
        for(int i = 0; i < size; i++)
            value[i] = data[i];
        return (E[]) value;
    }

    /**
     * Create a string representation of the ArrayList
     * @return The representation
     */
    public String toString() {
        String value = "";
        for(int i = 0; i < size; i++)
            value += "," + data[i];
        return value.length() > 0 ? value.substring(1) : "";
    }
}
