package version;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class VersionController{
	Stage primaryStage;
	@FXML Button okButton;

	public VersionController() {
		// TODO Auto-generated constructor stub
		primaryStage = new Stage(StageStyle.UTILITY);
		primaryStage.setTitle("Version");
		primaryStage.initModality(Modality.APPLICATION_MODAL);
		setSize(150,300);
	}

	private void setSize(double height,double width) {
		primaryStage.setMinHeight(height);
		primaryStage.setMaxHeight(height);
		primaryStage.setMaxWidth(width);
		primaryStage.setMinWidth(width);
	}

	@FXML
	public void doOK() {
		// TODO Auto-generated method stub
		Stage stage = (Stage) okButton.getScene().getWindow();
		stage.close();
	}

	public void display() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/version.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
