package testutilities;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

    /**
     * Method to invoke a private method of an object
     * @param TargetClass Class of the object
     * @param argClasses Classes of method arguments
     * @param methodName Method name
     * @param targetObject The object
     * @param argObjects The arguments
     * @return Method value
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws NoSuchMethodException
     */
    public static Object callPrivateMethod(Class TargetClass, Class[] argClasses, String methodName, Object targetObject, Object[] argObjects) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        Method method = TargetClass.getDeclaredMethod(methodName, argClasses);
        method.setAccessible(true);
        return method.invoke(targetObject, argObjects);
    }
}
