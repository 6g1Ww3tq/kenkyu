package close;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CloseWindow extends MyDialog{
	Application mainWindow;

	public CloseWindow(Application mainWindow,String msg) {
		// TODO 自動生成されたコンストラクター・スタブ
		super(msg);
		this.mainWindow = mainWindow;
	}

	public void display() {
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("/closeDialog.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.setMinHeight(100);
			primaryStage.setMinWidth(200);
			primaryStage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}