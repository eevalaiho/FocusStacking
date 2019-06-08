package domain;

import org.junit.Test;
import testutilities.TestUtilities;
import util.Complex;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;

public class FFTTest {

    private static final double DELTA = 1e-15;

    @Test
    public void construct() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor ctor = FFT.class.getDeclaredConstructors()[0];
        ctor.setAccessible(true);
        FFT c = (FFT)ctor.newInstance();
    }

    @Test
    public void fft2_1111() {
        double[][] array = new double[][] {{1.0, 1.0}, {1.0, 1.0}};
        Complex[][] result = FFT.fft2(array);
        assertEquals(4.0, result[0][0].re(), DELTA);
        assertEquals(0.0, result[0][0].im(), DELTA);
        assertEquals(0.0, result[0][1].re(), DELTA);
        assertEquals(0.0, result[0][1].im(), DELTA);
        assertEquals(0.0, result[1][0].re(), DELTA);
        assertEquals(0.0, result[1][0].im(), DELTA);
        assertEquals(0.0, result[1][1].re(), DELTA);
        assertEquals(0.0, result[1][1].im(), DELTA);

    }

    @Test
    public void fft2_0000() {
        double[][] array = new double[][] {{0, 0}, {0, 0}};
        Complex[][] result = FFT.fft2(array);
        assertEquals(0.0, result[0][0].re(), DELTA);
        assertEquals(0.0, result[0][0].im(), DELTA);
        assertEquals(0.0, result[0][1].re(), DELTA);
        assertEquals(0.0, result[0][1].im(), DELTA);
        assertEquals(0.0, result[1][0].re(), DELTA);
        assertEquals(0.0, result[1][0].im(), DELTA);
        assertEquals(0.0, result[1][1].re(), DELTA);
        assertEquals(0.0, result[1][1].im(), DELTA);

    }

    @Test
    public void fft2_1234() {
        double[][] array = new double[][] {{1, 2}, {3, 4}};
        Complex[][] result = FFT.fft2(array);
        assertEquals(10.0, result[0][0].re(), DELTA);
        assertEquals(0.0, result[0][0].im(), DELTA);
        assertEquals(-2.0, result[0][1].re(), DELTA);
        assertEquals(0.0, result[0][1].im(), DELTA);
        assertEquals(-4.0, result[1][0].re(), DELTA);
        assertEquals(0.0, result[1][0].im(), DELTA);
        assertEquals(0.0, result[1][1].re(), DELTA);
        assertEquals(0.0, result[1][1].im(), DELTA);

    }

    @Test(expected = IllegalArgumentException.class)
    public void fft2_unequal_dim() {
        double[][] array = new double[][] {{1, 2, 2}, {3, 4, 4}};
        FFT.fft2(array);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fft2_not_a_pow_of_2() {
        double[][] array = new double[][] {{1, 2, 2}, {3, 4, 4}, {5, 6, 6}};
        FFT.fft2(array);
    }

    @Test
    public void fft_1234() {
        Complex[] array = new Complex[] {new Complex(1,0), new Complex(2, 0), new Complex(3, 0), new Complex(4, 0)};
        Complex[] result = FFT.fft(array);
        assertEquals(10.0, result[0].re(), DELTA);
        assertEquals(0.0, result[0].im(), DELTA);
        assertEquals(-2.0, result[1].re(), DELTA);
        assertEquals(2.0, result[1].im(), DELTA);
        assertEquals(-2.0, result[2].re(), DELTA);
        assertEquals(0.0, result[2].im(), DELTA);
        assertEquals(-2.0, result[3].re(), DELTA);
        assertEquals(-2.0, result[3].im(), DELTA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void fft_not_a_pow_of_2() {
        Complex[] array = new Complex[] {new Complex(1,0), new Complex(2, 0), new Complex(3, 0)};
        FFT.fft(array);
    }


}