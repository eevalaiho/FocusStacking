import io.MyIOException;

import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        System.out.println("Started");

        int windowSize = 32;
        //String[] paths = new String[]{ "focus-stacking-5.png", "focus-stacking-9.png", "focus-stacking-12.png"};
        String[] paths = new String[] {"150x100-Humming-bird-top-blur.png", "150x100-Humming-bird-left-blur.png", "150x100-Humming-bird-right-blur.png"};
        FocusStacking fstack = new FocusStacking(windowSize);
        try {
            fstack.Stack(paths, "output_"+windowSize+"_"+LocalDateTime.now().toString()+".png");
        } catch (MyIOException e) {
            e.printStackTrace();
        }

        System.out.println("Finished");
    }
}
