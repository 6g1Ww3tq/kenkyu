package analyzer.xmlBuilder;

import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import analyzer.reflect.ModifierBuilder;

public class ClassXmlBuilder {
	Class<?> clazz;
	StringBuilder sb;
	Document document;
	Element root;

	public ClassXmlBuilder(Class<?> clazz) throws ParserConfigurationException {
		this.clazz = clazz;
		this.sb = new StringBuilder();
		docInit();
	}

	private void docInit() throws ParserConfigurationException{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbFactory.newDocumentBuilder();
		document = db.newDocument();
		root = document.createElement("Root");
		document.appendChild(root);
	}

	public void build() throws TransformerException, IOException{
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		StringWriter sw = new StringWriter();

		Element clazzElement = setClassElement();
		setFieldElement(clazzElement);
		setConstructorElement(clazzElement);
		setMethodElement(clazzElement);

		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		//		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://"
		//				+ "jakarta.apache.org/struts/dtds/struts-config_1_1.dtd");
		transformer.transform(new DOMSource(document), new StreamResult(sw));

		sb.append(sw.toString());
		sw.close();
	}

	private Element setClassElement() {
		Element element = document.createElement("Class");
		int fieldModifiers = clazz.getModifiers();
		ModifierBuilder mb = new ModifierBuilder(fieldModifiers);

		element.setAttribute("modifiers", mb.toString());
		element.setAttribute("name", clazz.getName());
		root.appendChild(element);
		return element;
	}

	private void setFieldElement(Element clazzElement) {
		Element element = null;
		Element fieldTag = null;
		Field fields[] = clazz.getDeclaredFields();
		Class<?> fieldType = null;
		int fieldModifiers;
		ModifierBuilder mb = null;

		fieldTag = document.createElement("Fields");

		for (Field field : fields) {
			field.setAccessible(true);
			fieldModifiers = field.getModifiers();
			mb = new ModifierBuilder(fieldModifiers);
			fieldType = field.getType();
			element = document.createElement(field.getName());
			element.setAttribute("type", fieldType.getName());
			element.setAttribute("modifiers", mb.toString());
			fieldTag.appendChild(element);
		}
		clazzElement.appendChild(fieldTag);
	}

	private void setConstructorElement(Element clazzElement) {
		Element element = null;
		Element constructorTag = null;
		Constructor<?> constructors[] = clazz.getDeclaredConstructors();
		//		Class<?> constructorParams = null;
		int constructorModifier;
		ModifierBuilder mb = null;

		constructorTag = document.createElement("Constructors");

		for (Constructor<?> constructor : constructors) {
			constructor.setAccessible(true);
			constructorModifier = constructor.getModifiers();
			mb = new ModifierBuilder(constructorModifier);
			//			constructorParams = constructor.getTypeParameters();
			element = document.createElement(constructor.getName());
			element.setAttribute("modifiers", mb.toString());
			constructorTag.appendChild(element);
		}
		clazzElement.appendChild(constructorTag);
	}

	private void setMethodElement(Element clazzElement) {
		Element element = null;
		Element methodTag = null;
		Method methods[] = clazz.getDeclaredMethods();
		//		Class<?> methodParams = null;
		int methodModifier;
		ModifierBuilder mb = null;

		methodTag = document.createElement("Methods");

		for (Method method : methods) {
			method.setAccessible(true);
			methodModifier = method.getModifiers();
			mb = new ModifierBuilder(methodModifier);
			//			constructorParams = constructor.getTypeParameters();
			element = document.createElement(method.getName());
			element.setAttribute("modifiers", mb.toString());
			methodTag.appendChild(element);
		}
		clazzElement.appendChild(methodTag);
	}

	@Override
	public String toString() {
		return sb.toString();
	}
}