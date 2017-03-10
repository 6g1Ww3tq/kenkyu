package tree;

import java.io.File;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import textarea.TextAreaController;
import textarea.TextAreaController.SETTYPE;

public class TreeItemClickEvent implements EventHandler<MouseEvent> {

	public TreeItemClickEvent() {
		// TODO 自動生成されたコンストラクター・スタブ
		super();
	}

	@Override
	public void handle(MouseEvent event) {
		// TODO 自動生成されたメソッド・スタブ
		String fileName = null;
		Object obj = event.getSource();
		if (obj instanceof TreeCellImpl) {
			TreeCellImpl treeCell = (TreeCellImpl) obj;
			fileName = treeCell.getItem().getName();
			System.out.println(fileName);
			//			TextAreaController.setText(new File(fileName), SETTYPE.FOLDER);
		}
	}
}