package domain;

public class SlidingWindow {

    final double DEFAULT = 0.0;
    int x = 0;
    int y = 0;
    int size = 0;
    int arrayWidth = 0;
    int arrayHeight = 0;

    /**
     * Get current x
     * @return
     */
    public int getX() {
        return x ;
    }

    /**
     * Get current y
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     * Construct the SlidingWindow
     * @param size Size of the window
     * @param arrayWidth Width of the array
     * @param arrayHeight Height of the array
     */
    public SlidingWindow(int size, int arrayWidth, int arrayHeight) {
        this.size = size;
        this.arrayWidth = arrayWidth;
        this.arrayHeight = arrayHeight;
    }

    /**
     * Determine if there's a next value
     * @return True if there is
     */
    public boolean hasNext() {
        return this.x+1 < arrayWidth || this.y+1 < arrayHeight;
    }

    /**
     * Slides the window forward
     */
    public void moveNext() {
        this.x ++;
        if (this.x >= arrayWidth) {
            this.x = 0;
            this.y ++;
        }
    }

    /**
     * Get the window doubly mirrored
     *
     * ab    becomes    abba
     * cd               cddc
     *                  cddc
     *                  abba
     *
     * @param array
     * @return A double[][] that is 4-times the size of the window
     */
    public double[][] getDoubleMirroredWindow(double[][] array) {
        double[][] value = new double[2*size][2*size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                value[i][j] = getValue(array,x-size/2+i, y-size/2+j);
                value[2*size-1-i][j] = value[i][2*size-1-j] = value[2*size-1-i][2*size-1-j] = value[i][j];
                //System.out.println("("+i+","+j+"), ("+(2*size-1-i)+","+j+"), ("+i+","+(2*size-1-j)+"), ("+(2*size-1-i)+","+(2*size-1-j)+") = " + value[i][j]);
            }
        }
        return value;
    }

    /**
     * Gets a value from the array in position (x,y).
     * If either x or y are out of bounds will return the closest value from the array.
     * @param x
     * @param y
     * @return
     */
    private double getValue(double[][] array, int x, int y) {
        if (x < 0)
            x = 0;
        else if (x > array[0].length - 1)
            x = array[0].length - 1;
        if (y < 0)
            y = 0;
        else if (y > array.length - 1)
            y = array.length - 1;

        double value = array[y][x];
        return value;
    }
}
