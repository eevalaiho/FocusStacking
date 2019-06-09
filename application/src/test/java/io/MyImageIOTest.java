package io;

import org.apache.commons.math3.stat.inference.TestUtils;
import org.junit.Before;
import org.junit.Test;
import testutilities.TestUtilities;
import util.MyArrayList;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.*;

public class MyImageIOTest {

    private static final double DELTA = 1e-15;
    private static MyImageIO imageIO;

    @Before
    public void before() throws IOException {

        File outputfile = new File("./src/test/resources/rgb.png");
        File outputfile2 = new File("./src/test/resources/rgb2.png");

        int r = (255 << 24) | (255 << 16);
        int g = (255 << 24) | (255 << 8);
        int b = (255 << 24) | 255;
        int a = (255 << 24) | (255 << 16) | (255 << 8) | 255;
        int[] pixels = new int[]{r, r, r, r, r, r, g, g, g, g, g, g, b, b, b, b, b, b, a, a, a, a, a, a};

        int width = 6;
        int height = 4;
        BufferedImage image = new BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        BufferedImage image2 = new BufferedImage(3, height, java.awt.image.BufferedImage.TYPE_INT_ARGB);

        // Copy correct pixels to the image
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, pixels[y * width + x]);
                if (x <= 2)
                    image2.setRGB(x, y, pixels[y * width + x]);
            }
            if (x == 2)
                ImageIO.write(image2, "png", outputfile2);
        }

        ImageIO.write(image, "png", outputfile);

        imageIO = new MyImageIO("./src/test/resources/");
        imageIO.loadImages(new String[]{"rgb.png"}, RGB.GREEN);
    }

    @Test
    public void construct() {
        try {
            MyImageIO imageIO = new MyImageIO("./src/test/resources/");
            assertTrue(imageIO != null);
            assertEquals("./src/test/resources/", TestUtilities.getPrivateField("resourcesRoot", imageIO));

            MyImageIO imageIO2 = new MyImageIO();
            assertTrue(imageIO2 != null);
            assertEquals(new File(".").getCanonicalPath() + "/src/main/resources/", TestUtilities.getPrivateField("resourcesRoot", imageIO2));

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
        assertTrue(imageIO.getChannels() instanceof MyArrayList);
        assertTrue(imageIO.getChannels().get(0) instanceof double[][]);

        MyArrayList<double[][]> greens = imageIO.getChannels();
        assertEquals(1, greens.getSize());
        assertEquals(6, ((double[][]) greens.get(0)).length);
        assertEquals(4, ((double[][]) greens.get(0))[0].length);
        assertArrayEquals(new double[][]{{0, 255, 0, 255}, {0, 255, 0, 255}, {0, 255, 0, 255}, {0, 255, 0, 255}, {0, 255, 0, 255}, {0, 255, 0, 255}}, (double[][]) greens.get(0));
    }

    @Test
    public void loadImages() throws IOException {
        String path = new File(".").getCanonicalPath() + "/src/test/resources/";
        MyImageIO imageIO = new MyImageIO(path);
        imageIO.loadImages(new String[]{"rgb.png", "rgb.png"}, RGB.GREEN);
        assertEquals(2, imageIO.getChannels().getSize());
    }

    @Test(expected = IllegalArgumentException.class)
    public void loadImages_NotSameSizeImages() throws IllegalArgumentException, IOException {
        String path = new File(".").getCanonicalPath() + "/src/test/resources/";
        MyImageIO imageIO = new MyImageIO(path);
        imageIO.loadImages(new String[]{"rgb.png", "rgb2.png"}, RGB.GREEN);
    }

    @Test(expected = IllegalArgumentException.class)
    public void loadImages_NoFiles() throws IllegalArgumentException, IOException {
        MyImageIO imageIO = new MyImageIO();
        imageIO.loadImages(null, RGB.GREEN);
    }

    @Test
    public void saveImage() throws IOException {
        int r = (255 << 24) | (255 << 16);
        int g = (255 << 24) | (255 << 8);
        int b = (255 << 24) | 255;
        int a = (255 << 24) | (255 << 16) | (255 << 8) | 255;
        int[] pixels = new int[]{r, r, r, r, r, r, g, g, g, g, g, g, b, b, b, b, b, b, a, a, a, a, a, a};

        String path = new File(".").getCanonicalPath() + "/src/test/resources/";
        MyImageIO imageIO = new MyImageIO(path);
        MyImageIO.saveImage(pixels, 6, 4, "../../test/resources/rgb.png");
        assertTrue((new File("./src/test/resources/", "rgb.png")).exists());
    }

    @Test
    public void getDefaultResourceRoot() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        MyImageIO imageIO = new MyImageIO();
        Object value = TestUtilities.callPrivateMethod(MyImageIO.class, new Class[] { String.class }, "getDefaultResourceRoot", imageIO, new Object[] {"\u0000"});
        assertEquals("./src/main/resources/", value);
    }

}
