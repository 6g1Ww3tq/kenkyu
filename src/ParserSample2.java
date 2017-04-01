import java.io.IOException;
import java.util.regex.Pattern;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class ParserSample2 {
	public static void main(String[] args) {
		try {
			DOMParser parser = new DOMParser();
			parser.parse("src/sample.xml");
			Document document = parser.getDocument();

			tracceNodes(document);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
	}

	static private void tracceNodes(Node node){
		Node child = node.getFirstChild();

		while(child!=null){
			//printNode(child);
			//printTextNode(child);
			printAttribute(child);

			tracceNodes(child);
			child = child.getNextSibling();
		}
	}

	static private void printNode(Node node){
		System.out.println("nodeName:"+node.getNodeName());
		System.out.println("nodeType:"+node.getNodeType());
		System.out.println("nodeValue:"+node.getNodeValue());
	}

	static private void printTextNode(Node node){
		if (node.getNodeType() == Node.TEXT_NODE && !Pattern.matches("[\\s]+", node.getNodeValue())) {
			System.out.println("Type:"+node.getNodeType());
			System.out.println("Value:"+node.getNodeValue());
		}
	}

	static private void printAttribute(Node node){
		NamedNodeMap attMap = node.getAttributes();

		if (attMap!=null) {
			for (int index = 0; index < attMap.getLength(); index++) {
				System.out.println("AttributeName:"+attMap.item(index).getNodeName());
				System.out.println("AttributeValue:"+attMap.item(index).getNodeValue());
			}
		}
	}

}
