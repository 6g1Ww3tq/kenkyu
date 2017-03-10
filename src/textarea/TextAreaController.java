package textarea;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.scene.control.TextArea;

public class TextAreaController {
	private static final int READ_ERROR = -1;
	private static TextArea ta;
	private static File folder;

	public static void setTextArea(TextArea ta) {
		TextAreaController.ta = ta;
	}

	public static void setFolder(File folder){
		TextAreaController.folder = folder;
	}

	public static void setText(File file,SETTYPE type) {
		// TODO 自動生成されたメソッド・スタブ
		FileReader fr = null;
		StringBuilder sb = new StringBuilder();
		int data = READ_ERROR;
		try {
			if (type == SETTYPE.FILE) {
				fr = new FileReader(file);
			} else {
//				System.out.println(folder.getName()+file.getName());
//				file = new File(folder.getName()+file.getName());
//				fr = new FileReader(file);
			}
			while ((data = fr.read()) != READ_ERROR) {
				sb.append((char)data);
			}
			ta.setText(sb.toString());
			fr.close();

		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public enum SETTYPE{
		FILE,FOLDER;
	}
}
