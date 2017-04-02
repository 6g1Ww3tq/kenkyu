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

public class XMLBuilder {
	Class<?> clazz;
	StringBuilder sb;
	Document document;
	Element root;

	public XMLBuilder(Class<?> clazz) throws ParserConfigurationException {
		this.clazz = clazz;
		this.sb = new StringBuilder();
		docInit();
	}

	private void docInit() throws ParserConfigurationException{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbFactory.newDocumentBuilder();
		document = db.newDocument();
	}

	public void doXMLBuilder() throws TransformerException, IOException{
		TransformerFactory tFactory = TransformerFactory.newInstance();
		Transformer transformer = tFactory.newTransformer();
		StringWriter sw = new StringWriter();
		
		setClassElement();
		setFieldElement();
		setConstructorElement();
		setMethodElement();
		
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
//		transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "http://"
//				+ "jakarta.apache.org/struts/dtds/struts-config_1_1.dtd");
		transformer.transform(new DOMSource(document), new StreamResult(sw));

		sb.append(sw.toString());
		sw.close();
	}

	private void setClassElement() {
		root = document.createElement(clazz.getName());
		document.appendChild(root);
	}

	private void setFieldElement() {
		Field fields[] = clazz.getDeclaredFields();
		Element element = null;

		for (Field field : fields) {
			field.setAccessible(true);
			element = document.createElement(field.getName());
			root.appendChild(element);
		}
	}

	private void setConstructorElement() {
		Constructor<?> constructors[] = clazz.getDeclaredConstructors();
		Element element = null;

		for (Constructor<?> constructor : constructors) {
			constructor.setAccessible(true);
			element = document.createElement(constructor.getName());
			root.appendChild(element);
		}
	}

	private void setMethodElement() {
		Method methods[] = clazz.getDeclaredMethods();
		Element element = null;

		for (Method method : methods) {
			method.setAccessible(true);
			element = document.createElement(method.getName());
			root.appendChild(element);
		}
	}

	@Override
	public String toString() {
		return sb.toString();
	}
}