package textarea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.scene.control.TextArea;
import type.SETTYPE;

public class TextAreaInputer {
	private  final int READ_ERROR = -1;
	private  TextArea ta;

	public TextAreaInputer(TextArea ta) {
		this.ta = ta;
	}

	public void setTextArea(TextArea ta) {
		this.ta = ta;
	}

	public void setText(File file,SETTYPE type) {
		FileReader fr = null;
		StringBuilder sb = new StringBuilder();
		int data = READ_ERROR;
		try {
			if (type == SETTYPE.FILE) {
				fr = new FileReader(file);
			}
			while ((data = fr.read()) != READ_ERROR) {
				sb.append((char)data);
			}
			ta.setText(sb.toString());
			fr.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
