package close;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class CloseDialogController {
	@FXML Button yesButton;
	@FXML Button noButton;

	@FXML
	public void exit(ActionEvent event){
		if (event.getSource() == yesButton) {
			Platform.exit();
		}else{
			Stage stage = (Stage) noButton.getScene().getWindow();
			stage.close();
		}
	}

}
