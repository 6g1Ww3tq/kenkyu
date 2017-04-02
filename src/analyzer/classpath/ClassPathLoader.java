package analyzer.classpath;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ClassPathLoader {
	URL url;
	URLClassLoader loader;

	public ClassPathLoader(File path) throws MalformedURLException {
		URL url = path.toURI().toURL();
		URLClassLoader loader = new URLClassLoader(new URL[] { url });

		this.url = url;
		this.loader = loader;
	}

	public Class<?> getClass(String fqcn, boolean initialize) throws ClassNotFoundException {
		return Class.forName(fqcn, initialize, loader);
	}

	public void close() throws IOException {
		loader.close();
		url = null;
		System.gc();
	}

}
