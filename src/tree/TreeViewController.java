package tree;

import java.io.File;

import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.util.Callback;

public class TreeViewController {
	TreeView<TreeItemData> treeview;

	public TreeViewController(TreeView<TreeItemData> treeview) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.treeview = treeview;
		treeview.setCellFactory(new TreeViewCellFactory());
	}

	public void makeFolderTree(File file) {
		// TODO 自動生成されたメソッド・スタブ
		//TreeViewのモデル(TreeItem)を構築する
//		TreeItem<TreeItemData> rootNode = new TreeItem<>(new TreeItemData("Root", TreeItemData.Type.FOLDER));
//		TreeItem<TreeItemData> node0 = new TreeItem<>(new TreeItemData("Folder0", TreeItemData.Type.FOLDER));
//		TreeItem<TreeItemData> node1 = new TreeItem<>(new TreeItemData("Folder1", TreeItemData.Type.FOLDER));
//		rootNode.setExpanded(true);
//		rootNode.getChildren().add(node0);
//		rootNode.getChildren().add(node1);
//		for(int i=0; i<3; i++){
//			node0.getChildren().add(new TreeItem<>(new TreeItemData("File0"+i)));
//			node1.getChildren().add(new TreeItem<>(new TreeItemData("File1"+i)));
//		}
//		//TreeItemのルートをTreeViewに設定する
//		treeview.setRoot(rootNode);
		
		TreeLogic tl = new TreeLogic(file.getPath());
		tl.makeTreeView(tl.getRootNode(),tl.getRoot());
		treeview.setRoot(tl.getRootNode());
	}
	
	public TreeItem<TreeItemData> makeBranch(TreeItemData data,TreeItem<TreeItemData> parant){
		TreeItem<TreeItemData> item = new TreeItem<TreeItemData>(data);
		item.setExpanded(true);

		parant.getChildren().add(item);
		return item;
	}

	private class TreeViewCellFactory implements Callback<javafx.scene.control.TreeView<tree.TreeItemData>, TreeCell<TreeItemData>> {

		@Override
		public TreeCell<TreeItemData> call(TreeView<TreeItemData> treeview) {
			// TODO 自動生成されたメソッド・スタブ
			return new TreeCellImpl(TreeViewController.this);
		}

	}


	public TreeItemData treeItemDataRenamed(TreeItem<TreeItemData> treeItem, String name) {
		return new TreeItemData(name, treeItem.getValue().getType());
	}
}
