import io.RGB;
import org.junit.Test;
import testutilities.TestUtilities;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class FocusStackingTest {

    @Test
    public void focusStacking() throws NoSuchFieldException, IllegalAccessException {
        FocusStacking fs = new FocusStacking(RGB.RED, 2);
        Integer expected = 2;
        assertEquals(expected, TestUtilities.getPrivateField("windowSize", fs));
        RGB expected2 = RGB.RED;
        assertEquals(expected2, TestUtilities.getPrivateField("color", fs));
    }

    @Test
    public void loadImages() throws IOException {
        FocusStacking fs = new FocusStacking(RGB.RED, 2);
        fs.loadImages(new String[] {"../../test/resources/rgb.png"});
        assertEquals(1, fs.channels.getSize());
    }

    @Test(expected = IOException.class)
    public void loadImages_IOException() throws IOException {
        FocusStacking fs = new FocusStacking(RGB.RED, 2);
        fs.loadImages(new String[] {"non-existing.png"});
    }

    @Test
    public void computeSharpestPixels() throws IOException {
        FocusStacking fs = new FocusStacking(RGB.RED, 2);
        fs.loadImages(new String[] {"../../test/resources/rgb.png"});
        fs.computeSharpestPixels();
    }

    @Test
    public void saveImage() throws IOException {
        FocusStacking fs = new FocusStacking(RGB.RED, 2);
        fs.loadImages(new String[] {"../../test/resources/rgb.png"});
        fs.computeSharpestPixels();
        fs.saveImage("../../test/resources/test_out_rgb.png");
        assertTrue((new File("./src/test/resources/", "test_out_rgb.png")).exists());
    }

    @Test
    public void printSharpestPixelIndexes() throws IOException {
        FocusStacking fs = new FocusStacking(RGB.RED, 2);
        fs.loadImages(new String[] {"../../test/resources/rgb.png"});
        fs.computeSharpestPixels();

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        fs.printSharpestPixelIndexes();
        assertEquals("0,0,0,0,0,0\n" +
                "0,0,0,0,0,0\n" +
                "0,0,0,0,0,0\n" +
                "0,0,0,0,0,0\n", outContent.toString());

        System.setOut(System.out);
    }
}