import org.junit.Test;
import testutilities.TestUtilities;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class Performance_WindowSize {


    @Test
    public void test_150x100_w8_cRED() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, InterruptedException {
        String[] args = new String[] {"-c", "RED", "-w", "8", "-f", "150x100-koralli-mirrored-top-blur.png", "150x100-koralli-mirrored-left-blur.png", "150x100-koralli-mirrored-right-blur.png", "-o", "../../test/resources/test_output_150x100_w8_cRED_%s_%d.png", "-d"};
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
    public void test_150x100_w16_cRED() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, InterruptedException {
        String[] args = new String[] {"-c", "RED", "-w", "16", "-f", "150x100-koralli-mirrored-top-blur.png", "150x100-koralli-mirrored-left-blur.png", "150x100-koralli-mirrored-right-blur.png", "-o", "../../test/resources/test_output_150x100_w16_cRED_%s_%d.png", "-d"};
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
    public void test_150x100_w32_cRED() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, InterruptedException {
        String[] args = new String[] {"-c", "RED", "-w", "32", "-f", "150x100-koralli-mirrored-top-blur.png", "150x100-koralli-mirrored-left-blur.png", "150x100-koralli-mirrored-right-blur.png", "-o", "../../test/resources/test_output_150x100_w32_cRED_%s_%d.png", "-d"};
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

    /*
    @Test
    public void test_150x100_w64_cRED() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, InterruptedException {
        String[] args = new String[] {"-c", "RED", "-w", "64", "-f", "150x100-koralli-mirrored-top-blur.png", "150x100-koralli-mirrored-left-blur.png", "150x100-koralli-mirrored-right-blur.png", "-o", "../../test/resources/test_output_150x100_w64_cRED_%s_%d.png", "-d"};
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
    */
}