package util;

import org.junit.Test;
import testutilities.TestUtilities;

import static org.junit.Assert.*;

public class MyArrayListTest {

    private static final double DELTA = 1e-15;

    @Test
    public void construct() {
        MyArrayList<Object> list = new MyArrayList<>();
        assertEquals(0, list.getSize());

        MyArrayList<Object> list2 = new MyArrayList<>(5);
        assertEquals(0, list2.getSize());
        try {
            int actual = TestUtilities.getPrivateField("capacity", list2);
            assertEquals(2, actual);
        }
        catch(Exception e) {}

        MyArrayList<Object> list3 = new MyArrayList<>(new Integer[] {0, 1});
        assertEquals(2, list3.getSize());
        assertEquals(list3.get(0), 0);
        assertEquals(list3.get(1), 1);
    }

    @Test
    public void add() {
        MyArrayList<Integer> list = new MyArrayList<>(1);
        list.add(1);

        assertNotNull(list);
        assertEquals(1, list.getSize());

        list.add(2);
        list.add(3);
        assertEquals(3, list.getSize());
    }

    @Test
    public void add_doubleArrayArray() {
        MyArrayList<double[][]> list = new MyArrayList<>();
        list.add(new double[][] {{1.0}});

        assertNotNull(list);
        assertEquals(1, list.getSize());

        list.add(new double[][]{{2.0}});
        list.add(new double[][]{{3.0}});
        assertEquals(3, list.getSize());
    }

    @Test
    public void get() {

        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);

        Object actual = list.get(0);
        assertEquals("java.lang.Integer", actual.getClass().getName());

        assertEquals((int)actual, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void get_IndexOutOfBoundsException() {
        MyArrayList<Integer> list = new MyArrayList<>(1);
        list.get(2);
    }

    @Test
    public void get_doubleArrayArray() {

        MyArrayList<double[][]> list = new MyArrayList<>();
        list.add(new double[][] {{1.0}});

        Object actual = list.get(0);
        assertEquals("[[D", actual.getClass().getName());

        double[][] actual2 = (double[][]) actual;
        assertEquals(1.0, actual2[0][0], DELTA);
    }

    @Test
    public void set() {
        MyArrayList<Integer> list = new MyArrayList<>(1);
        list.add(1);
        assertEquals(1, (int) list.get(0));
        list.set(0, 2);
        assertEquals(2, (int) list.get(0));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void set_IndexOutOfBoundsException() {
        MyArrayList<Integer> list = new MyArrayList<>(1);
        list.set(2, 1);
    }

    @Test
    public void set_doubleArrayArray() {
        MyArrayList<double[][]> list = new MyArrayList<>();
        list.add(new double[][] {{1.0}});
        assertEquals(1.0, ((double[][]) list.get(0))[0][0], DELTA);

        list.set(0, new double[][] {{2.0}});
        assertEquals(2.0, ((double[][]) list.get(0))[0][0], DELTA);
    }

    @Test
    public void toString1() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("a");
        assertEquals("a", list.toString());
        list.add("b");
        assertEquals("a,b", list.toString());
    }

    @Test
    public void remove() {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[] {1, 2, 3});
        assertEquals(3, list.getSize());
        list.remove(0);
        assertEquals(2, list.getSize());
        list.remove(0);
        list.remove(0);
        assertEquals(0, list.getSize());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void remove_IndexOutOfBoundsException() {
        MyArrayList<Integer> list = new MyArrayList<>(1);
        list.remove(2);
    }

    @Test
    public void getSize() {
        MyArrayList<Integer> list = new MyArrayList<>(new Integer[] {1, 2, 3});
        assertEquals(3, list.getSize());

        MyArrayList<Integer> list2 = new MyArrayList<>();
        assertEquals(0, list2.getSize());
    }
}