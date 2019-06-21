import org.junit.Test;
import testutilities.TestUtilities;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class Performance_ImageSize {

    @Test
    public void test_30x20_w16_cRED() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, InterruptedException {
        String[] args = new String[] {"-c", "RED", "-w", "16", "-f", "30x20-kaunokki-top-blur.png", "30x20-kaunokki-left-blur.png", "30x20-kaunokki-right-blur.png", "-o", "../../test/resources/test_output_%s_%d.png", "-d"};
        Object[] args2 = {args};

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Constructor ctor = Main.class.getDeclaredConstructors()[0];
        ctor.setAccessible(true);
        Main main = (Main)ctor.newInstance();

        // Call main method
        TestUtilities.callPrivateMethod(main.getClass(), new Class[] { args.getClass() }, "main", main, args2);

        //Pause for 5 seconds
        Thread.sleep(5000);
    }

    @Test
    public void test_150x100_w16_cRED() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, InterruptedException {
        String[] args = new String[] {"-c", "RED", "-w", "16", "-f", "150x100-koralli-mirrored-top-blur.png", "150x100-koralli-mirrored-left-blur.png", "150x100-koralli-mirrored-right-blur.png", "-o", "../../test/resources/test_output_%s_%d.png", "-d"};
        Object[] args2 = {args};

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Constructor ctor = Main.class.getDeclaredConstructors()[0];
        ctor.setAccessible(true);
        Main main = (Main)ctor.newInstance();

        // Call main method
        TestUtilities.callPrivateMethod(main.getClass(), new Class[] { args.getClass() }, "main", main, args2);

        Thread.sleep(5000);
    }

    @Test
    public void test_300x200_w16_cRED() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, InterruptedException {
        String[] args = new String[] {"-c", "RED", "-w", "16", "-f", "300x200-kaunokki-top-blur.png", "300x200-kaunokki-left-blur.png", "300x200-kaunokki-right-blur.png", "-o", "../../test/resources/test_output_%s_%d.png", "-d"};
        Object[] args2 = {args};

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Constructor ctor = Main.class.getDeclaredConstructors()[0];
        ctor.setAccessible(true);
        Main main = (Main)ctor.newInstance();

        // Call main method
        TestUtilities.callPrivateMethod(main.getClass(), new Class[] { args.getClass() }, "main", main, args2);


        Thread.sleep(5000);
    }
}