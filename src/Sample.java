import java.lang.reflect.Field;

import foo.Foo;

public class Sample {

	public static void main(String[] args) {
		ClassLoader classLoader = Hoge.class.getClassLoader();
		try {
			Class aClass = classLoader.loadClass("Hoge");
			Field[] fields = aClass.getDeclaredFields();
			
			for (Field field : fields) {
				field.setAccessible(true);
				System.out.println("FieldName:"+field);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
