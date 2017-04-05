package window.load;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import window.alertWindow.alertWindowController;

public class LoadWindow {
	private BorderPane rootBorderPane;
	private boolean isOpened;
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoadWindow.class.getName());

	public LoadWindow(BorderPane rootBorderPane) {
		this.rootBorderPane = rootBorderPane;
	}
	
	public boolean loadAlertWindow(String locFXML,String windowTitle,Error error) {
		try {
			alertWindowController.setException(error);
			Parent parent = FXMLLoader.load(getClass().getResource(locFXML));
			Stage stage = new Stage(StageStyle.DECORATED);
			isOpened = true;

			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					isOpened = false;
				}
			});
			stage.setTitle(windowTitle);
			stage.setScene(new Scene(parent));
			stage.show();
		} catch (IOException ex) {
			Logger.getLogger(LoadWindow.class.getName()).log(Level.SEVERE, null, ex);
			isOpened = false;
		}

		return isOpened;
	}

	public boolean loadAlertWindow(String locFXML,String windowTitle,Exception exception) {
		try {
			alertWindowController.setException(exception);
			Parent parent = FXMLLoader.load(getClass().getResource(locFXML));
			Stage stage = new Stage(StageStyle.DECORATED);
			isOpened = true;

			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					isOpened = false;
				}
			});
			stage.setTitle(windowTitle);
			stage.setScene(new Scene(parent));
			stage.show();
		} catch (IOException ex) {
			Logger.getLogger(LoadWindow.class.getName()).log(Level.SEVERE, null, ex);
			isOpened = false;
		}

		return isOpened;
	}

	public boolean loadDialogWindow(String locFXML,String windowTitle,double height,double width) {
		logger.info("loadDialog Window");
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(locFXML));
			Stage stage = new Stage(StageStyle.DECORATED);
			isOpened = true;
			stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent event) {
					isOpened = false;
				}
			});
			stage.initOwner(rootBorderPane.getScene().getWindow());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle(windowTitle);
			stage.setScene(new Scene(parent));
			stage.setMinHeight(height);
			stage.setMaxHeight(height);
			stage.setMaxWidth(width);
			stage.setMinWidth(width);
			stage.showAndWait();

		}catch (IOException ex) {
			Logger.getLogger(LoadWindow.class.getName()).log(Level.SEVERE, null, ex);
			isOpened = false;
		}

		return isOpened;
	}
}
