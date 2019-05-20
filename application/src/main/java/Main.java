public class Main {
    public static void main(String[] args) {
        System.out.println("Started");

        //String[] paths = new String[] {"bark-1.jpg", "bark-2.jpg", "bark-3.jpg", "bark-4.jpg", "bark-5.jpg", "bark-6.jpg", "bark-7.jpg", "bark-8.jpg", "bark-9.jpg", "bark-10.jpg", "bark-11.jpg", "bark-12.jpg"};
        String[] paths = new String[] {"bark-1.jpg", "bark-5.jpg", "bark-11.jpg"};

        FocusStacking fstack = new FocusStacking();
        fstack.Stack(paths, "./output.jpg");

        System.out.println("Finished");
    }
}
