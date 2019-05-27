package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ComplexTest {

    private static final double DELTA = 1e-15;

    @Test
    public void re() {
        Complex cplx = new Complex(0.5, 0);
        assertEquals(0.5, cplx.re(), DELTA);

        cplx = new Complex(-0.5, 0);
        assertEquals(-0.5, cplx.re(), DELTA);
    }

    @Test
    public void im() {
        Complex cplx = new Complex(0, 0.5);
        assertEquals(0.5, cplx.im(), DELTA);

        cplx = new Complex(0, -0.5);
        assertEquals(-0.5, cplx.im(), DELTA);
    }

    @Test
    public void abs() {
        Complex cplx = new Complex(3.0, 4.0);
        assertEquals(5.0, cplx.abs(), DELTA);

        cplx = new Complex(-3.0, 4.0);
        assertEquals(5.0, cplx.abs(), DELTA);

        cplx = new Complex(3.0, -4.0);
        assertEquals(5.0, cplx.abs(), DELTA);
    }
}