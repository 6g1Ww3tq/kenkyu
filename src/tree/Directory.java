package tree;

import java.util.List;

public class Directory extends TreeItemData {
	List<TreeItemData> children;

	public Directory(String name) {
		super(name, Type.FOLDER);
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public List<TreeItemData> getChildren(){
		return children;
	}
	
	public void setChildren(List<TreeItemData> children) {
		this.children = children;
	}
}
