import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author      Eeva-Maria Laiho <eeva-maria.laiho@helsinki.fi>
 * @version     0.3
 * @since       0.3
 */
public final class MyImage {

    private int imageType;
    /**
     * Image type
     */
    public int getImageType() { return imageType; }

    private int width;
    /**
     * Pixel width of the image
     */
    public int getWidth() { return width; }

    private int height;
    /**
     * Pixel height of the image
     */
    public int getHeight() { return this.height; }

    private int[] pixels;
    /**
     * Get pixels of an image
     * @return Pixels as an integer array
     */
    public int[] getPixels() { return pixels; }

    //private int[] greenChannel = null;
    /**
     * Green channel of the image as a 2D-array
     */
    //public int[] getGreenChannel() {return this.greenChannel;}

    /**
     * Load the image 
     * @param path
     * @throws Exception
     */
    public MyImage(String path) throws IOException {
        // Load the image
        BufferedImage image = ImageIO.read(MyImage.class.getResourceAsStream(path));
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.imageType = image.getType();

        // Get pixels and green pixels
        this.pixels = new int[this.width*this.height];
        //this.greenChannel = new int[this.pixels.length];        
        for (int x = 0; x < this.getWidth(); x++) {
            for (int y = 0; y < this.getHeight(); y++) {
                int rgb = image.getRGB(x,y);
                int idx = x + this.width*y;
                this.pixels[idx] = rgb;
                //this.greenChannel[idx] = rgb >> 8 & 0xff;  // https://stackoverflow.com/questions/16698372/isolating-red-green-blue-channel-in-java-bufferedimage
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
