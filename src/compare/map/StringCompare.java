package compare.map;

import java.util.HashMap;

public abstract class StringCompare {
	protected HashMap<String, String> wordMap;

	abstract protected void perform(String key);

	public void search(String targetText){
		for(String key : wordMap.keySet()){
			if (key.equals(targetText)) {
				perform(key);
				break;
			}
		}
	}

}
