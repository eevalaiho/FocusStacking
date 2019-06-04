import io.MyIOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Started");

        String[] paths = new String[]{ "focus-stacking-4.png", "focus-stacking-9.png", "focus-stacking-12.png"};
        FocusStacking fstack = new FocusStacking();
        try {
            fstack.Stack(paths, "output.png");
        } catch (MyIOException e) {
            e.printStackTrace();
        }

        System.out.println("Finished");
    }
}
