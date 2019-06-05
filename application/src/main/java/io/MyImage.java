package io;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author      Eeva-Maria Laiho <eeva-maria.laiho@helsinki.fi>
 * @version     0.3
 * @since       0.3
 */
public final class MyImage {

    private int imageType;
    private int[] pixels;
    private int width;
    private int height;

    /**
     * Image type
     */
    public int getImageType() { return imageType; }
    /**
     * Pixel width of the image
     */
    public int getWidth() { return width; }
    /**
     * Pixel height of the image
     */
    public int getHeight() { return this.height; }
    /**
     * Get pixels of an image
     * @return Pixels as an integer array
     */
    public int[] getPixels() { return pixels; }
    /**
     * Load the image 
     * @param path
     * @throws Exception
     */
    public MyImage(String path) throws IOException {
        // Load the image
        BufferedImage image = null;
        image = ImageIO.read(new File(path));
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.imageType = image.getType();

        // Get pixels and green pixels
        this.pixels = new int[this.width*this.height];
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                int rgb = image.getRGB(x,y);
                int idx = x + this.width*y;
                this.pixels[idx] = rgb;
            }
        }
    }
    /**
     * Get single pixel from the image
     * @param x x-coodrdinate
     * @param y -coordinate
     * @return Pixel value as int
     */
    public int GetPixel(int x, int y) {
        int idx = x + this.width*y;
        return this.pixels[idx];
    }
}
