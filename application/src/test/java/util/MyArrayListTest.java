package util;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeTrue;

public class MyArrayListTest {

    @Test
    public void test_construct() {
        MyArrayList<int[][]> list2 = new MyArrayList<>();
        assertEquals(0, list2.getSize());
    }

    @Test
    public void test_add() {
        MyArrayList<int[][]> list = new MyArrayList<>();
        list.add(new int[][] {{1, 2}, {3, 4}});

        assertNotNull(list);
        assertEquals(1, list.getSize());

        list.add(new int[][] {{1, 2}, {3, 4}});
        list.add(new int[][] {{1, 2}, {3, 4}});
        assertEquals(3, list.getSize());
    }

    @Test
    public void test_get() {

        MyArrayList<int[][]> list = new MyArrayList<>();
        list.add(new int[][] {{1, 2}, {3, 4}});

        Object actual = list.get(0);
        assertEquals((new int[][] {}).getClass().getName(), actual.getClass().getName());

        int[][] actual2 = (int[][]) actual;
        assertEquals(actual2[0][0], 1);
        assertEquals(actual2[0][1], 2);
        assertEquals(actual2[1][0], 3);
        assertEquals(actual2[1][1], 4);
    }

    @Test
    public void test_set() {
        MyArrayList<int[][]> list = new MyArrayList<>();
        list.add(new int[][] {{1, 2}, {3, 4}});

        list.set(0, new int[][] {{5, 6}, {7, 8}});
        int[][] actual = list.get(0);
        assertEquals(actual[0][0], 5);
        assertEquals(actual[0][1], 6);
        assertEquals(actual[1][0], 7);
        assertEquals(actual[1][1], 8);
    }

    @Test
    public void test_toString() {
        MyArrayList<int[][]> list = new MyArrayList<>();
        list.add(new int[][] {{1, 2}, {3, 4}});

        assertEquals("jepulis", list.toString());
    }

    @Test
    public void test_remove() {
        MyArrayList<int[][]> list = new MyArrayList<>();
        list.add(new int[][] {{1, 2}, {3, 4}});

        list.remove(0);
        assertEquals(0, list.getSize());
    }

}