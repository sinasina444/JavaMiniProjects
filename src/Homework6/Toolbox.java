package Homework6;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * User: blangel
 * Date: 10/11/17
 * Time: 8:44 AM
 */
public class Toolbox {

    public WireColor getColor(Wire wire) throws ToolMisuseException {
        if (!isColorVisible(wire)) {
            throw new ToolMisuseException();
        }
        if (!(wire instanceof Colored)) {
            throw new ToolMisuseException();
        }
        Colored colored = (Colored) wire;
        return colored.getColor();
    }

    private boolean isColorVisible(Wire wire) {
        for (Class<?> interfaceType : wire.getClass().getInterfaces()) {
            if (interfaceType.isAnnotationPresent(ColorVisible.class)) {
                return true;
            }
        }
        return false;
    }

    public Object invokeMethod(Object instance, String methodName) {
        if ((instance == null) || (methodName == null)) {
            throw new IllegalArgumentException();
        }
        try {
            Method method = instance.getClass().getMethod(methodName);
            method.setAccessible(true);
            return method.invoke(instance);
        } catch (NoSuchMethodException nsme) {
            return null;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new AssertionError(e);
        }
    }

    public void setField(Object instance, String fieldName, Object value) {
        if ((instance == null) || (fieldName == null) || (value == null)) {
            throw new IllegalArgumentException();
        }
        try {
            Field field = instance.getClass().getDeclaredField(fieldName);
            System.out.println("field= " + field);
            field.setAccessible(true);
            field.set(instance, value);
        } catch (Throwable t) {
            throw new AssertionError(t);
        }
    }

}
