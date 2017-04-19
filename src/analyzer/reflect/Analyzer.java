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

		sb.append("ClassName:"+'\n');
		sb.append("------------------------------------\n");
		sb.append(clazz.toString()+'\n');
		sb.append("------------------------------------\n\n");

		sb.append("Fileds:"+'\n');
		sb.append("------------------------------------\n");
		for (Field field : fields) {
			sb.append(field.toString()+'\n');
		}
		sb.append("------------------------------------\n\n");

		sb.append("Constructors:"+'\n');
		sb.append("------------------------------------\n");
		for (Constructor<?> constructor : constructers) {
			sb.append(constructor.toString()+'\n');
		}
		sb.append("------------------------------------\n\n");

		sb.append("Methods:"+'\n');
		sb.append("------------------------------------\n");
		for (Method method : methods) {
			sb.append(method.toString()+'\n');
		}
		sb.append("------------------------------------\n\n");
	}

}
