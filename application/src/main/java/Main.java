import io.RGB;

import java.io.IOException;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {

        System.out.println("Started");

        String[] fileNames = new String[] {"150x100-koralli-mirrored-top-blur.png", "150x100-koralli-mirrored-left-blur.png", "150x100-koralli-mirrored-right-blur.png"};
        //String[] fileNames = new String[] {"150x100-koralli2-mirrored-top-blur.png", "150x100-koralli2-mirrored-left-blur.png", "150x100-koralli2-mirrored-right-blur.png"};
        //String[] fileNames = new String[] {"150x100-butterfly-mirrored-top-blur.png", "150x100-butterfly-mirrored-left-blur.png", "150x100-butterfly-mirrored-right-blur.png"};

        boolean debugChannels = false;
        boolean debugWindowSize = true;

        if ( debugChannels ) {
            int windowSize = 32;
            for (RGB channel: new RGB[] { RGB.RED, RGB.GREEN, RGB.BLUE }) {
                String outputFileName = String.format("output_%s_%d_%s.png", channel.name(), windowSize, LocalDateTime.now().toString());
                System.out.println("Stacking " + channel.name());
                makeImageStack(channel, windowSize, fileNames, outputFileName);
            }
        }
        else if ( debugWindowSize ) {
            RGB channel = RGB.BLUE;
            for (int windowSize: new int[] { 16, 32, 64 }) {
                String outputFileName = String.format("output_%s_%d_%s.png", channel.name(), windowSize, LocalDateTime.now().toString());
                System.out.println("Stacking " + channel.name());
                makeImageStack(channel, windowSize, fileNames, outputFileName);
            }
        }
        else {
            RGB channel = RGB.BLUE;
            int windowSize = 16;
            String outputFileName = String.format("output_%s_%d_%s.png", channel.name(), windowSize, LocalDateTime.now().toString());
            System.out.println("Stacking " + channel.name());
            makeImageStack(channel, windowSize, fileNames, outputFileName);
        }

        System.out.println("Finished");
    }

    /**
     * Driver method to
     * @param channel
     * @param windowSize
     * @param fileNames
     * @param outputFileName
     */
    public static void makeImageStack(RGB channel, int windowSize, String[] fileNames, String outputFileName) {
        FocusStacking fstack = new FocusStacking(channel, windowSize);
        try {
            fstack.loadImages(fileNames);
            fstack.computeSharpestPixels();
            fstack.printSharpestPixelIndexes();
            fstack.saveImage(outputFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
