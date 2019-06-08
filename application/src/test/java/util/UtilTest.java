package util;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class UtilTest {

    private static final double DELTA = 1e-15;

    @Test
    public void construct() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor ctor = Util.class.getDeclaredConstructors()[0];
        ctor.setAccessible(true);
        Util c = (Util)ctor.newInstance();
    }

    @Test
    public void normalize() {
        double[][] actual = Util.normalize(new double[][]{{0, 10.0}, {2.0, 3.0}}, 0, 10);
        assertArrayEquals(new double[][]{{0, 1}, {0.2, 0.3}}, actual);

        actual = Util.normalize(new double[][]{{0, -10.0}, {-2.0, -3.0}}, -10, 0);
        assertArrayEquals(new double[][]{{0, 1}, {0.2, 0.3}}, actual);
    }

    @Test
    public void minMaxValue() {
        double[] actual = Util.minMaxValue(new double[][]{{0, 1},{2, 3}}, 0, 3);
        assertArrayEquals(new double[]{0, 3}, actual, DELTA);

        actual = Util.minMaxValue(new double[][]{{1,1},{1,1}}, 1, 1);
        assertArrayEquals(new double[]{1, 1}, actual, DELTA);


        actual = Util.minMaxValue(new double[][]{{0, 0, 0, 0, 0, 0},{0, 0, 100, 0, -10, 0}}, 1, 1);
        assertArrayEquals(new double[]{-10, 100}, actual, DELTA);
    }

    @Test
    public void l2Norm() {
        Complex[][] array = new Complex[][] {{new Complex(3, 4)}};
        assertEquals(5.0, Util.l2Norm(array), DELTA);

        Complex[][] array2 = new Complex[][] {{new Complex(-3, 4)}};
        assertEquals(5.0, Util.l2Norm(array2), DELTA);

        Complex[][] array3 = new Complex[][] {{new Complex(1, 1)}};
        assertEquals(1.414213562373095, Util.l2Norm(array3), DELTA);
    }

}