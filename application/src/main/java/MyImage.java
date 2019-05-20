import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author      Eeva-Maria Laiho <eeva-maria.laiho@helsinki.fi>
 * @version     0.3
 * @since       0.3
 */
public class MyImage {

    private BufferedImage image;

    /**
     * Pixel width of the image
     */
    public int getWidth() { return this.image.getWidth(); }

    /**
     * Pixel height of the image
     */
    public int getHeight() { return this.image.getHeight(); }

    private double[][] green;
    /**
     * Green channel of the image as a 2D-array
     */
    public double[][] getGreen() {
        return this.green;
    }

    private void MyImage() {

        int width = this.image.getWidth();
        int height = this.image.getHeight();

        // Get pixels
        int[] pixels = new int[width * height];
        this.image.getRGB(0, 0, width, height, pixels, 0, width);

        // Get green channel
        this.green = new double[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int rgb = this.image.getRGB(i, j);
                this.green[i][j] = rgb>>8 & 0xff;  // https://stackoverflow.com/questions/16698372/isolating-red-green-blue-channel-in-java-bufferedimage
            }
        }
    }

    public MyImage(String path) throws Exception {
        this.image = ImageIO.read(MyImage.class.getResourceAsStream(path));

        // Call default constructor
        MyImage();
    }

    public MyImage(String path, int width, int height) throws Exception {
        /**
         *
         */

        this.image = ImageIO.read(MyImage.class.getResourceAsStream(path));
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();
        // Call default constructor
        MyImage();
    }



    public int GetPixel(int i, int j) {
        return this.image.getRGB(i, j);
    }
}
