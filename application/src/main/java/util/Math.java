package util;

/**
 * Class for math utility methods
 */
public final class Math {

    private Math() {}

    /**
     * Calculate square root of a number (double)
     * @param number The number
     * @return Value as double
     */
    public static double sqrt(double number) {

        if (number < 0) return Double.NaN;

        double epsilon = 1.0e-15;       // relative error tolerance
        double t = number;              // estimate of the square root of c

        // repeatedly apply Newton update step until desired precision is achieved
        while (abs(t - number/t) > epsilon*t) {
            t = (number/t + t) / 2.0;
        }

        return t;
    }

    /**
     * Calculate absolute value of a number
     * @param a The number
     * @return Absolute value
     */
    public static double abs(double a) {
        return a >= 0 ? a : (-1)*a;
    }

    /**
     * Calculate hypothenuse of a right-angled triangle
     * @param a first cathetus
     * @param b second cathetus
     * @return Value as double
     */
    public static double hypot(double a, double b) {
        return sqrt( a*a + b*b);
    }
}