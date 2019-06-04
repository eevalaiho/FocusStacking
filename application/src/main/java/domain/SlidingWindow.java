package domain;

public class SlidingWindow {

    final double DEFAULT = 0.0;
    double[][] array = null;
    int x = 0;
    int y = 0;
    int size = 0;

    public int getX() {
        return x ;
    }

    public int getY() {
        return y;
    }

    public SlidingWindow(double[][] array, int size) {
        this.array = array;
        this.size = size;
    }

    public double[][] getWindow() {
        double[][] value = new double[2*size][2*size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                value[i][j] = getValue(x-size/2+i, y-size/2+j);
            }
        }
        return value;
    }

    public boolean hasNext() {
        return this.x+1 < array.length || this.y+1 < array[0].length;
    }

    public void moveNext() {
        this.x ++;
        if (this.x >= array.length) {
            this.x = 0;
            this.y ++;
        }
    }
    /**
     * Gets a value from the array
     * @param x
     * @param y
     * @return
     */
    private double getValue(int x, int y) {
        if (x < 0)
            x = 0;
        else if (x > array.length-1)
            x = array.length-1;
        if (y < 0)
            y = 0;
        else if (y > array[0].length-1)
            y = array[0].length-1;
        return array[x][y];
    }
}
