package close;


import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MyDialog{
	@FXML Text dialog_msg;
	Stage primaryStage;

	public MyDialog(String msg) {
		// TODO Auto-generated constructor stub
		primaryStage = new Stage();
		primaryStage.setTitle(msg);
		setSize(70, 150);
		primaryStage.initModality(Modality.APPLICATION_MODAL);
	}
	
	private void setSize(double height,double width) {
		primaryStage.setMinHeight(height);
		primaryStage.setMaxHeight(height);
		primaryStage.setMaxWidth(width);
		primaryStage.setMinWidth(width);
	}

}
