package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class UtilTest {

    private static final double DELTA = 1e-15;

    @Test
    public void l2norm() {
    }

    @Test
    public void highPassFilter() {
    }

    @Test
    public void normalize() {
        double[][] actual = Util.normalize(new double[][]{{0, 10.0}, {2.0, 3.0}});
        assertArrayEquals(new double[][]{{0, 1}, {0.2, 0.3}}, actual);

        actual = Util.normalize(new double[][]{{0, -10.0}, {-2.0, -3.0}});
        assertArrayEquals(new double[][]{{0, 1}, {0.2, 0.3}}, actual);
    }

    @Test
    protected void maxValue() {
        double[] actual = Util.maxValue(new double[][]{{0, 1},{2, 3}});
        assertArrayEquals(new double[]{3, 1, 1}, actual, DELTA);

        actual = Util.maxValue(new double[][]{{1,1},{1,1}});
        assertArrayEquals(new double[]{1, 0,0}, actual, DELTA);

        actual = Util.maxValue(new double[] {1,2,3});
        assertArrayEquals(new double[]{3, 2}, actual, DELTA);

    }

}