import javax.xml.bind.JAXB;

public class JAXBSample {

	public static void main(String[] args) {
		Hoge hoge = new Hoge();
		hoge.setId(10);
		hoge.setValue("hoge");
		
		JAXB.marshal(hoge, System.out);
	}
}
