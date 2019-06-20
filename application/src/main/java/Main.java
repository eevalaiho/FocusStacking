import java.io.IOException;
import java.time.LocalDateTime;

import io.RGB;
import util.MyArrayList;

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
     * The main method of the program
     * @param args Parameters for the program, use:<ul>
     *             <li>-d, --debug                    to set debug mode on<br /> In debug mode the id's of the sharpest images in each pixel position will be printed</li>
     *             <li>-w, --windowSize               to set window size for fourier transform<br />Window size must be a power of two. Multiple sizes can be provided. A separate output image will be created for each window size. Example -w 8 16 32</li>
     *             <li>-c, --channels                 to st color channel(s) to use for figuring out the sharpest pixels<br />Possible values RED, GREEN, BLUE. A separate output image will be created for each window size. Example -c BLUE</li>
     *             <li>-f, --fileNames                to set names of the files to use<br />The files should reside in application/src/main/resources folder. Example -f pic1.png pic2.png pic3.png</li>
     *             <li>-o, --outputFileNameFormat     to set the formatting string of the output file<br />If none is provided a default formatting string is used. Should contain format instruction %s for channel name and %d for window size. Example output_%s_%d.png</li>
     *  </ul>
     */
    public static void main(String[] args) throws IOException {

        System.out.println("Started");

        if (args != null && args.length > 0) {
            parseArguments(args);
        }

        if (fileNames == null) {
            if (debug) {
                //fileNames = new String[]{"150x100-koralli-mirrored-top-blur.png", "150x100-koralli-mirrored-left-blur.png", "150x100-koralli-mirrored-right-blur.png"};
                //fileNames = new String[]{"300x200-kaunokki-top-blur.png", "300x200-kaunokki-left-blur.png", "300x200-kaunokki-right-blur.png"};
                fileNames = new String[]{"30x20-kaunokki-top-blur.png", "30x20-kaunokki-left-blur.png", "30x20-kaunokki-right-blur.png"};
            }
            else {
                System.out.println("File names not provided, exiting");
                return;
            }
        }
        if (windowSizes == null) {
            System.out.println("Window sizes not provided, using 16");
            windowSizes = new int[]{16};
        }
        if (channels == null) {
            System.out.println("Channels not provided, using BLUE");
            channels = new RGB[]{RGB.BLUE};
        }

        if (debug)
            System.out.println(argumentsToString());

        // Main program loop
        for (int windowSize : windowSizes) {
            System.out.println("Using window of size " + windowSize);
            for (RGB channel : channels) {
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
    private static void parseArguments(String[] args) throws IllegalArgumentException {

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
                    case "--windowSizes":
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
                }
            }
            else {
                throw new IllegalArgumentException("Illegal argument " + args[i]);
            }
        }
    }

    /**
     * Concatenate program parameters as a String
     * For debugging purposes
     * @return A string of parametes
     */
    private static String argumentsToString() {
        String value = "Running the program with parameters:\n";

        value += "File names: [";
        for (String x : fileNames) {
            value += x + ", ";
        }
        value = value.substring(0, value.length()-2) + "]\n";

        value += "Channels: [";
        for (RGB x : channels) {
            value += x + ", ";
        }
        value = value.substring(0, value.length()-2) + "]\n";

        value += "Window sizes: [";
        for (int x : windowSizes) {
            value += x + ", ";
        }
        value = value.substring(0, value.length()-2) + "]\n";

        value += "Output file name format: " + outputFileNameFormat;
        value += "\nDebug: " + debug;

        return value;
    }
}
