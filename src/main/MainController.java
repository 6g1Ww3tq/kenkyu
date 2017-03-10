package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import tree.TreeItemData;
import tree.TreeViewController;

public class MainController implements Initializable{
	@FXML Text statuText;
	@FXML TreeView<TreeItemData> treeview;
	@FXML BorderPane rootPane;
	@FXML TextArea textarea;

	private static final int READ_ERROR = -1;
	private static TreeViewController controller;

	@FXML
	public void doActive(MouseEvent event) {
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

		File openFile = dirChooser.showDialog(new Stage());
		if (openFile!=null) {
			controller.makeFolderTree(openFile);
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

	private void setTextArea(File file) {
		FileReader fr = null;
		StringBuilder sb = new StringBuilder();
		int data = READ_ERROR;
		try {
			if (file.isFile()) {
				fr = new FileReader(file);
				while ((data = fr.read()) != READ_ERROR) {
					sb.append((char)data);
				}
				textarea.setText(sb.toString());
				fr.close();
			}
		} catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
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
			stage.setTitle(title);
			stage.setScene(new Scene(parent));
			stage.setMinHeight(height);
			stage.setMaxHeight(height);
			stage.setMaxWidth(width);
			stage.setMinWidth(width);
			stage.show();
		}catch (IOException ex) {
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
			controller = new TreeViewController(treeview);
	}

}
