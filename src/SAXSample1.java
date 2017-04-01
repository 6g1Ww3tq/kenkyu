import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class SAXSample1 {
	protected static final String PARSER_NAME = "com.sun.org.apache.xerces.internal.parsers.SAXParser";

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(SAXSample1.class);
		String fileName = "src/sample.xml";

		DOMConfigurator.configure("src/log4j.xml");
		XMLReader parser;
		try {
			logger.info("start:SAXSample1");
			parser = XMLReaderFactory.createXMLReader(PARSER_NAME);
			SampleSAXHandler sh = new SampleSAXHandler();
			parser.setContentHandler(sh);
			parser.parse(fileName);
			logger.info("end:SAXSample1");
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
	}

}
