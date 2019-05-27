package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class MathTest {

    private static final double DELTA = 1e-15;

    @Test
    public void sqrt() {
        Double val = util.Math.sqrt(4);
        assertEquals(2, val, DELTA);

        val = util.Math.sqrt(-4);
        assertEquals(true, Double.isNaN(val));
    }

    @Test
    public void abs() {
    }

    @Test
    public void hypot() {
    }
}