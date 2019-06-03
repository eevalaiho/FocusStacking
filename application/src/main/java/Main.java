import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Started");

        //String[] paths = new String[] {"bark-1.jpg", "bark-2.jpg", "bark-3.jpg", "bark-4.jpg", "bark-5.jpg", "bark-6.jpg", "bark-7.jpg", "bark-8.jpg", "bark-9.jpg", "bark-10.jpg", "bark-11.jpg", "bark-12.jpg"};
        //String[] paths = new String[] {"bark-1.jpg", "bark-5.jpg", "bark-11.jpg"};
        String[] paths = new String[] {"src/test/resources/rgb.png", "src/test/resources/rgb2.png"};

        FocusStacking fstack = new FocusStacking();
        try {
            // imageIO.LoadImages(new String[]{new File("src/test/resources/rgb.png").getAbsolutePath()});
            fstack.Stack(paths, "src/test/resources/output.jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Finished");
    }
}
