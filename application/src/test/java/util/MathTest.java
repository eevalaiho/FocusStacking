package util;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class MathTest {

    private static final double DELTA = 1e-15;

    @Test
    public void construct() throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor ctor = Math.class.getDeclaredConstructors()[0];
        ctor.setAccessible(true);
        Math c = (Math)ctor.newInstance();
    }

    @Test
    public void sqrt() {
        Double val = util.Math.sqrt(4);
        assertEquals(2, val, DELTA);

        val = util.Math.sqrt(-4);
        assertEquals(true, Double.isNaN(val));

        val = util.Math.sqrt(2.0);
        assertEquals(1.414213562373095, val, DELTA);
    }

    @Test
    public void abs() {
        Double val = util.Math.abs(-1.0);
        assertEquals(val, 1.0, DELTA);

        val = util.Math.abs(1.0);
        assertEquals(val, 1.0, DELTA);
    }

    @Test
    public void hypot() {
        Double val = util.Math.hypot(3.0, 4.0);
        assertEquals(5.0, val, DELTA);
    }
}