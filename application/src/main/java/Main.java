import io.MyIOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Started");

        String[] paths = new String[]{ "focus-stacking-4.jpg", "focus-stacking-9.jpg", "focus-stacking-12.jpg"};
        FocusStacking fstack = new FocusStacking();
        try {
            fstack.Stack(paths, "output.jpg");
        } catch (MyIOException e) {
            e.printStackTrace();
        }

        System.out.println("Finished");
    }
}
