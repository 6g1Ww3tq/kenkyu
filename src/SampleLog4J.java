import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class SampleLog4J {
	private Logger logger = Logger.getLogger(SampleLog4J.class.getName());

	void runSample(){
		DOMConfigurator.configure("src/log4j.xml");

		int a = 1;
		int b = 2;
		int c = 3;
		int d = 4;

		logger.trace("test:"+a);
		logger.debug("test:"+a);
		logger.info("test:"+b);
		logger.warn("test:"+c);
		logger.error("test:"+d);
	}

	public static void main(String[] args) {
		SampleLog4J s = new SampleLog4J();
		s.runSample();
	}
}
