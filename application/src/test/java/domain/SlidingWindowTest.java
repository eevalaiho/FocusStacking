package domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SlidingWindowTest {

    private static final double DELTA = 1e-15;
    private static SlidingWindow window;
    private static int width = 6;
    private static int height = 4;
    private static int size = 2;

    @Before
    public void before() {
        window = new SlidingWindow(size, width, height);
    }

    @Test
    public void construct() {
        assert 0 == window.getX();
        assert 0 == window.getY();
    }

    @Test
    public void getX() {
        assert 0 == window.getX();
    }

    @Test
    public void getY() {
        assert 0 == window.getY();
    }

    @Test
    public void hasNext() {
        assert window.hasNext();
    }

    @Test
    public void moveNext() {
        window.moveNext();
        assert 1 == window.getX();
        assert 0 == window.getY();
    }

    @Test
    public void getDoubleMirroredWindow() {
        SlidingWindow window = new SlidingWindow(2, 4, 3);
        double[][] array = new double[][]{{1, 2, 3, 4}, {2, 3, 4, 5}, {3, 4, 5, 6}};

        // Test
        double[][] expected = new double[][]{{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};
        assertArrayEquals(expected, window.getDoubleMirroredWindow(array));
    }

    @Test
    public void getDoubleMirroredWindow_topleft() {
        SlidingWindow window = new SlidingWindow(2, 4, 3);
        double[][] array = new double[][]{{1, 2, 3, 4}, {2, 3, 4, 5}, {3, 4, 5, 6}};

        for (int i = 0; i < 5; i++)
            window.moveNext();
        double[][] expected2 = new double[][]{{1, 2, 2, 1}, {2, 3, 3, 2}, {2, 3, 3, 2}, {1, 2, 2, 1}};
        assertArrayEquals(expected2, window.getDoubleMirroredWindow(array));
    }

    @Test
    public void getDoubleMirroredWindow_bottomright() {
        SlidingWindow window = new SlidingWindow(2, 4, 3);
        double[][] array = new double[][]{{1, 2, 3, 4}, {2, 3, 4, 5}, {3, 4, 5, 6}};

        for (int i = 0; i < 11; i++)
            window.moveNext();
        double[][] expected = new double[][]{{4, 5, 5, 4}, {5, 6, 6, 5}, {5, 6, 6, 5}, {4, 5, 5, 4}};
        double[][] actual = window.getDoubleMirroredWindow(array);
        assertArrayEquals(expected, actual);
    }
}