package tree;

import java.io.File;

import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class TreeLogic {
	private Image FOLDER = new Image(getClass().getResourceAsStream("../icons/folder.gif"));
	private Image FILE = new Image(getClass().getResourceAsStream("../icons/file.gif"));
	private TreeItem<String> root;

	public TreeLogic() {
		// TODO 自動生成されたコンストラクター・スタブ
		root = new TreeItem<>("Root",new ImageView(FOLDER));
		root.setExpanded(true);
	}

	public TreeItem<String> makeTree(TreeItem<String> rootNode,File currentFile){
		TreeItem<String> node = null;
		File[] files = null;

		rootNode.setExpanded(true);

		files = currentFile.listFiles();

		for (File file : files) {
			if (file.isDirectory()) {
				node = new TreeItem<String>(file.getName(),new ImageView(FOLDER));
				node.getChildren().add(makeTree(node,file));
			}else{
				node = new TreeItem<String>(file.getName(),new ImageView(FILE));
				node.addEventHandler(MouseEvent.MOUSE_PRESSED,new TreeItemMouseEvent());
			}
			rootNode.getChildren().add(node);
		}

		if (files!=null) {
			node = null;
		}
		return node;
	}

	public TreeItem<String> getRoot() {
		return root;
	}
}
