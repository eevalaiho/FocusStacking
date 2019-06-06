package io;

import org.junit.Before;
import org.junit.Test;
import util.MyArrayList;

import java.io.File;

import static org.junit.Assert.*;

public class MyImageIOTest {

    private static final double DELTA = 1e-15;
    private static MyImageIO imageIO;

    @Before
    public void before() {
        try {
            String path = new File(".").getCanonicalPath() + "/src/test/resources/";
            imageIO = new MyImageIO(path);
            imageIO.loadImages(new String[]{"rgb.png"});
        } catch (Exception e) {
            fail(e.toString());
        }
    }

    @Test
    public void getWidth() {
        assertTrue(imageIO != null);
        assertEquals(6, imageIO.getWidth());
    }

    @Test
    public void getHeight() {
        assertTrue(imageIO != null);
        assertEquals(4, imageIO.getHeight());
    }

    @Test
    public void getPixels() {
        assertTrue(imageIO.getPixels() instanceof MyArrayList);
        assertTrue(imageIO.getPixels().get(0) instanceof int[][]);

        MyArrayList<int[][]> pixels = imageIO.getPixels();
        assertEquals(1, pixels.getSize());
        assertEquals(6, ((int[][]) pixels.get(0)).length);
        assertEquals(4, ((int[][]) pixels.get(0))[0].length);

        int r = (255 << 24) | (255 << 16);
        int g = (255 << 24) | (255 << 8);
        int b = (255 << 24) | 255;
        int a = (255 << 24) | (255 << 16) | (255 << 8) | 255;
        assertArrayEquals(new int[][]{{r, g, b, a}, {r, g, b, a}, {r, g, b, a}, {r, g, b, a}, {r, g, b, a}, {r, g, b, a}}, (int[][]) pixels.get(0));
    }

    @Test
    public void getGreens() {
        assertTrue(imageIO.getGreens() instanceof MyArrayList);
        assertTrue(imageIO.getGreens().get(0) instanceof double[][]);

        MyArrayList<double[][]> greens = imageIO.getGreens();
        assertEquals(1, greens.getSize());
        assertEquals(6, ((double[][]) greens.get(0)).length);
        assertEquals(4, ((double[][]) greens.get(0))[0].length);
        assertArrayEquals(new double[][]{{0, 255, 0, 255}, {0, 255, 0, 255}, {0, 255, 0, 255}, {0, 255, 0, 255}, {0, 255, 0, 255}, {0, 255, 0, 255}}, (double[][]) greens.get(0));
    }

    @Test
    public void loadImages() {
        assertTrue(imageIO != null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void loadImages_IllegalArgumentException() throws IllegalArgumentException {
        try {
            String path = new File(".").getCanonicalPath() + "/src/test/resources/";
            MyImageIO imageIO = new MyImageIO(path);
            imageIO.loadImages(new String[]{"rgb.png","rgb2.png"});
        }
        catch (IllegalArgumentException e1) {
            throw e1;
        }
        catch (Exception e2) {
            // Do nothing
        }
    }

    @Test
    public void saveImage() {
        int r = (255 << 24) | (255 << 16);
        int g = (255 << 24) | (255 << 8);
        int b = (255 << 24) | 255;
        int a = (255 << 24) | (255 << 16) | (255 << 8) | 255;
        int[] pixels = new int[]{r, r, r, r, r, r, g, g, g, g, g, g, b, b, b, b, b, b, a, a, a, a, a, a};

        try {
            MyImageIO.saveImage(pixels, 6, 4, "./src/test/resources/", "rgb.png");
        } catch (Exception e) {
            fail(e.toString());
        }

        assertTrue((new File("./src/test/resources/", "rgb.png")).exists());
    }

}
