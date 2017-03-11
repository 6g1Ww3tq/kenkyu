package main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import textarea.TextAreaInputer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tree.TreeLogic;
import type.SETTYPE;

public class MainController implements Initializable{
	private static final int DOUBLE_CLICK = 2;
	@FXML Text statuText;
	@FXML TreeView<String> treeview;
	@FXML BorderPane rootPane;
	@FXML TextArea textarea;
	@FXML TextField searchField;


	@FXML
	public void doActivePane(MouseEvent event) {
		Object obj = event.getSource();
		String str = obj.toString();
		int index = str.indexOf("[");
		String msg = str.substring(0, index);
		switch (msg) {
		case "TextField":
			msg = "search in treeview";
			break;
		case "TextArea":
			msg = "input";
			break;
		case "TreeView":
			msg = "view";
		default:
			break;
		}
		statuText.setText(msg);
	}

	@FXML
	public void doAnalyze(ActionEvent event) {
		System.out.println("analyze..");
	}

	@FXML
	public String doSetting(ActionEvent event){
		System.out.println("setting..");
		return "hoge";
	}

	@FXML
	public void doOpenFolder(ActionEvent event){
		DirectoryChooser dirChooser = new DirectoryChooser();
		dirChooser.setTitle("Open Resource Directory");
		TreeLogic tl = null;

		File openFile = dirChooser.showDialog(new Stage());
		if (openFile!=null) {
			tl = new TreeLogic();
			tl.makeTree(tl.getRoot(), openFile);
			treeview.setRoot(tl.getRoot());
		}

	}

	@FXML
	public void doOpenFile(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();

		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Java Files (*.java)", "*.java")
				,new ExtensionFilter("Class Files (*.class)", "*.class")
				,new ExtensionFilter("Fxml Files (*.fxml)","*.fxml"));
		fileChooser.setTitle("Open Resource File");

		List<File> list = fileChooser.showOpenMultipleDialog(new Stage());


		if (list != null) {
			for (File file : list) {
				setTextArea(file);
			}
		}
	}

	@FXML
	public void pressedTreeItem(MouseEvent event){
		if (event.getClickCount()==DOUBLE_CLICK) {
			String path = null;
			TreeItem<String> treeItem = treeview.getSelectionModel().getSelectedItem();
			path = treeItem.toString();
			if (path!=null) {
				setTextArea(new File(path));
			}
		}
	}

	private void setTextArea(File file) {
		if (file.isFile()) {
			TextAreaInputer tai = new TextAreaInputer(textarea);
			tai.setText(file, SETTYPE.FILE);
		}
	}

	@FXML
	public void doClose(ActionEvent event){
		loadDialogWindow("/closeDialog.fxml", "Exit ?", 100, 200);
	}

	@FXML
	public void doAbout(ActionEvent event){
		loadDialogWindow("/version.fxml", "Version", 150, 300);
	}

	private void loadWindow(String loc,String title) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(loc));
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setTitle(title);
			stage.setScene(new Scene(parent));
			stage.show();
		} catch (IOException ex) {
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void loadDialogWindow(String loc,String title,double height,double width) {
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(loc));
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.initOwner(rootPane.getScene().getWindow());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle(title);
			stage.setScene(new Scene(parent));
			//stage.setResizable(false);
			stage.setMinHeight(height);
			stage.setMaxHeight(height);
			stage.setMaxWidth(width);
			stage.setMinWidth(width);
			stage.showAndWait();
		}catch (IOException ex) {
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	}
}
