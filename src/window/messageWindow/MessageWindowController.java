package window.messageWindow;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

public class MessageWindowController implements Initializable {
	@FXML TextArea textArea;
	
	private static String textMsg;
	
	public static void setTextMsg(String textMsg) {
		MessageWindowController.textMsg = textMsg;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		textArea.setEditable(false);
		textArea.setText(textMsg);
	}

}
