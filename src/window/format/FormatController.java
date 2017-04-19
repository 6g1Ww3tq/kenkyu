package window.format;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import window.main.MainWindowController;

public class FormatController implements Initializable{

	@FXML
	private MenuButton menuButton;
	@FXML
	private Button okButton;

	private MenuItem asta;
	private MenuItem javaItem;
	private MenuItem classItem;

	@FXML
	void doOK(ActionEvent event) {
		Stage stage = (Stage) okButton.getScene().getWindow();
		stage.close();
		String format = menuButton.getText();
		MainWindowController.setFolderFormat(format);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		asta = new MenuItem("*");
		asta.setOnAction(new MenuItemEvent(menuButton));
		javaItem = new MenuItem("java");
		javaItem.setOnAction(new MenuItemEvent(menuButton));
		classItem = new MenuItem("class");
		classItem.setOnAction(new MenuItemEvent(menuButton));
		menuButton.getItems().addAll(
				FXCollections.observableArrayList(asta,javaItem,classItem)
				);
	}

} 