import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.ext.DefaultHandler2;

public class SampleSAXHandler extends DefaultHandler2 {
	@Override
	public void startDocument() throws SAXException {
		System.out.println("Documentの開始です。");
	}
	
	@Override
	public void endDocument() throws SAXException {
		System.out.println("Documentの終了です。");
	}
	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("要素名:"+localName+"の開始です");
		
		/*
		 * attributes.getLength()でAttributesに含まれる属性の数を得ます。
		 */
		int length = attributes.getLength();

		/*
		 * 属性の数だけ、属性名と属性値を取得、表示します。
		 */
		for (int i = 0; i < length; i++) {
			System.out.println(attributes.getLocalName(i)+"="+attributes.getValue(i));
		}
		
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		System.out.println(new String(ch, start, length));
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		System.out.println("要素名:"+localName+"の終了です");
	}
}
