import org.apache.commons.math3.stat.Frequency;
import org.jtransforms.fft.DoubleFFT_2D;

import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        System.out.println("Started");
        FocusStack();
        System.out.println("Finished");
    }

    public static void FocusStack() {

        //String[] paths = new String[] {"fly1_1024.jpg", "fly2_1024.jpg"};
        //String[] paths = new String[] {"fly1_256.jpg", "fly2_256.jpg"};
        //String[] paths = new String[] {"bark-1.jpg", "bark-2.jpg", "bark-3.jpg", "bark-4.jpg", "bark-5.jpg", "bark-6.jpg", "bark-7.jpg", "bark-8.jpg", "bark-9.jpg", "bark-10.jpg", "bark-11.jpg", "bark-12.jpg"};
        String[] paths = new String[] {"bark-1.jpg", "bark-5.jpg", "bark-11.jpg"};
        MyRGBImage[] rgbs = new MyRGBImage[paths.length];

        int n = paths.length;
        double highpass_threshold = 1.0;


        //int m = 16; // convolution size
        //int threshold = 500;
        int m = 32; // convolution size
        int threshold = 650;
        //int m = 64; // convolution size
        //int threshold = 8500;

        /* Load images */
        for (int k = 0; k < n; k++) {
            /* Load image */
            try {
                rgbs[k] = new MyRGBImage(paths[k]);
            } catch (Exception e) {
                System.out.println("Exception: " + e.getMessage());
            }
            if (rgbs[k] == null)
                throw new NullPointerException("Image is null.");
        }

        /* Find maximum L2 norm for each pixel*/
        int width = rgbs[0].getWidth();
        int height = rgbs[0].getHeight();
        double[][][] maxL2Norms = new double[width][height][paths.length];
        for (int k = 0; k < n; k++) {

            /* Extract green channel */
            double[][] green = rgbs[k].getGreen();

            /* Normalize to [0,1] */
            double max = maxValue(green)[0];
            double min = minValue(green)[0];
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < height; j++) {
                    green[i][j] = (green[i][j] - min) / (max - min);
                }
            }

            boolean saved = false;
            /* Get convolution and find the maximum L^2 norm*/
            for (int i = 0; i < width-m; i++) {
                for (int j = 0; j < height-m; j++) {

                    /* Slide window */
                    double[][] convolution = sliceAndExtend(green, i, j, m);
                    //printHead(convolution, 2*m);

                    /* Compute Fourier transform */
                    DoubleFFT_2D fft = new DoubleFFT_2D(m,m);
                    fft.realForward(convolution);

                    /* High-pass filter */
                    //highPassFilter(convolution, highpass_threshold);

                    /* L^2-norm */
                    double[][] norm = l2norm(convolution);

                    /* Find the maximum L^2 norm */
                    maxL2Norms[i][j][k] = maxValue(norm)[0];
                }
            }
            System.out.println("Computed convolutions for '" + paths[k] + "'");
        }

        SortedMap<Integer, Integer> freq = frequency(maxL2Norms);
        for (int key: freq.keySet()){
            int value = freq.get(key);
            System.out.print(key + ": " + value + ", ");
        }
        System.out.println();

        // Count pixels taken from each image
        HashMap<String, Integer> counter = new HashMap<String, Integer>();
        for(int k = 0; k < paths.length; k++)
            counter.put("" + k, k);

        // Construct new pixels
        int[] newPixels = rgbs[0].getPixels();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                double max = maxL2Norms[i][j][0];
                int argmax = 0;
                for (int k = 1; k < n; k++) {
                    double val = maxL2Norms[i][j][k];
                    if (val > threshold && val > max) {
                        max = val;
                        argmax = k;
                    }
                }
                int idx = i + width*j;
                newPixels[idx] = rgbs[argmax].getPixels()[idx];
                counter.put("" + argmax, counter.get("" + argmax) + 1);
            }
        }

        for (String key: counter.keySet()){
            String value = counter.get(key).toString();
            System.out.print(key + ": " + value + ", ");
        }
        System.out.println();

        MyRGBImage newImage = new MyRGBImage(width, height, newPixels);
        try {
            newImage.SaveAs(String.format("%sx%sx%s_%s_%s", n, m, m, threshold, paths[0]));
        }
        catch(Exception ex) {

        }

    }

    private static double[] maxValue(double[][] numbers) {
        int width = numbers.length;
        int height = numbers[0].length;
        int[] argmax = {0, 0};
        double max = numbers[0][0];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (numbers[i][j] > max) {
                    max = numbers[i][j];
                    argmax = new int[] {i, j};
                }
            }
        }
        return new double[] {max, argmax[0], argmax[1]};
    }

    private static double[] minValue(double[][] numbers) {
        int width = numbers.length;
        int height = numbers[0].length;
        int[] argmin = {0, 0};
        double min = numbers[0][0];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (numbers[i][j] < min) {
                    min = numbers[i][j];
                    argmin = new int[] {i, j};
                }
            }
        }
        return new double[] {min, argmin[0], argmin[1]};
    }

    private static void printHead(double[][] table, int n) {
        double max = maxValue(table)[0];
        double min = minValue(table)[0];
        System.out.printf("\nlength: (%s, %s), min: %s, max: %s\n", table[0].length, table[1].length, min, max);
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++) {
                System.out.print(String.format("%20s", table[i][j]));
            }
            System.out.println("");
        }
    }

    private static double[][] sliceAndExtend(double[][] array, int x, int y, int n) {
        double[][] value = new double[n][2*n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                value[i][j] = array[x+i][y+j];
            }
        }
        return value;
    }

    private static double[][] doubleMirror(double[][] array) {
        int width = array.length;
        int height = array[0].length;
        double[][] value = new double[2*width][2*height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                value[i][j] = value[i][2*height-1-j] = value[2*width-1-i][j] = value[2*width-1-i][2*height-1-j] = array[i][j];
            }
        }
        return value;
    }

    private static double[][] highPassFilter(double[][] array, double threshold) {
        int width = array.length;
        int height = array[0].length;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (Math.abs(array[i][j]) < threshold)
                    array[i][j] =0;
            }
        }
        return array;
    }

    private static double[][] l2norm(double[][] table) {
        int columns = table[0].length;
        int rows = table.length;
        double[][] value = new double[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j = j+2) {
                double sum = Math.pow(table[i][j],2)+Math.pow(table[i][j+1],2);
                value[i][j] = Math.sqrt(sum);
            }
        }
        return value;
    }

    private static SortedMap<Integer, Integer> frequency(double[][][] array) {
        SortedMap<Integer, Integer> freq = new TreeMap();
        int width = array.length;
        int height = array[0].length;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int key = (int) Math.round(array[i][j][0]);
                if (freq.containsKey(key))
                    freq.put(key, freq.get(key)+1);
                else
                    freq.put(key, 1);
            }
        }
        return freq;
    }
}
