package tree;

import java.io.File;

//import javafx.scene.input.MouseEvent;
import type.SETTYPE;

public class TreeLogic {
	private TreeItemString root;
	private String regex;

	public TreeLogic(String format) {
		root = new TreeItemString("Root",SETTYPE.FOLDER);
		if (format.equals("*")) {
			format = ".*";
		}else{
			format = ".*" + format;
		}
		this.regex = format;
	}

	public TreeItemString makeTree(TreeItemString rootNode,File currentFile){
		TreeItemString node = null;;
		File[] files = null;

		files = currentFile.listFiles();

		for (File file : files) {
			if (file.isDirectory()) {
				node = new TreeItemString(file.getName(),SETTYPE.FOLDER);
				node.setFile(file);
				node.getChildren().add(makeTree(node,file));
			}else{
				if (file.getName().matches(regex)) {
					node = new TreeItemString(file.getName(),SETTYPE.FILE);
					node.setFile(file);
				}
			}

			if (node!=null) {
				rootNode.getChildren().add(node);
			}

			node = null;
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
