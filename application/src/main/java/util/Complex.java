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

    /**
     * Add two Complex's
     * @param c The Complex to be added
     * @return A new Complex whose value is this + c
     */
    public Complex plus(Complex c) {
        Complex a = this;
        return new Complex(a.re + c.re, a.im + c.im);
    }

    /**
     * Subtracts a Complex from this
     * @param c The Complex to be subtracted
     * @return A new Complex whose value is this - c
     */
    public Complex minus(Complex c) {
        Complex a = this;
        return new Complex(a.re - c.re, a.im - c.im);
    }

    /**
     * Miltiplies a Complex with this
     * @param c The Complex to be multiplied with
     * @return A new Complex whose value is this * c
     */
    public Complex times(Complex c) {
        Complex a = this;
        double real = a.re * c.re - a.im * c.im;
        double imag = a.re * c.im + a.im * c.re;
        return new Complex(real, imag);
    }
}
