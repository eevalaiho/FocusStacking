import java.io.IOException;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        System.out.println("Started");

        String[] paths = new String[] {"150x100-koralli-mirrored-top-blur.png", "150x100-koralli-mirrored-left-blur.png", "150x100-koralli-mirrored-right-blur.png"};
        int windowSize = 32;

        FocusStacking fstack = new FocusStacking(windowSize);
        try {
            fstack.Stack(paths, "output_"+windowSize+"_"+LocalDateTime.now().toString()+".png");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finished");
    }
}
