package testutilities;

import java.lang.reflect.Field;

public class TestUtilities {

    /**
     * Method to extract a private property value from an object
     * using reflection
     * @param fieldName Name of the field
     * @param obj The object to query
     * @param <F> Type of the field
     * @return Field value as F
     * @throws NoSuchFieldException If the object doesn't contain the named field
     * @throws IllegalAccessException If the field cannot be accessed
     */
    public static <F> F getPrivateField(String fieldName, Object obj) throws NoSuchFieldException, IllegalAccessException {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        return (F)field.get(obj);
    }

}
