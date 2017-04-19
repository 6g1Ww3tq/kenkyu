package window.main;

import org.apache.log4j.Logger;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class VerifyApplication extends Application {
	private static Logger logger = Logger.getLogger(VerifyApplication.class.getName());

	public static Logger getLogger(){
		return VerifyApplication.logger;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try{
			logger.info("Application Start");
			Image img16 = new Image(getClass().getResourceAsStream("/window/icons/title_16.png"));
			Image img32 = new Image(getClass().getResourceAsStream("/window/icons/title_32.png"));
			Pane root = (Pane)FXMLLoader.load(getClass().getResource("/window/main/mainWindow.fxml"));
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
		launch();
	}
}
