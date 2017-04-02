package main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import alertWindow.alertWindowController;
import analyzer.classpath.ClassPathLoader;
import analyzer.reflect.Analyzer;
import analyzer.xmlBuilder.XMLBuilder;
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
			msg = "Input FQCN (ex. sample.Main)";
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
			ClassPathLoader cpm = new ClassPathLoader(openFile);
			Class<?> clazz = null;
			String fqcn = null;
			Analyzer analyzer = null;

			fqcn = searchField.getText();
			clazz = cpm.getClass(fqcn, true);
			analyzer = new Analyzer(clazz);

			analyzer.doAnalyze();

			System.out.println(analyzer.toString());
			statuText.setText(clazz.getName() + " : analyze");

			cpm.close();
		} catch (IOException | ClassNotFoundException | SecurityException | IllegalArgumentException | NullPointerException exception) {
			loadAlertWindow("/alertWindow/alertWindow.fxml", "Alert", exception);
		} catch (NoClassDefFoundError error){
			loadAlertWindow("/alertWindow/alertWindow.fxml", "Alert", error);
		}
	}

	@FXML
	public void doXML(ActionEvent event){
		try{
			ClassPathLoader cpm = new ClassPathLoader(openFile);
			Class<?> clazz = null;
			String fqcn = null;
			XMLBuilder xmlb = null;

			fqcn = searchField.getText();
			clazz = cpm.getClass(fqcn, true);
			xmlb = new XMLBuilder(clazz);
			
			xmlb.doXMLBuilder();

			System.out.println(xmlb.toString());
		} catch (IOException | ClassNotFoundException | SecurityException | IllegalArgumentException | NullPointerException | ParserConfigurationException | TransformerException exception) {
			loadAlertWindow("/alertWindow/alertWindow.fxml", "Alert", exception);
		} catch (NoClassDefFoundError error){
			loadAlertWindow("/alertWindow/alertWindow.fxml", "Alert", error);
		}
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

	private void loadAlertWindow(String locFXML,String windowTitle,Error error) {
		try {
			alertWindowController.setException(error);
			Parent parent = FXMLLoader.load(getClass().getResource(locFXML));
			Stage stage = new Stage(StageStyle.DECORATED);
			stage.setTitle(windowTitle);
			stage.setScene(new Scene(parent));
			stage.show();
		} catch (IOException ex) {
			Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
		}
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
