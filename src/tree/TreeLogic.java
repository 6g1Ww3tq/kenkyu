package tree;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseEvent;

public class TreeLogic {
	TreeItemData root;
	TreeItem<TreeItemData> rootNode;

	public TreeLogic(String path) {
		// TODO 自動生成されたコンストラクター・スタブ
		root = getNode(new File(path));
		rootNode = new TreeItem<>(new TreeItemData("Root", TreeItemData.Type.FOLDER));
		rootNode.setExpanded(true);
	}


	public TreeItem<TreeItemData> getRootNode() {
		return rootNode;
	}

	public TreeItemData getRoot() {
		return root;
	}

	public TreeItemData getNode(File rootFile){
		TreeItemData node = null;

		if (rootFile.isDirectory()) {
			Directory dir = new Directory(rootFile.getName());

			List<TreeItemData> children = new ArrayList<>();
			File[] childFiles = rootFile.listFiles();
			for (File child : childFiles) {
				children.add(getNode(child));
			}
			dir.setChildren(children);

			node = dir;
		} else {
			node = new MyFile(rootFile.getName());
		}

		return node;
	}

	public TreeItem<TreeItemData> makeTreeView(TreeItem<TreeItemData> tree,TreeItemData node){

		if (node instanceof Directory) {
			TreeItem<TreeItemData> dir = new TreeItem<>(new TreeItemData(node.getName(), TreeItemData.Type.FOLDER));
			tree.getChildren().add(dir);
			List<TreeItemData> children = ((Directory) node).getChildren();
			for (int i = 0; i < children.size(); i++) {
				TreeItemData child = children.get(i);
				dir.getChildren().add(makeTreeView(tree,child));
			}
			return dir;
		}else{
			return new TreeItem<>(new TreeItemData(node.getName(), TreeItemData.Type.ITEM));
		}
	}
}
