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
     *
     * Constructor
     */
    public MyArrayList() {
        data = new Object[INITIAL_CAPACITY];
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
    public String toString() {
        String value = "";
        for (int i = 0; i < size - 1; i++)
            value += data[i + 1].toString();
        return value;
    }
}
