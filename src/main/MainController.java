package main;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import alertWindow.alertWindowController;
import analyze.reflect.ClassPathModifier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	private static String folderFormat;
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MainController.class.getName());
	private static ClassLoader classLoader = MainController.class.getClassLoader();

	@FXML Text statuText;
	@FXML TreeView<String> treeview;
	@FXML BorderPane rootPane;
	@FXML TextArea textarea;
	@FXML TextField searchField;

	private String fileName;
	private static File openFile;

	@FXML
	public void doActivePane(MouseEvent event) {
		Object obj = event.getSource();
		if (obj instanceof Button) {
			return ;
		}

		String str = obj.toString();
		int index = str.indexOf("[");
		String msg = str.substring(0, index);
		switch (msg) {
		case "TextField":
			msg = "search in treeview";
			break;
		case "TextArea":
			msg = fileName;
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
		try {
			ClassPathModifier.addClassPath(classLoader, openFile);
			Class<?> clazz = null;
			String fqcn = null;

			fqcn = searchField.getText();
			clazz = classLoader.loadClass(fqcn);

			Method methods[] = clazz.getDeclaredMethods();
			for (Method method : methods) {
				System.out.println(method);
			}
		} catch (IOException | ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NullPointerException e) {
			loadAlertWindow("/alertWindow/alertWindow.fxml", "Alert", e);
		}
	}

	@FXML
	public void doSetting(ActionEvent event){
		System.out.println("setting..");
	}

	public static void setFolderFormat(String folderFormat) {
		MainController.folderFormat = folderFormat;
	}

	@FXML
	public void doOpenFolder(ActionEvent event){
		DirectoryChooser dirChooser = new DirectoryChooser();
		dirChooser.setTitle("Open Resource Directory");
		TreeLogic tl = null;

		openFile = dirChooser.showDialog(new Stage());
		if (openFile!=null) {
			logger.info("Start OpenFolder");
			loadDialogWindow("/format/fileformat.fxml", "Choose File Format", 150, 300);
			tl = new TreeLogic(folderFormat);
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

		//List<File> list = fileChooser.showOpenMultipleDialog(new Stage());
		//if (list != null) {
		//logger.info("Start OpenFile");
		//for (File file : list) {
		//setTextArea(file);
		//}
		//}

		openFile = fileChooser.showOpenDialog(new Stage());
		if (openFile != null) {
			logger.info("Start OpenFile");
			setTextArea(openFile);
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
			fileName = file.getName();
			TextAreaInputer tai = new TextAreaInputer(textarea);
			tai.setText(file, SETTYPE.FILE);
		}
	}

	@FXML
	public void doClose(ActionEvent event){
		loadDialogWindow("/close/closeDialog.fxml", "Exit ?", 100, 200);
	}

	@FXML
	public void doAbout(ActionEvent event){
		loadDialogWindow("/version/version.fxml", "Version", 150, 300);
	}

	private void loadAlertWindow(String locFXML,String windowTitle,Exception exception) {
		try {
			alertWindowController.setException(exception);
			Parent parent = FXMLLoader.load(getClass().getResource(locFXML));
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setTitle(windowTitle);
			stage.setScene(new Scene(parent));
			stage.show();
		} catch (IOException ex) {
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void loadDialogWindow(String locFXML,String windowTitle,double height,double width) {
		logger.info("loadDialog Window");
		try {
			Parent parent = FXMLLoader.load(getClass().getResource(locFXML));
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.initOwner(rootPane.getScene().getWindow());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setTitle(windowTitle);
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

		setFolderFormat("*");

		openFile = null;
	}
}
