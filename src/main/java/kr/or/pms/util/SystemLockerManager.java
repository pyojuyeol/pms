package kr.or.pms.util;

import java.io.File;
import java.io.IOException;

public class SystemLockerManager {
	
	private static String systemLockerPath;
	public void setSystemLockerPath(String systemLockerPath) {
		SystemLockerManager.systemLockerPath = systemLockerPath;
	}
	
	private static String filePath = (systemLockerPath + "/system.lock").replace("/", File.separator);
	
	//배포파일을 변경하면 서버가 재시작되면서 사용하던 객체들을 잃어버리게 되고
	//에러를 발생시킨다. 따라서 배포영역이 아닌곳에 파일을 둬야함. 와스가 여러개면 디비에 둬야함 
	public static void makeLocker() {
		File lockerFile = new File(filePath);
		lockerFile.getParentFile().mkdirs();

		try {
			if(lockerFile.exists()==false) {
				lockerFile.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static void removeLocker() {
		File systemFile = new File(filePath);
		if(systemFile.exists()) {
			systemFile.delete();
		}
	}
	public static boolean isLocked() {
		File systemFile = new File(filePath);
		return systemFile.exists();	
	}
	
}
