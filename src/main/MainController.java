package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import close.CloseWindow;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import version.VersionController;
import javafx.stage.Stage;
import tree.TreeItemData;
import tree.TreeViewController;

public class MainController extends Application {
	private static final int READ_ERROR = -1;

	private static TreeViewController controller;

	public void doAnalyze(ActionEvent event) {
		System.out.println("analyze..");
	}

	public String doSetting(ActionEvent event){
		System.out.println("setting..");
		return "hoge";
	}

	public void doOpenFolder(ActionEvent event){
		DirectoryChooser dirChooser = new DirectoryChooser();
		dirChooser.setTitle("Open Resource Directory");

		File openFile = dirChooser.showDialog(new Stage());
		if (openFile!=null) {
			controller.makeFolderTree(openFile);
		}
	}

	public void doOpenFile(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();

		fileChooser.getExtensionFilters().addAll(
				new ExtensionFilter("Java Files (*.java)", "*.java")
				,new ExtensionFilter("Class Files (*.class)", "*.class")
				,new ExtensionFilter("Fxml Files (*.fxml)","*.fxml"));
		fileChooser.setTitle("Open Resource File");

		List<File> list = fileChooser.showOpenMultipleDialog(new Stage());


		if (list != null) {
			try {
				int data;
				FileReader fr = null;
				for (File file : list) {
					fr = new FileReader(file);
					while ((data = fr.read()) != READ_ERROR) {
						System.out.print((char)data);
					}
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
	}

	public void doClose(ActionEvent event){
		CloseWindow closeDialog = new CloseWindow(this,"Exit?");
		closeDialog.display();
	}

	public void doAbout(ActionEvent event){
		VersionController vController = new VersionController();
		vController.display();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		try{
			final Image img16 = new Image(getClass().getResourceAsStream("treeviewsample16.jpg"));
			final Image img32 = new Image(getClass().getResourceAsStream("treeviewsample32.jpg"));

			Pane root = (Pane)FXMLLoader.load(getClass().getResource("/main.fxml"));
			Scene scene = new Scene(root);
			controller = new TreeViewController((TreeView<TreeItemData>) root.lookup("#treeview"));
			primaryStage.setTitle("MainWindow");
			primaryStage.setScene(scene);
			primaryStage.getIcons().addAll(img16,img32);
			primaryStage.setMinHeight(400);
			primaryStage.setMinWidth(600);
			primaryStage.show();
		}catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
