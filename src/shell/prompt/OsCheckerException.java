package shell.prompt;

import java.io.PrintWriter;

public class OsCheckerException extends Throwable {
	/**
	 * SerialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	String msg;
	
	public OsCheckerException(String msg) {
		this.msg = msg;
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		StringBuilder sb = new StringBuilder();
		sb.append("OsCheckerException : " + msg);

		s.write(sb.toString());
		super.printStackTrace(s);
	}
}