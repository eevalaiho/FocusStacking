package util;

import static util.Math.abs;

/**
 * Class for general utility methods
 */
public class Util {

    private Util() {}

    /**
     * Normalize a 2-dimensional array to the range [0, 1]
     * @param array The array
     * @return Normalized array
     */
    public static double[][] normalize(double[][] array, double minBound, double maxBound) {
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
     * Compute L2-norm of a 2-dimensional Complex array
     * @param table The array
     * @return L2-norm of the array
     */
    public static double l2Norm(Complex[][] table) {
        double sum = 0;
        for (int i = 0; i < table.length; i++) {
            for (int j = 0; j < table[0].length; j++) {
                sum += table[i][j].squared();
            }
        }
        return util.Math.sqrt(sum);
    }

//    /**
//     * Compute maximum L2 norm of a 2-dimensional Complex array
//     * @param table The array
//     * @return Maximum L2-norm of the array
//     */
//    public static double MaxL2Norm(Complex[][] table) {
//        double max = 0;
//        for (int i = 0; i < table.length; i++) {
//            for (int j = 0; j < table[0].length; j++) {
//                double norm = table[i][j].l2norm();
//                if (norm > max) {
//                    max = norm;
//                }
//            }
//        }
//        return max;
//    }
}
