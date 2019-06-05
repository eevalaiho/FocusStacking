import java.io.IOException;

import domain.FFT;
import domain.SlidingWindow;
import io.MyImageIO;
import util.Complex;
import util.MyArrayList;
import static util.Util.*;

public class FocusStacking {

    private int windowSize = 16;

    public FocusStacking(int windowSize) {
        this.windowSize = windowSize;
    }

    public void Stack(String[] paths, String outputPath) throws IOException {

        // Load and preprocess images
        MyImageIO imageIO = new MyImageIO();
        imageIO.LoadImages(paths);
        int width = imageIO.getWidth();
        int height = imageIO.getHeight();

        // Get the green channels
        MyArrayList<double[][]> greens = imageIO.getGreens();  // <-- make more effective ??

        // Normalize the channels
        for (int k = 0; k < paths.length; k++)
            greens.set(k, normalize(greens.get(k), 0, 255));

        // Figure out the sharpest pixels

        // Array to store image indexes
        int[][] maxL2Norm_indexes = new int[width][height];

        // Create a sliding window
        SlidingWindow slidingWindow = new SlidingWindow(windowSize, width, height);
        while(slidingWindow.hasNext()) {

            double maxL2Norm = 0;
            for (int k = 0; k < paths.length; k++) {

                // Get the double-mirrored window ()
                double[][] window = slidingWindow.getDoubleMirroredWindow(greens.get(k));
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

        // Print the array
        printArray(maxL2Norm_indexes);

        // Create an array of the sharpest pixels
        int[] sharpestPixels = new int[(width+1)*(height+1)];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                sharpestPixels[i+j*width] = imageIO.getPixels().get(maxL2Norm_indexes[i][j])[i][j];
            }
        }

        // Save the image
        MyImageIO.SaveImage(sharpestPixels, width, height, outputPath);

    }

    /**
     * Prints out a 2D int array
     * Utility method for debugging
     * @param array The array
     */
    public void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i][0]);
            for (int j = 1; j < array[0].length; j++) {
                System.out.print(","+array[i][j]);
            }
            System.out.println("");
        }
    }
}
