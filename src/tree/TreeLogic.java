package tree;

import java.io.File;

//import javafx.scene.input.MouseEvent;
import type.SETTYPE;

public class TreeLogic {
	private TreeItemString root;

	public TreeLogic() {
		root = new TreeItemString("Root",SETTYPE.FOLDER);
		root.setExpanded(true);
	}

	public TreeItemString makeTree(TreeItemString rootNode,File currentFile){
		TreeItemString node = null;
		File[] files = null;

		rootNode.setExpanded(true);

		files = currentFile.listFiles();

		for (File file : files) {
			if (file.isDirectory()) {
				node = new TreeItemString(file.getName(),SETTYPE.FOLDER);
				node.setFile(file);
				node.getChildren().add(makeTree(node,file));
			}else{
				node = new TreeItemString(file.getName(),SETTYPE.FILE);
				node.setFile(file);
//				node.addEventHandler(MouseEvent.MOUSE_PRESSED,new TreeItemMouseEvent());
			}
			rootNode.getChildren().add(node);
		}

		if (files!=null) {
			node = null;
		}
		return node;
	}

	public TreeItemString getRoot() {
		return root;
	}
}
