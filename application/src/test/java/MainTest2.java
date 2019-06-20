import io.RGB;
import org.junit.Test;
import testutilities.TestUtilities;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTest2 {

    /**
     * For some reason (the use of reflection maybe) this method does not work together with the rest of the main class methods
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     */
    @Test
    public void main_withoutArgs() throws IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchMethodException {
        String[] args = new String[] {};
        Object[] args2 = {args};

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        Constructor ctor = Main.class.getDeclaredConstructors()[0];
        ctor.setAccessible(true);
        Main main2 = (Main)ctor.newInstance();

        // Call main method
        TestUtilities.callPrivateMethod(main2.getClass(), new Class[] { args.getClass() }, "main", main2, args2);

        System.setOut(System.out);

        String outStr = outContent.toString();
        String[] arr_outStr = outStr.split("\n");

        assertEquals("Started\n" +
                "File names not provided, exiting\n", outStr);
        assertEquals(2, arr_outStr.length);
        assertEquals("File names not provided, exiting", arr_outStr[1]);
    }
}