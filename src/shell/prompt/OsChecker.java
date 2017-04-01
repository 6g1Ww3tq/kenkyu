package shell.prompt;

import org.apache.commons.lang3.SystemUtils;

public class OsChecker {
	private static OperatingSystemType osType = null;
	private static String javaPath = null;

	private OsChecker(){}

	public void analyze() {
		if (SystemUtils.IS_OS_LINUX) {
			osType = OperatingSystemType.Linux;
			javaPath = "/usr/bin/java";
		}else if (SystemUtils.IS_OS_WINDOWS) {
			osType = OperatingSystemType.Windows;
			javaPath = "C:/Program Files/Java/";		//保留
		}
	}

	public static OperatingSystemType getOsType() throws OsCheckerException{
		if (osType==null) {
			throw new OsCheckerException("Attribute osType in OsChecker is null");
		}
		return osType;
	}
	
	public static String getJavaPath() throws OsCheckerException{
		if (osType==null) {
			throw new OsCheckerException("Attribute javaPath in OsChecker is null");
		}
		return javaPath;
	}
}
