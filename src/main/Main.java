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
		// TODO Auto-generated method stub
		try{
			Image img16 = new Image(getClass().getResourceAsStream("treeviewsample16.jpg"));
			Image img32 = new Image(getClass().getResourceAsStream("treeviewsample32.jpg"));
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("/main.fxml"));
			Scene scene = new Scene(root);

			primaryStage.setTitle("MainWindow");
			primaryStage.getIcons().addAll(img16,img32);
			primaryStage.setMinHeight(400);
			primaryStage.setMinWidth(600);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
