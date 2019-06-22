import org.junit.Test;
import testutilities.TestUtilities;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


public class Performance_Channel {

    @Test
    public void test_150x100_w32_cRED() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, InterruptedException {
        String[] args = new String[] {"-c", "RED", "-w", "32", "-f", "150x100-koralli-mirrored-top-blur.png", "150x100-koralli-mirrored-left-blur.png", "150x100-koralli-mirrored-right-blur.png", "-o", "../../test/resources/test_150x100_w32_cRED_%s_%d.png", "-d"};
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
    public void test_150x100_w32_cGREEN() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, InterruptedException {
        String[] args = new String[] {"-c", "GREEN", "-w", "32", "-f", "150x100-koralli-mirrored-top-blur.png", "150x100-koralli-mirrored-left-blur.png", "150x100-koralli-mirrored-right-blur.png", "-o", "../../test/resources/test_150x100_w32_cGREEN_%s_%d.png", "-d"};
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
    public void test_150x100_w32_cBLUE() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, InterruptedException {
        String[] args = new String[] {"-c", "BLUE", "-w", "32", "-f", "150x100-koralli-mirrored-top-blur.png", "150x100-koralli-mirrored-left-blur.png", "150x100-koralli-mirrored-right-blur.png", "-o", "../../test/resources/test_150x100_w32_cBLUE_%s_%d.png", "-d"};
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