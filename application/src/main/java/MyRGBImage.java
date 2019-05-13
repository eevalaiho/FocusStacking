import org.apache.commons.math3.complex.Complex;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.File;

public final class MyRGBImage {

    private int width;
    public int getWidth() {
        return this.width;
    }
    private int height;
    public int getHeight() {
        return this.height;
    }
    private int[] pixels;
    public int[] getPixels() {
        return this.pixels;
    }
    public void setPixels(int[] value) {
        this.pixels = value;
    }

    private double[][] green;
    public double[][] getGreen() {
        return this.green;
    }

    private BufferedImage image;

    private void MyRGBImage() {}

    public MyRGBImage(String path) throws Exception {

        this.image = ImageIO.read(MyRGBImage.class.getResourceAsStream(path));
        this.width = this.image.getWidth();
        this.height = this.image.getHeight();

        // Get pixels
        this.pixels = new int[this.width * this.height];
        this.image.getRGB(0, 0, this.width, this.height, this.pixels, 0, this.width);

        // Get color channels
        this.green = new double[this.width][this.height];
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                Color c = new Color(this.pixels[i + this.width*j]);
                this.green[i][j] = (double) c.getGreen();
            }
        }

        Complex num = new Complex(1, 2);
    }

    public MyRGBImage (int width, int height, int[] pixels) {

        this.width = width;
        this.height = height;
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        final int[] a = ((DataBufferInt) this.image.getRaster().getDataBuffer() ).getData();
        System.arraycopy(pixels, 0, a, 0, pixels.length);
    }

    public void SaveAs(String path) throws Exception {
        File outputfile = new File(path);
        ImageIO.write(this.image, "jpg", outputfile);
        System.out.println("Wrote " + path);
    }
}