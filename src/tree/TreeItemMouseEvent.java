package tree;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class TreeItemMouseEvent implements EventHandler<MouseEvent> {

	public TreeItemMouseEvent() {
	}

	@Override
	public void handle(MouseEvent event) {
		if (event.isSecondaryButtonDown()) {
			System.out.println("clicked");
		}
	}

}
