package analyzer.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Analyzer {
	Class<?> clazz;
	StringBuilder sb;

	public Analyzer(Class<?> clazz) {
		this.clazz = clazz;
		this.sb = new StringBuilder();
	}
	
	@Override
	public String toString() {
		return sb.toString();
	}

	public void doAnalyze() {
		Field fields[] = clazz.getDeclaredFields();
		Constructor<?> constructers[] = clazz.getDeclaredConstructors();
		Method methods[] = clazz.getDeclaredMethods();

		sb.append(clazz.toString());

		for (Field field : fields) {
			sb.append(field.toString()+'\n');
		}

		for (Constructor<?> constructor : constructers) {
			sb.append(constructor.toString());
		}

		for (Method method : methods) {
			sb.append(method.toString()+'\n');
		}
	}

}
