import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Started");

        String[] paths = new String[]{ "bark-4.jpg", "bark-9.jpg", "bark-12.jpg"};
        FocusStacking fstack = new FocusStacking();
        try {
            fstack.Stack(paths, "output.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Finished");
    }
}
