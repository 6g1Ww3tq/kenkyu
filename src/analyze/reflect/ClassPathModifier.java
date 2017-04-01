package analyze.reflect;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassPathModifier {

	public static void addClassPath(ClassLoader classLoadr,File path) throws IOException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException ,NullPointerException{
		if (classLoadr instanceof URLClassLoader) {
			Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			method.setAccessible(true);

			method.invoke(classLoadr, path.toURI().toURL());
		}
	}
}
