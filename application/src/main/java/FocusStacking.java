import domain.FFT;
import domain.SlidingWindow;
import io.MyIOException;
import io.MyImageIO;
import util.Complex;
import util.MyArrayList;

import static util.Util.*;

public class FocusStacking {

    private final int windowSize = 16;
    private final double highPassThreshold = 1.0;

    public void FocusStacking() { }

    public void Stack(String[] paths, String outputPath) throws MyIOException {

        // Load and preprocess images
        MyImageIO imageIO = new MyImageIO();
        imageIO.LoadImages(paths);

        // Get the green channels as 3D array (width x height x image id)
        MyArrayList<double[][]> greens = imageIO.getGreens();
        int width = imageIO.getWidth();
        int height = imageIO.getHeight();

        // Normalize the channels
        for (int k = 0; k < paths.length; k++) {
            greens.set(k, normalize((double[][]) greens.get(k)));
        }

        // Figure out the sharpest pixels
        double[][] maxL2Norms = new double[width][height];
        int[][] maxL2Norm_indexes = new int[width][height];
        for (int k = 0; k < paths.length; k++) {

            // Sliding window
            SlidingWindow slidingWindow = new SlidingWindow(greens.get(k), windowSize);
            while(slidingWindow.hasNext()) {

                // Get window
                double[][] window = slidingWindow.getWindow();
                int i = slidingWindow.getX();
                int j = slidingWindow.getY();

                // Compute FFT
                Complex[][] fft = FFT.fft2(window);

                // Compute max L^2 norm
                double maxL2Norm = maxL2Norm(fft);
                if(maxL2Norm > (maxL2Norms[i][j])) {
                    maxL2Norms[i][j] = maxL2Norm;
                    maxL2Norm_indexes[i][j] = k;
                }

                // Next window
                slidingWindow.moveNext();
            }
        }

        // Create an array of the sharpest pixels
        int[] sharpestPixels = new int[(width+1)*(height+1)];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                sharpestPixels[i+j*width] = imageIO.getPixels().get(maxL2Norm_indexes[i][j])[i][j];
            }
        }

        // Save the image
        // public static void SaveImage(int[] pixels, int width, int height, String path) throws MyIOException {
        MyImageIO.SaveImage(sharpestPixels, width, height, outputPath);
    }
}
