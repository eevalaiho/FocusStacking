import io.RGB;
import util.MyArrayList;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Driver program for image stacking implementation
 */
public class Main {

    static String[] fileNames = null;
    static Integer[] windowSizes = null;
    static RGB[] channels = null;
    static boolean debug = false;

    /**
     * Driver program for image stacking implementation
     * @param args Parameters for the program, use:
     *             -d, --debug          Set debug mode. In debug mode the id's of the sharpest images in each pixel position will be printed
     *             -w, --windowSize     Set window size for fourier transform. Window size must be a power of two. Multiple sizes can be provided. A separate output image will be created for each window size. Example -w 8 16 32
     *             -c, --channels       Set color channel(s) to use for figuring out the sharpest pixels. Possible values RED, GREEN, BLUE. A separate output image will be created for each window size. Example -c BLUE
     *             -f, --fileNames      Set names of the files to use. The files should reside in application/src/main/resources folder. Example -f 150x100-koralli-mirrored-top-blur.png 150x100-koralli-mirrored-left-blur.png 150x100-koralli-mirrored-right-blur.png
     */
    public static void main(String[] args) {

        // Default values - if command line arguments are passed these will be overritten
        fileNames = new String[]{"150x100-koralli-mirrored-top-blur.png", "150x100-koralli-mirrored-left-blur.png", "150x100-koralli-mirrored-right-blur.png"};
        windowSizes = new Integer[]{32};
        channels = new RGB[]{RGB.BLUE};

        if (args != null && args.length > 0) {
            parseArguments(args);
        }

        System.out.println("Started");

        // Main program loop
        for (int windowSize: windowSizes) {
            System.out.println("Using window of size " + windowSize);
            for (RGB channel: channels) {
                System.out.println("Stacking channel " + channel.name());
                String outputFileName = String.format("output_%s_%d_%s.png", channel.name(), windowSize, LocalDateTime.now().toString());
                makeImageStack(channel, windowSize, fileNames, outputFileName);
            }
        }

        System.out.println("Finished");
    }

    /**
     * Method to create a stacked image by using the FocusStacking classes methods
     * @param channel Which color channel to use for picking teh sharpest pixel
     * @param windowSize Which window size to use for fourier transform
     * @param fileNames File names of the images to stack
     * @param outputFileName Output file name
     */
    private static void makeImageStack(RGB channel, int windowSize, String[] fileNames, String outputFileName) {
        FocusStacking fstack = new FocusStacking(channel, windowSize);
        try {
            fstack.loadImages(fileNames);
            fstack.computeSharpestPixels();
            if (debug)
                fstack.printSharpestPixelIndexes();
            fstack.saveImage(outputFileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parse command line arguments
     * @param args The arguments
     */
    private static void parseArguments(String[] args) {

        MyArrayList<String> list_fileNames = new MyArrayList<>();
        MyArrayList<RGB> list_channels = new MyArrayList<>();
        MyArrayList<Integer> list_windowSizes = new MyArrayList<>();

        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                switch (args[i]) {
                    case "-d": case "--debug":
                        debug = true;
                    case "-c": case "--channels":
                        for (int j = i+1; j < args.length; j++) {
                            if (args[j].startsWith("-"))
                                break;
                            list_channels.add(RGB.parse(args[j]));
                            i = j;
                        }
                        channels = list_channels.toArray();
                        break;
                    case "-w": case "--windowSize":
                        for (int j = i+1; j < args.length; j++) {
                            if (args[j].startsWith("-"))
                                break;
                            list_windowSizes.add(Integer.parseInt(args[j]));
                            i = j;
                        }
                        fileNames = list_fileNames.toArray();
                        break;
                    case "-f": case "--fileNames":
                        for (int j = i+1; j < args.length; j++) {
                            if (args[j].startsWith("-"))
                                break;
                            list_fileNames.add(args[j]);
                            i = j;
                        }
                        windowSizes = list_windowSizes.toArray();
                        break;
                    default:
                        throw new IllegalArgumentException("Illegal argument " + args[i]);
                }
            }
            else {
                throw new IllegalArgumentException("Illegal argument " + args[i]);
            }
        }
    }
}
