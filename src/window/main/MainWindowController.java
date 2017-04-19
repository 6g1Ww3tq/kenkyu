package window.main;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import analyzer.classpath.ClassPathLoader;
import analyzer.reflect.Analyzer;
import analyzer.xmlBuilder.XMLBuilder;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
import javafx.stage.Stage;
import type.FILETYPE;
import window.load.LoadWindow;
import window.messageWindow.MessageWindowController;
import window.textarea.TextAreaInputer;
import window.tree.TreeLogic;

public class MainWindowController implements Initializable{
	private static final int DOUBLE_CLICK = 2;
	private static String folderFormat;
	private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(MainWindowController.class.getName());

	@FXML Text statuText;
	@FXML TreeView<String> treeview;
	@FXML BorderPane rootPane;
	@FXML TextArea textarea;
	@FXML TextField searchField;

	private String fileName;
	private File openFile;
	private LoadWindow lw;

	@FXML
	public void doActivePane(MouseEvent event) {
		Object obj = event.getSource();
		if (obj instanceof Button) {
			return ;
		}

		String str = obj.toString();
		int index = str.indexOf("[");
		String msg = str.substring(0, index);
		if (msg.equals("TextField")) {
			msg = "Input FQCN (ex. sample.Main)";
		}else if (msg.equals("TextArea")) {
			msg = fileName;
		}else if (msg.equals("TreeView")) {
			msg = "view";
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

			MessageWindowController.setTextMsg(analyzer.toString());
			lw.loadDialogWindow("/window/messageWindow/messageWindow.fxml", clazz.getName(), 300, 500);

			cpm.close();
		} catch (IOException | ClassNotFoundException | SecurityException | IllegalArgumentException | NullPointerException exception) {
			lw.loadAlertWindow("/window/alertWindow/alertWindow.fxml", "Alert", exception);
		} catch (NoClassDefFoundError error){
			lw.loadAlertWindow("/window/alertWindow/alertWindow.fxml", "Alert", error);
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

			MessageWindowController.setTextMsg(xmlb.toString());
			lw.loadDialogWindow("/window/messageWindow/messageWindow.fxml", clazz.getName(), 300, 500);

		} catch (IOException | ClassNotFoundException | SecurityException | IllegalArgumentException | NullPointerException | ParserConfigurationException | TransformerException exception) {
			lw.loadAlertWindow("/window/alertWindow/alertWindow.fxml", "Alert", exception);
		} catch (NoClassDefFoundError error){
			lw.loadAlertWindow("/window/alertWindow/alertWindow.fxml", "Alert", error);
		}
	}

	public static void setFolderFormat(String folderFormat) {
		MainWindowController.folderFormat = folderFormat;
	}

	@FXML
	public void doOpenFolder(ActionEvent event){
		lw = new LoadWindow(rootPane);
		DirectoryChooser dirChooser = new DirectoryChooser();
		dirChooser.setTitle("Open Resource Directory");
		TreeLogic tl = null;
		Stage chooseWindow = new Stage();

		openFile = dirChooser.showDialog(chooseWindow);

		if (openFile!=null) {
			logger.info("Start OpenFolder");
			if(lw.loadDialogWindow("/window/format/fileformat.fxml", "Choose File Format", 150, 300)){
				tl = new TreeLogic(folderFormat);
				tl.makeTree(tl.getRoot(), openFile);
				treeview.setRoot(tl.getRoot());
			}
		}

	}

	@FXML
	public void doOpenFile(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		Stage chooseWindow = new Stage();

		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Java Files (*.java)", "*.java")
				,new ExtensionFilter("Class Files (*.class)", "*.class")
				,new ExtensionFilter("Fxml Files (*.fxml)","*.fxml"));
		fileChooser.setTitle("Open Resource File");

		openFile = fileChooser.showOpenDialog(chooseWindow);
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

	@FXML
	public void doClose(ActionEvent event){
		lw.loadDialogWindow("/window/close/closeDialog.fxml", "Exit ?", 100, 200);
	}

	@FXML
	public void doAbout(ActionEvent event){
		lw.loadDialogWindow("/window/version/version.fxml", "Version", 150, 300);
	}

	private void setTextArea(File file) {
		if (file.isFile()) {
			fileName = file.getName();
			TextAreaInputer tai = new TextAreaInputer(textarea);
			tai.setText(file, FILETYPE.FILE);
		}
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lw = new LoadWindow(rootPane);
		setFolderFormat("*");

		openFile = null;
	}
}
