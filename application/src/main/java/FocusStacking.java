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

        // Compute the sharpest pixels
        double[][] maxL2Norms = new double[width][height];
        for (int k = 0; k < paths.length; k++) {
            // Sliding window
            SlidingWindow slidingWindow = new SlidingWindow(greens.get(k), windowSize);
            while(slidingWindow.hasNext()) {
                // Get window
                double[][] window = slidingWindow.getWindow();
                // Compute FFT in-place
                Complex[][] fft = FFT.fft2(window);
                // Max L^2 norm

                // Next window
                slidingWindow.moveNext();
            }
        }



        /*
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

                double[] norms = new double[paths.length];
                for (int k = 0; k < paths.length; k++) {

                    // Compute FFT
                    double[][] window = FFT.ComputeFFT((double[][]) greens.get(k), i, j, windowSize);

                    // High-pass-filter
                    window = highPassFilter(window, highPassThreshold);

                    // L^2 norm
                    norms[k] = l2norm(window);
                }

                // Set the max L^2 norm pixel id
                maxL2Norms[i][j] = maxValue(norms)[1];
            }
        }
        */

        // Save pixels


    }
}
