package domain;

import util.Complex;
import util.MyArrayList;

public class FFT {

    /**
     * Computes 2D Fourier transform (FFT) in two steps.
     * First it computes the one-dimensional FFT along rows.
     * Then it computes the FFT of the output along columns.
     * @param array
     * @return
     */
    public static Complex[][] fft2(double[][] array) throws IllegalArgumentException {

        if (array.length != array[0].length)
            throw new IllegalArgumentException("Array dimensions should equal");

        if (array.length % 2 != 0)
            throw new IllegalArgumentException("Array dimensions should be a power of 2");

        int size = array.length;

        // Row-wise fft
        MyArrayList<Complex[]> fft_rows = new MyArrayList<>();
        for (int i = 0; i < size; i++) {
            Complex[] row = new Complex[size];
            for (int j = 0; j < size; j++) {
                row[j] = new Complex(array[i][j], 0.0);
            }
            fft_rows.add(fft(row));
        }

        // Column-wise fft
        Complex[][] value = new Complex[size][size];
        for (int j = 0; j < size; j++) {
            Complex[] column = new Complex[size];
            for (int i = 0; i < size; i++) {
                column[i] = fft_rows.get(i)[j];
            }
            Complex[] fft_column = fft(column);
            for (int i = 0; i < size; i++) {
                value[i][j] = fft_column[i];
            }
        }

        return value;
    }


    /**
     * Computes Fourier transform (FFT).
     * Length of the array should be a power of 2
     * Copyright © 2000–2017, Robert Sedgewick and Kevin Wayne.
     * Ref: https://introcs.cs.princeton.edu/java/97data/FFT.java.html
     * @param x
     * @return
     */
    public static Complex[] fft(Complex[] x) {
        int n = x.length;

        // base case
        if (n == 1) return new Complex[] { x[0] };

        // radix 2 Cooley-Tukey FFT
        if (n % 2 != 0) {
            throw new IllegalArgumentException("n is not a power of 2");
        }

        // fft of even terms
        Complex[] even = new Complex[n/2];
        for (int k = 0; k < n/2; k++) {
            even[k] = x[2*k];
        }
        Complex[] q = fft(even);

        // fft of odd terms
        Complex[] odd  = even;  // reuse the array
        for (int k = 0; k < n/2; k++) {
            odd[k] = x[2*k + 1];
        }
        Complex[] r = fft(odd);

        // combine
        Complex[] y = new Complex[n];
        for (int k = 0; k < n/2; k++) {
            double kth = -2 * k * Math.PI / n;
            Complex wk = new Complex(Math.cos(kth), Math.sin(kth));
            y[k]       = q[k].plus(wk.times(r[k]));
            y[k + n/2] = q[k].minus(wk.times(r[k]));
        }
        return y;
    }
}
