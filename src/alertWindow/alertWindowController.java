package alertWindow;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class alertWindowController implements Initializable{
	@FXML TextArea textArea;
	@FXML Button okButton;

	private static Exception exception;
	
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(alertWindowController.class.getName());

	public static void setException(Exception exception) {
		alertWindowController.exception = exception;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exception.printStackTrace(pw);
		pw.flush();
		
		textArea.setText(sw.toString());
		logger.error("ERROR");
		logger.error(exception.toString());
	}

	@FXML
	public void close() {
		Stage stage = (Stage) okButton.getScene().getWindow();
		stage.close();
	}
}
