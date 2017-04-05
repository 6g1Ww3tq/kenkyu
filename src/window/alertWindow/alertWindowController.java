package window.alertWindow;

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
	private static Error error;

	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(alertWindowController.class.getName());

	public static void setException(Error error) {
		alertWindowController.error = error;
	}

	public static void setException(Exception exception) {
		alertWindowController.exception = exception;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		textArea.setEditable(false);
		
		if (exception != null) {
			exception.printStackTrace(pw);
			logger.error("ERROR:");
			logger.error(exception.toString());
		}

		if (error != null) {
			error.printStackTrace(pw);
			logger.error("ERROR:");
			logger.error(error.toString());
		}
		pw.flush();

		textArea.setText(sw.toString());

		pw.close();
		exception = null;
		error = null;
	}

	@FXML
	public void close() {
		Stage stage = (Stage) okButton.getScene().getWindow();
		stage.close();
	}
}
