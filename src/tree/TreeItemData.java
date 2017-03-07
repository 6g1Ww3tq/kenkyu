package tree;

public class TreeItemData {
	private String name;
	private Type type;

	public TreeItemData(String name,Type type) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.name = name;
		this.type = type;
	}

	public TreeItemData(String name) {
		// TODO 自動生成されたコンストラクター・スタブ
		this(name, Type.ITEM);
	}

	public String getName() {
		return name;
	}

	public Type getType() {
		return type;
	}

	public void setName(String name) {
		if (name.equals("[a-z]{10}")) {
			this.name = name;
		} else {
			System.out.println("tree item name error");
		}
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public String toString() {
		// TODO 自動生成されたメソッド・スタブ
		return name;
	}

	public enum Type {
		FOLDER,
		ITEM,
	}
}
