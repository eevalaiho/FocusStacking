package io;

import org.junit.Test;

import static org.junit.Assert.*;

public class RGBTest {

    @Test
    public void getColorValue() {
        int color = 0xffffffff;
        assertEquals(255, RGB.getChannelValue(color, RGB.RED));
        assertEquals(255, RGB.getChannelValue(color, RGB.GREEN));
        assertEquals(255, RGB.getChannelValue(color, RGB.BLUE));

        color = 0x01010101;
        assertEquals(1, RGB.getChannelValue(color, RGB.RED));
        assertEquals(1, RGB.getChannelValue(color, RGB.GREEN));
        assertEquals(1, RGB.getChannelValue(color, RGB.BLUE));
    }

    @Test
    public void parse() {
        assertEquals(RGB.RED, RGB.parse("RED"));
        assertEquals(RGB.GREEN, RGB.parse("GREEN"));
        assertEquals(RGB.BLUE, RGB.parse("BLUE"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void parse_IllegalArgumentException() {
        RGB.parse("Illegal");
    }
}