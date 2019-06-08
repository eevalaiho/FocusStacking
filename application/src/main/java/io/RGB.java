package io;

public enum RGB {

    RED(16), GREEN(8), BLUE(0);

    private int numVal;

    RGB(int numVal) {
        this.numVal = numVal;
    }

    private int getNumVal() {
        return numVal;
    }

    /**
     * Get color channel value from int representation of pixel
     *
     * AAAAAAAA RRRRRRRR GGGGGGGG BBBBBBBB
     * ^Alpha   ^Red     ^Green   ^Blue
     *
     * For reference see: https://stackoverflow.com/questions/16698372/isolating-red-green-blue-channel-in-java-bufferedimage
     *
     * @param rgb Pixel as RGB int representation
     * @param channel Color channel as io.RGB
     * @return Color channel of the pixel as integer
     */
    public static int getChannelValue(int rgb, RGB channel) {
        return rgb >> channel.getNumVal() & 0xff;
    }


    public static RGB parse(String name) {
        switch (name) {
            case "RED":
                return RGB.RED;
            case "GREEN":
                return RGB.GREEN;
            case "BLUE":
                return RGB.BLUE;
            default:
                throw new IllegalArgumentException("Name couldn't be parsed " + name);
        }
    }
}
