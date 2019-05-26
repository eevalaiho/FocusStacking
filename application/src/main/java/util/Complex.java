package util;

import static java.lang.Math.hypot;

/**
 * Reference implementation: https://introcs.cs.princeton.edu/java/32class/Complex.java.html
 */
public class Complex {

    private final double re;   // the real part
    private final double im;   // the imaginary part

    // return the real or imaginary part
    public double re() { return re; }
    public double im() { return im; }

    // create a new object with the given real and imaginary parts
    public Complex(double real, double imag) {
        this.re = real;
        this.im = imag;
    }

    // return abs/modulus/magnitude
    public double abs() {
        return hypot(re, im);
    }
}
