import java.io.IOException;

import domain.FFT;
import domain.SlidingWindow;
import io.RGB;
import io.MyImageIO;
import util.Complex;
import util.MyArrayList;
import static util.Util.*;

public class FocusStacking {

    private int windowSize = 16;
    private RGB color = RGB.GREEN;
    int count = 0;
    int width = 0;
    int height = 0;
    private MyImageIO imageIO = null;
    MyArrayList<double[][]> channels = null;
    int[][] maxL2Norm_indexes = null;


    public FocusStacking(RGB color, int windowSize) {
        this.windowSize = windowSize;
        this.color = color;
    }

    public void loadImages(String[] fileNames) throws IOException {
        this.imageIO = new MyImageIO();
        this.imageIO.loadImages(fileNames, this.color);

        // Get the green chathis.imageIO.loadImages(fileNames, this.color);nnels
        this.channels = imageIO.getChannels();

        // Normalize the channels
        for (int k = 0; k < this.channels.getSize(); k++)
            channels.set(k, normalize(channels.get(k), 0, 255));

    }

    public void computeSharpestPixels() {

        int width = this.imageIO.getWidth();
        int height = this.imageIO.getHeight();
        int n = this.channels.getSize();

        maxL2Norm_indexes = new int[width][height];
        SlidingWindow slidingWindow = new SlidingWindow(windowSize, width, height);
        while(slidingWindow.hasNext()) {

            double maxL2Norm = 0;
            for (int k = 0; k < n; k++) {

                // Get the double-mirrored window ()
                double[][] window = slidingWindow.getDoubleMirroredWindow(channels.get(k));
                int i = slidingWindow.getX();
                int j = slidingWindow.getY();

                // Compute FFT
                Complex[][] fft = FFT.fft2(window);

                // Compute L^2 norm of this window and compare
                double norm = l2Norm(fft);
                //double l2Norm = MaxL2Norm(fft);
                if (norm > maxL2Norm) {
                    maxL2Norm = norm;
                    maxL2Norm_indexes[i][j] = k;
                }
            }

            // Next window
            slidingWindow.moveNext();
        }

    }

    public void saveImage(String outputFileName) throws IOException {

        int width = maxL2Norm_indexes.length;
        int height = maxL2Norm_indexes[0].length;

        // Create an array of the sharpest pixels
        int[] sharpestPixels = new int[(width+1)*(height+1)];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                sharpestPixels[i+j*width] = imageIO.getPixels().get(maxL2Norm_indexes[i][j])[i][j];
            }
        }

        // Save the image
        MyImageIO.saveImage(sharpestPixels, width, height, outputFileName);

    }

    /**
     * Prints out a 2D int array of the sharpest pixel indexes
     * Utility method for debugging
     */
    public void printSharpestPixelIndexes() {
        for (int i = 0; i < maxL2Norm_indexes.length; i++) {
            System.out.print(maxL2Norm_indexes[i][0]);
            for (int j = 1; j < maxL2Norm_indexes[0].length; j++) {
                System.out.print(","+maxL2Norm_indexes[i][j]);
            }
            System.out.println("");
        }
    }
}
