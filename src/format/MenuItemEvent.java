package format;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class MenuItemEvent implements EventHandler<ActionEvent> {
	private MenuButton menuButton;

	public MenuItemEvent(MenuButton menuButton) {
		this.menuButton = menuButton;
	}

	@Override
	public void handle(ActionEvent event) {
		Object obj = event.getSource();
		if (obj instanceof MenuItem) {
			MenuItem menuItem = (MenuItem)obj;
			menuButton.setText(menuItem.getText());
		}

	}

}
