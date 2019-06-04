package domain;

public class SlidingWindow {

    final double DEFAULT = 0.0;
    double[][] array = null;
    int startX = 0;
    int startY = 0;
    int size = 0;

    public SlidingWindow(double[][] array, int size) {
        this.array = array;
        this.size = size;
    }

    public double[][] getWindow() {
        double[][] value = new double[2*size][2*size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                value[i][j] = getValue(startX-size/2+i, startY-size/2+j);
            }
        }
        return value;
    }

    public boolean hasNext() {
        return this.startX+1 < array.length || this.startY+1 < array[0].length;
    }

    public void moveNext() {
        this.startX ++;
        if (this.startX > array.length) {
            this.startX = 0;
            this.startY ++;
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
