import io.RGB;
import org.junit.Test;
import testutilities.TestUtilities;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class MainTest {


    @Test
    public void main() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, NoSuchFieldException {

        String[] args = new String[] {"-c", "RED", "-w", "8", "-f", "30x20-kaunokki-top-blur.png", "30x20-kaunokki-left-blur.png", "30x20-kaunokki-right-blur.png", "-o", "../../test/resources/test_output_%s_%d.png", "-d"};
        Object[] args2 = {args};

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Constructor ctor = Main.class.getDeclaredConstructors()[0];
        ctor.setAccessible(true);
        Main main = (Main)ctor.newInstance();

        // Call main method
        TestUtilities.callPrivateMethod(main.getClass(), new Class[] { args.getClass() }, "main", main, args2);

        System.setOut(System.out);

        String[] arr_outStr = outContent.toString().split("\n");

        assertEquals(25, arr_outStr.length);
        assertEquals("Using window of size 8", arr_outStr[1]);
        assertEquals("Stacking channel RED", arr_outStr[2]);
        assertEquals("2,2,2,2,2,2,0,1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0", arr_outStr[3]);
        assertEquals("1,1,1,1,1,1,1,1,2,2,2,2,2,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0", arr_outStr[22]);


        assertTrue((new File("./src/test/resources/", "test_output_RED_8.png")).exists());
    }

    @Test
    public void makeImageStack() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

        String[] fileNames = new String[]{"30x20-kaunokki-top-blur.png", "30x20-kaunokki-left-blur.png", "30x20-kaunokki-right-blur.png"};
        String outputFileName = "../../test/resources/test_output_RED_8.png";
        Object[] args = new Object[] {RGB.RED, 8, fileNames, outputFileName};

        Constructor ctor = Main.class.getDeclaredConstructors()[0];
        ctor.setAccessible(true);
        Main main = (Main)ctor.newInstance();

        // Call main method
        TestUtilities.callPrivateMethod(main.getClass(), new Class[] { RGB.class, int.class, fileNames.getClass(), String.class }, "makeImageStack", main, args);

        assertTrue((new File("./src/test/resources/", "test_output_RED_8.png")).exists());
    }

    @Test(expected = InvocationTargetException.class)
    public void parseArguments_illegal() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {

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


    @Test
    public void parseArguments_outputFileNameFormat() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException, NoSuchFieldException {

        String[] args = new String[] {"-o", "a", "-d"};
        Object[] args2 = {args};

        Constructor ctor = Main.class.getDeclaredConstructors()[0];
        ctor.setAccessible(true);
        Main main = (Main)ctor.newInstance();

        TestUtilities.callPrivateMethod(main.getClass(), new Class[] { args.getClass() }, "parseArguments", main, args2);

        String actual = TestUtilities.getPrivateField("outputFileNameFormat", main);
        assertEquals("a", actual);
    }
}