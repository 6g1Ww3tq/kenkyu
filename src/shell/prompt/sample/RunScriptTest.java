package shell.prompt.sample;
import java.io.IOException;
import java.io.InputStream;

public class RunScriptTest {

	public static void main(String[] args) {
		try {
			String[] cmd = {
					"sh",
					"src/shell/prompt/sample/test.sh"
			};

			Process p = Runtime.getRuntime().exec(cmd);
			InputStream es = p.getErrorStream();
			InputStream is = p.getInputStream();
			int ch = -1;
			StringBuffer sb = new StringBuffer();

			while ((ch = is.read()) != -1) {
				sb.append((char)ch);
			}

			while ((ch = es.read()) != -1) {
				sb.append((char)ch);
			}

			System.out.println(sb.toString());

			is.close();
			es.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
