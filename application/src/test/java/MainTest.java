import io.RGB;
import org.junit.Test;
import testutilities.TestUtilities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static java.awt.SystemColor.window;
import static org.junit.Assert.*;

public class MainTest {

    @Test
    public void main() {
        String[] fileNames = new String[]{"30x20-kaunokki-top-blur.png", "30x20-kaunokki-left-blur.png", "30x20-kaunokki-right-blur.png"};
        Integer[] windowSizes = new Integer[]{4, 8};
        String[] channels = new String[]{"BLUE", "GREEN", "RED"};

        //Main.main()
    }

    @Test
    public void parseArguments() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

        String[] args = new String[] {"-c", "RED", "GREEN", "BLUE", "-w", "4", "8", "-f", "30x20-kaunokki-top-blur.png", "30x20-kaunokki-left-blur.png", "30x20-kaunokki-right-blur.png","-d"};
        Object[] args2 = {args};

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Constructor ctor = Main.class.getDeclaredConstructors()[0];
        ctor.setAccessible(true);
        Main main = (Main)ctor.newInstance();

        Object value = TestUtilities.callPrivateMethod(main.getClass(), new Class[] { args.getClass() }, "parseArguments", main, args2);

        System.setOut(System.out);
    }

    @Test(expected = InvocationTargetException.class)
    public void parseArguments_illegal() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, NoSuchFieldException {

        String[] args = new String[] {"illegal"};
        Object[] args2 = {args};

        Constructor ctor = Main.class.getDeclaredConstructors()[0];
        ctor.setAccessible(true);
        Main main = (Main)ctor.newInstance();

        TestUtilities.callPrivateMethod(main.getClass(), new Class[]{args.getClass()}, "parseArguments", main, args2);
    }

    @Test
    public void parseArguments_channels() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, NoSuchFieldException {

        String[] args = new String[] {"-c", "RED", "GREEN", "BLUE", "-d"};
        Object[] args2 = {args};

        Constructor ctor = Main.class.getDeclaredConstructors()[0];
        ctor.setAccessible(true);
        Main main = (Main)ctor.newInstance();

        TestUtilities.callPrivateMethod(main.getClass(), new Class[] { args.getClass() }, "parseArguments", main, args2);

        RGB[] actual = TestUtilities.getPrivateField("channels", main);
        assertEquals(RGB.RED, actual[0]);
        assertEquals(RGB.GREEN, actual[1]);
        assertEquals(RGB.BLUE, actual[2]);
    }

    @Test
    public void parseArguments_windowSizes() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, NoSuchFieldException {

        String[] args = new String[] {"-w", "8", "16", "-d"};
        Object[] args2 = {args};

        Constructor ctor = Main.class.getDeclaredConstructors()[0];
        ctor.setAccessible(true);
        Main main = (Main)ctor.newInstance();

        TestUtilities.callPrivateMethod(main.getClass(), new Class[] { args.getClass() }, "parseArguments", main, args2);

        int[] actual = TestUtilities.getPrivateField("windowSizes", main);
        assertEquals(8, actual[0]);
        assertEquals(16, actual[1]);
    }

    @Test
    public void parseArguments_fileNames() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, NoSuchFieldException {

        String[] args = new String[] {"-f", "a", "b", "c", "-d"};
        Object[] args2 = {args};

        Constructor ctor = Main.class.getDeclaredConstructors()[0];
        ctor.setAccessible(true);
        Main main = (Main)ctor.newInstance();

        TestUtilities.callPrivateMethod(main.getClass(), new Class[] { args.getClass() }, "parseArguments", main, args2);

        String[] actual = TestUtilities.getPrivateField("fileNames", main);
        assertEquals("a", actual[0]);
        assertEquals("b", actual[1]);
        assertEquals("c", actual[2]);
    }

}