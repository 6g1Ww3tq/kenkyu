package version;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class VersionController{
	Stage primaryStage;
	@FXML Button okButton;

	public VersionController() {
		// TODO Auto-generated constructor stub
		this.primaryStage = new Stage();
		primaryStage.setResizable(false);
		primaryStage.setTitle("Version");
		setSize(150,300);
		primaryStage.initModality(Modality.APPLICATION_MODAL);
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
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
