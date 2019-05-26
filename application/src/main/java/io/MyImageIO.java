package io;

import util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author      Eeva-Maria Laiho <eeva-maria.laiho@helsinki.fi>
 * @version     0.3
 * @since       0.3
 */
public class MyImageIO {

    private ArrayList<int[][]> pixels;
    private ArrayList<double[][]> greens;
    private int width;
    private int height;

    /**
     * Pixel width of the image
     */
    public int getWidth() { return width; }
    /**
     * Pixel height of the image
     */
    public int getHeight() { return this.height; }
    /**
     * @return Pixels as 3D array (width x height x image id)
     */
    public ArrayList<int[][]> getPixels() { return pixels; }
    /**
     * @return Green channels as 3D array (width x height x image id)
     */
    public ArrayList<double[][]> getGreens() { return greens; }
    /**
     * Construct the class
     */
    public MyImageIO() {}

    /**
     * Load the images and store them internally.
     * The green channels can be accessed through accessor. 
     * @param paths Paths to the images
     * @throws IOException When image cannot be loaded
     */
    public void LoadImages(String[] paths) throws IOException, IllegalArgumentException {
        int width = -1;
        int height = -1;

        for (String path: paths) {
            MyImage image = new MyImage(path);
            if (width < 0) {
                width = image.getWidth();
                height = image.getHeight();
            }
            else if (width != image.getWidth() || height != image.getHeight())
                throw new IllegalArgumentException("The images need to be of same size");

            // Loop through the pixels and store in pixels array, extract green channel
            int[][] px = new int[width][height];
            int[][] gr = new int[width][height];
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int idx = x + y*width;
                    int rgb = image.getPixels()[idx];
                    px[x][y] = rgb;
                    gr[x][y] = rgb >> 8 & 0xff;  // https://stackoverflow.com/questions/16698372/isolating-red-green-blue-channel-in-java-bufferedimage
                }
            }
            pixels.add(px);
            greens.add(gr);
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

        // Copy correct pixels to the image
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, pixels[y*width + x]);
            }
        }

        // Create and write image file
        File outputfile = new File(path);
        String extension = path.substring(path.lastIndexOf(".") + 1);
        ImageIO.write(image, extension, outputfile);
    }
}