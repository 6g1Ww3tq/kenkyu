import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class ParserSample {

	public static void main(String[] args) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = null;

		try {
			db = dbf.newDocumentBuilder();
			try {
				TransformerFactory tf = TransformerFactory.newInstance();
				Transformer transformer = tf.newTransformer();
				Document document = db.parse(new File("src/sample.xml"));
				StringWriter sw = new StringWriter();
				StreamResult sr = new StreamResult(sw);
				DOMSource domSource = new DOMSource(document);
				transformer.transform(domSource, sr);

				tracceNodes(document);
			} catch (SAXException | IOException | TransformerException e) {
				e.printStackTrace();
			}

		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	static private void tracceNodes(Node node){
		Node child = node.getFirstChild();

		while(child!=null){
			printNode(child);

			tracceNodes(child);
			child = child.getNextSibling();
		}
	}

	static private void printNode(Node node){
		System.out.println("nodeName:"+node.getNodeName());
		System.out.println("nodeType:"+node.getNodeType());
		System.out.println("nodeValue:"+node.getNodeValue());
	}
}
