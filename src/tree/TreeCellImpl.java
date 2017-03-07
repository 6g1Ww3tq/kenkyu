package tree;

import javafx.scene.control.TreeCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TreeCellImpl extends TreeCell<TreeItemData> {
	private static final Image FolderImage = createImage("folder.gif");
	private static final Image FileImage = createImage("file.gif");
	TreeViewController controller;
	TreeCellGraph graph;

	public TreeCellImpl(TreeViewController controller) {
		// TODO 自動生成されたコンストラクター・スタブ
		this.controller = controller;
	}

	@Override
	public void startEdit() {
		super.startEdit();
		//編集用Graphicを生成する
		if(this.graph == null){
			this.graph = new TreeCellGraph(this.controller, this, createImageView(getItem()));
		}
		//編集開始前の処理をTreeCellGraphに委譲
		this.graph.startEdit();
		//編集時はLabeledTextにラベルを表示させない
		setText(null);
		setGraphic(this.graph);
	}

	@Override
	public void cancelEdit(){
		super.cancelEdit();
		//アイコンとラベルを元に戻す
		setText(getItem().getName());
		setGraphic(createImageView(getItem()));
	}

	@Override
	public void updateItem(TreeItemData data, boolean empty){
		super.updateItem(data, empty);
		if(empty){
			setText(null);
			setGraphic(null);
		}else if(isEditing()){
			//編集時はLabeledTextにラベルを表示させない
			setText(null);
			setGraphic(this.graph);
		} else {
			//通常の表示
			setText(data.getName());
			setGraphic(createImageView(data));
		}
	}

	private static Image createImage(String fileName){
		return new Image(TreeCellImpl.class.getResourceAsStream(fileName));
	}

	private static ImageView createImageView(TreeItemData data){
		final Image img = (data.getType() == TreeItemData.Type.FOLDER) ?
				FolderImage : FileImage;
		return new ImageView(img);
	}
}
