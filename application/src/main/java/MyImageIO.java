import org.apache.commons.math3.complex.Complex;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author      Eeva-Maria Laiho <eeva-maria.laiho@helsinki.fi>
 * @version     0.3
 * @since       0.3
 */
public class MyImageIO {

    private ArrayList<int[]> pixels;

    private int[][] greenChannels;
    /**
     * Green channels of the images
     * @return Green channels as 2D array (image id x pixel count)
     */
    public int[][] getGreenChannels() { return greenChannels; }

    /**
     * Construct the class
     */
    public MyImageIO() {}

    /**
     * Load a bunch of images as MyImage objects
     * @param paths Paths to the images
     * @throws IOException When image cannot be loaded
     */
    public void LoadImages(String[] paths) throws IOException {

        pixels = new ArrayList<int[]>(paths.length);
        for (int i = 0; i < paths.length; i++) {
            // Construct an image object
            MyImage image = new MyImage(paths[0]);
            // Store pixel values - we need them later
            pixels.add(image.getPixels());
            // Extract green channels
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    int idx = x + image.getWidth()*y;
                    int rgb = pixels.get(i)[idx];
                    this.greenChannels[idx][i] = rgb >> 8 & 0xff;  // https://stackoverflow.com/questions/16698372/isolating-red-green-blue-channel-in-java-bufferedimage
                }
            }
        }
    }

    /**
     * Create and image object from pixel values and save to disk
     * @param pixels Pixels in integer format
     * @param width Width of the image
     * @param height Height of the image
     * @param path PAth to save the image to
     * @throws IOException
     */
    public static void SaveImage(int[] pixels, int width, int height, String path) throws IOException {
        // Create the image object
        BufferedImage image = new BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_ARGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, pixels[y*width + x]);
            }
        }
        // Create and write the file
        File outputfile = new File(path);
        String extension = path.substring(path.lastIndexOf(".") + 1);
        ImageIO.write(image, extension, outputfile);
    }
}