import io.RGB;
import util.MyArrayList;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Driver program for image stacking implementation
 */
public class Main {

    static String[] fileNames = null;
    static int[] windowSizes = null;
    static RGB[] channels = null;
    static boolean debug = false;
    static String outputFileNameFormat = "";

    private Main() {}

    /**
     * Driver program for image stacking implementation
     * @param args Parameters for the program, use:
     *             -d, --debug                    Set debug mode. In debug mode the id's of the sharpest images in each pixel position will be printed
     *             -w, --windowSize               Set window size for fourier transform. Window size must be a power of two. Multiple sizes can be provided. A separate output image will be created for each window size. Example -w 8 16 32
     *             -c, --channels                 Set color channel(s) to use for figuring out the sharpest pixels. Possible values RED, GREEN, BLUE. A separate output image will be created for each window size. Example -c BLUE
     *             -f, --fileNames                Set names of the files to use. The files should reside in application/src/main/resources folder. Example -f pic1.png pic2.png pic3.png
     *             -o, --outputFileNameFormat     Format of the output file. If none is provided a default format is used. Should contain format instruction %s for channel name and %d for window size. Example output_%s_%d.png
     */
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, IOException {

        System.out.println("Started");

        if (args != null && args.length > 0)
            parseArguments(args);

        if (debug) {
            if (fileNames == null) {
                fileNames = new String[]{"150x100-koralli-mirrored-top-blur.png", "150x100-koralli-mirrored-left-blur.png", "150x100-koralli-mirrored-right-blur.png"};
                //fileNames = new String[]{"300x200-kaunokki-top-blur.png", "300x200-kaunokki-left-blur.png", "300x200-kaunokki-right-blur.png"};
            }
            if (windowSizes == null)
                windowSizes = new int[]{16,32};
            if (channels == null)
                channels = new RGB[]{RGB.BLUE, RGB.GREEN, RGB.RED};
        }

        // Main program loop
        for (int windowSize: windowSizes) {
            System.out.println("Using window of size " + windowSize);
            for (RGB channel: channels) {
                System.out.println("Stacking channel " + channel.name());
                if (outputFileNameFormat == "")
                    outputFileNameFormat = "output_%s_%d_" + LocalDateTime.now().toString() + ".png";
                String outputFileName = String.format(outputFileNameFormat, channel.name(), windowSize);
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
    private static void makeImageStack(RGB channel, int windowSize, String[] fileNames, String outputFileName) throws IOException {
        FocusStacking fstack = new FocusStacking(channel, windowSize);
        fstack.loadImages(fileNames);
        fstack.computeSharpestPixels();
        if (debug)
            fstack.printSharpestPixelIndexes();
        fstack.saveImage(outputFileName);
    }
    /**
     * Method to parse command line arguments
     * @param args
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    private static void parseArguments(String[] args) throws InstantiationException, IllegalAccessException {

        MyArrayList<String> list_fileNames = new MyArrayList<>();
        MyArrayList<RGB> list_channels = new MyArrayList<>();
        MyArrayList<Integer> list_windowSizes = new MyArrayList<>();

        for (int i = 0; i < args.length; i++) {
            if (args[i].startsWith("-")) {
                switch (args[i]) {
                    case "-d":
                    case "--debug":
                        debug = true;
                        break;
                    case "-c":
                    case "--channels":
                        for (int j = i+1; j < args.length; j++) {
                            if (args[j].startsWith("-")) {
                                i = j-1;
                                break;
                            }
                            list_channels.add(RGB.parse(args[j]));
                            i = j;
                        }
                        channels = new RGB[list_channels.getSize()];
                        for (int k = 0; k < list_channels.getSize(); k++)
                            channels[k] = list_channels.get(k);
                        break;
                    case "-w":
                    case "--windowSize":
                        for (int j = i+1; j < args.length; j++) {
                            if (args[j].startsWith("-")) {
                                break;
                            }
                            list_windowSizes.add(Integer.parseInt(args[j]));
                            i = j;
                        }
                        windowSizes = new int[list_windowSizes.getSize()];
                        for (int k = 0; k < list_windowSizes.getSize(); k++)
                            windowSizes[k] = list_windowSizes.get(k);
                        break;
                    case "-f":
                    case "--fileNames":
                        for (int j = i+1; j < args.length; j++) {
                            if (args[j].startsWith("-")) {
                                break;
                            }
                            list_fileNames.add(args[j]);
                            i = j;
                        }
                        fileNames = new String[list_fileNames.getSize()];
                        for (int k = 0; k < list_fileNames.getSize(); k++)
                            fileNames[k] = list_fileNames.get(k);
                        break;
                    case "-o":
                    case "--outputFileNameFormat":
                        outputFileNameFormat = args[i+1];
                        i++;
                        break;
                    //default:
                    //   throw new IllegalArgumentException("Illegal argument " + args[i]);
                }
            }
            else {
                throw new IllegalArgumentException("Illegal argument " + args[i]);
            }
        }
    }
}
