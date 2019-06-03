package util;

import org.junit.Test;
import static org.junit.Assert.*;

public class MyArrayListTest {

    private static final double DELTA = 1e-15;

    @Test
    public void test_construct() {
        MyArrayList<Object> list2 = new MyArrayList<>();
        assertEquals(0, list2.getSize());
    }

    @Test
    public void test_add() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);

        assertNotNull(list);
        assertEquals(1, list.getSize());

        list.add(2);
        list.add(3);
        assertEquals(3, list.getSize());
    }

    @Test
    public void test_add_doubleArrayArray() {
        MyArrayList<double[][]> list = new MyArrayList<>();
        list.add(new double[][] {{1.0}});

        assertNotNull(list);
        assertEquals(1, list.getSize());

        list.add(new double[][]{{2.0}});
        list.add(new double[][]{{3.0}});
        assertEquals(3, list.getSize());
    }

    @Test
    public void test_get() {

        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);

        Object actual = list.get(0);
        assertEquals("java.lang.Integer", actual.getClass().getName());

        assertEquals((int)actual, 1);
    }

    @Test
    public void test_get_doubleArrayArray() {

        MyArrayList<double[][]> list = new MyArrayList<>();
        list.add(new double[][] {{1.0}});

        Object actual = list.get(0);
        assertEquals("[[D", actual.getClass().getName());

        double[][] actual2 = (double[][]) actual;
        assertEquals(1.0, actual2[0][0], DELTA);
    }


    @Test
    public void test_set() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);

        list.set(0, 2);
        assertEquals((int) list.get(0), 2);
    }

    @Test
    public void test_set_doubleArrayArray() {

        MyArrayList<double[][]> list = new MyArrayList<>();
        list.add(new double[][] {{1.0}});
        assertEquals(1.0, ((double[][]) list.get(0))[0][0], DELTA);

        list.set(0, new double[][] {{2.0}});
        assertEquals(2.0, ((double[][]) list.get(0))[0][0], DELTA);
    }

    @Test
    public void test_toString() {
        MyArrayList<String> list = new MyArrayList<>();
        list.add("a");
        assertEquals("a", list.toString());
        list.add("b");
        assertEquals("a,b", list.toString());
    }

    @Test
    public void test_remove() {
        MyArrayList<Integer> list = new MyArrayList<>();
        list.add(1);
        assertEquals(1, list.getSize());

        list.remove(0);
        assertEquals(0, list.getSize());
    }

}