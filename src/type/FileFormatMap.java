package type;

import java.util.HashMap;

public class FileFormatMap extends HashMap<FILEFORMAT, String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileFormatMap() {
		this.put(FILEFORMAT.ANY, "*");
		this.put(FILEFORMAT.CLASS, "class");
		this.put(FILEFORMAT.JAVA, "java");
	}
	
	public String getFormat(Object key) {
		String value = super.get(key);
		
		if (value.equals("*")) {
			value = "." + value;
		}else{
			value = ".*" + value;
		}
		return value;
	}
	
	public FILEFORMAT getKey(String value) throws NullPointerException{
		String formatStr;
		
		for (FILEFORMAT formatType : this.keySet()) {
			formatStr = (String)this.get(formatType);
			if (formatStr.equals(value)) {
				return formatType;
			}
		}

		throw new NullPointerException("Not Found " + value + " in FileFormatMap Class");
	}
}