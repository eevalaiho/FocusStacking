package io;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.ArrayList;

import java.io.File;
import static org.junit.Assert.*;

public class MyImageIOTest {

    private static final double DELTA = 1e-15;
    private static MyImageIO imageIO;

    @Before
    public void before() {
        imageIO = new MyImageIO();
        try {
            imageIO.LoadImages(new String[]{new File("src/test/resources/rgb.png").getAbsolutePath()});
        }
        catch(Exception e){
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
        assertTrue(imageIO.getPixels() instanceof ArrayList);
        assertTrue(imageIO.getPixels().get(0) instanceof int[][]);

        ArrayList<int[][]> pixels = imageIO.getPixels();
        assertEquals(1, pixels.size());
        assertEquals(6, ((int[][]) pixels.get(0)).length);
        assertEquals(4, ((int[][]) pixels.get(0))[0].length);

        int r = (255<<24) | (255<<16);
        int g = (255<<24) | (255<<8);
        int b = (255<<24) | 255;
        int a = (255<<24) | (255<<16) | (255<<8) | 255;
        assertArrayEquals(new int[][] {{r,g,b,a},{r,g,b,a},{r,g,b,a},{r,g,b,a},{r,g,b,a},{r,g,b,a}}, (int[][]) pixels.get(0));
    }

    @Test
    public void getGreens() {
        assertTrue(imageIO.getGreens() instanceof ArrayList);
        assertTrue(imageIO.getGreens().get(0) instanceof double[][]);

        ArrayList<double[][]> greens = imageIO.getGreens();
        assertEquals(1, greens.size());
        assertEquals(6, ((double[][]) greens.get(0)).length);
        assertEquals(4, ((double[][]) greens.get(0))[0].length);
        assertArrayEquals(new double[][] {{0,255,0,255},{0,255,0,255},{0,255,0,255},{0,255,0,255},{0,255,0,255},{0,255,0,255}}, (double[][]) greens.get(0));
    }

    @Test
    public void loadImages() {
        assertTrue(imageIO != null);
     }

    @Test
    public void saveImage() {
    }
}