package util;

import static util.Math.abs;

/**
 * Class for general utility methods
 */
public class Util {
    /**
     * Normalize a 2-dimensional array to the range [0, 1]
     * @param array The array
     * @return Normalized array
     */
    public static double[][] normalize(double[][] array) {
        /* Normalize to [0,1] */
        double max = abs(maxValue(array)[0]);
        double min = abs(minValue(array)[0]);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (min > max)
                    array[i][j] = (abs(array[i][j]) - max) / (min - max);
                else
                    array[i][j] = (array[i][j] - min) / (max - min);
            }
        }
        return array;
    }
    /**
     * Find out maximum value of a 2-dimensional array and it's position
     * @param numbers The array
     * @return Double[] containing the maximum value, and it's position
     */
    public static double[] maxValue(double[][] numbers) {
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

    /**
     * Get maximum value and argument from double[]
     * @param array The array
     * @return MAximun value and argument as double[]
     */
    public static double[] maxValue(double[] array) {
        double max = 0;
        double argMax = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > max) {
                argMax = i;
                max = array[i];
            }
        }
        return new double[]{max, argMax};
    }
    /**
     * Find out minimum value of a 2-dimensional array and it's position
     * @param numbers The array
     * @return Double[] containing the minimum value, and it's position
     */
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
    /**
     * Compute L2-norm of a 2-dimensional array
     * @param table The array
     * @return A 2-dimensional array containing the L2-norm
     */
    public static double l2norm(double[][] table) {
        return 0;
    }
    /**
     * Compute high-pass filter of a 2-dimensional array
     * @param array
     * @param threshold
     * @return
     */
    public static double[][] highPassFilter(double[][] array, double threshold) {
        int width = array.length;
        int height = array[0].length;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (java.lang.Math.abs(array[i][j]) < threshold)
                    array[i][j] =0;
            }
        }
        return array;
    }
}
