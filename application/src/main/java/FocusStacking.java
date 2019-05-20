import java.io.IOException;

public class FocusStacking {

    public void FocusStacking() { }

    public void Stack(String[] paths, String outputPath) throws IOException{

        // Load and preprocess images
        MyImageIO imageIO = new MyImageIO();
        imageIO.LoadImages(paths);
        
        // Get the green 
        int[][] greens = imageIO.getGreenChannels();
        



        // Compute the sharpest pixels


        // Save pixels

    }
}
