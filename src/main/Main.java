package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		try{
			Image img16 = new Image(getClass().getResourceAsStream("title_16.png"));
			Image img32 = new Image(getClass().getResourceAsStream("title_32.png"));
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("/main.fxml"));
			Scene scene = new Scene(root);

			primaryStage.setTitle("MainWindow");
			primaryStage.getIcons().addAll(img16,img32);
			primaryStage.setMinHeight(400);
			primaryStage.setMinWidth(600);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
