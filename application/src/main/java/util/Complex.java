package util;

import static util.Math.hypot;

/**
 * Class to represent a complex number
 * For reference implementation see: https://introcs.cs.princeton.edu/java/32class/Complex.java.html
 */
public class Complex {

    private final double re;   // the real part
    private final double im;   // the imaginary part

    /**
     * Real part
     * @return a double
     */
    public double re() { return re; }

    /**
     * Imaginary part
     * @return a double
     */
    public double im() { return im; }

    /**
     * Create a new complex number object with the given real and imaginary parts
     * @param real Real part
     * @param imag Imaginary part
     */
    public Complex(double real, double imag) {
        this.re = real;
        this.im = imag;
    }

    /**
     * Get the absolute value of the complex number
     * @return a double
     */
    public double abs() {
        return hypot(re, im);
    }
}
