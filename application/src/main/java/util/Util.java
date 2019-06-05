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
    public static double[][] normalize(double[][] array, double minBound, double maxBound) {
        /* Normalize to [0,1] */
        double[] minMax = minMaxValue(array, minBound, maxBound);
        double min = abs(minMax[0]);
        double max = abs(minMax[1]);
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
     * Find minimum and maximum values in an array
     * @param numbers Numbers
     * @param minBound Smallest possible number
     * @param maxBound Largest possible number
     * @return Double[] containind min and max
     */
    public static double[] minMaxValue(double[][] numbers, double minBound, double maxBound) {
        int width = numbers.length;
        int height = numbers[0].length;
        double min = maxBound;
        double max = minBound;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (numbers[i][j] > max)
                    max = numbers[i][j];
                if (numbers[i][j] < min)
                    min = numbers[i][j];
                if (max == maxBound && min == minBound)
                    return new double[] {min, max};
            }
        }
        return new double[] {min, max};
    }

    /**
     * Compute L2-norm of a 2-dimensional array
     * @param table The array
     * @return A 2-dimensional array containing the L2-norm
     */
    public static double L2Norm(Complex[][] table) {

        double sum = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                sum += table[i][j].squared();
            }
        }
        return util.Math.sqrt(sum);
        /*
        double max = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                double norm = table[i][j].l2norm();
                if (norm > max) {
                    max = norm;
                }
            }
        }
        return max;
        */
    }

//    /**
//     * Compute high-pass filter of a 2-dimensional array
//     * @param array
//     * @param threshold
//     * @return
//     */
//    public static double[][] highPassFilter(double[][] array, double threshold) {
//        int width = array.length;
//        int height = array[0].length;
//        for (int i = 0; i < width; i++) {
//            for (int j = 0; j < height; j++) {
//                if (java.lang.Math.abs(array[i][j]) < threshold)
//                    array[i][j] =0;
//            }
//        }
//        return array;
//    }
}
