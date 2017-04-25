package window.text.statusText;

import java.util.HashMap;

import compare.map.StringCompare;
import javafx.scene.text.Text;

public class StatusTextCompare extends StringCompare{
	private Text text;

	public StatusTextCompare(Text text,HashMap<String, String> wordMap) {
		this.text = text;
		this.wordMap = wordMap;
	}

	@Override
	protected void perform(String key) {
		text.setText(wordMap.get(key));
	}

}