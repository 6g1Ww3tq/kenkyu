package tree;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class TreeItemClickEvent implements EventHandler<MouseEvent> {

	public TreeItemClickEvent() {
		// TODO 自動生成されたコンストラクター・スタブ
		super();
	}

	@Override
	public void handle(MouseEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		Object obj = event.getSource();
		if (obj instanceof TreeCellImpl) {
			TreeCellImpl treeCell = (TreeCellImpl) obj;
			System.out.println(treeCell.getItem().getName());
		}
	}
}