package window.tree;

import java.io.File;

import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import type.FILETYPE;

public class TreeItemString extends TreeItem<String> {
	FILETYPE type;
	private ImageView imageView;
	File file;

	public TreeItemString(String value,FILETYPE type) {
		Image image = null;
		if (type == FILETYPE.FOLDER) {
			image = new Image(getClass().getResourceAsStream("/window/icons/folder.png"));
		}else{
			image = new Image(getClass().getResourceAsStream("/window/icons/file.png"));
		}
		this.type = type;
		this.imageView = new ImageView(image);
		this.setGraphic(imageView);
		this.setValue(value);
	}
	
	public void setFile(File file){
		this.file = file;
	}

	@Override
	public String toString() {
		return file.toPath().toString();
	}
}