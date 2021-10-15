package GoFsckYourself;

import java.io.File;
import java.util.Arrays;

public class Fsck {
	
	private File currentDir;
	private int counterFsckedFiles = 0;
	private int counterDamagedFiles = 0;
	private int counterFsckedDirs = 0;
	private int counterDamagedDirs = 0;
	
	Thread t1 = new Thread();
	
	public Fsck() {
		currentDir = new File(System.getProperty("user.dir") + "/Prova3/treeRoot");
		System.out.println("CURRENT DIR: " + currentDir);
		displayDirectory(currentDir);
	}
	
	public boolean fsckDir(String dirPath) {
		String[] dirPathSplited = dirPath.split("_");
		//System.out.println(Arrays.toString(dirPathSplited));
		if (dirPathSplited.length>1 && dirPathSplited[1] != null && dirPathSplited[1].equals("dmg")) {
			return true;
		}
		return false;
	}
	
	public boolean fsckFile(String dirPath) {
		String[] dirPathSplited = dirPath.split("_");
		//System.out.println(Arrays.toString(dirPathSplited));
		if(dirPathSplited.length > 1) {
			return true;
		}
		return false;
	}
	
	public void displayDirectory(File dir) {

			File[] files = dir.listFiles();
			
			if (files != null) {
				
				for (File file : files) {
					System.out.println(file);
					if (file.isDirectory()) {
						counterFsckedDirs ++;
						if(fsckDir(file.getAbsolutePath())) {
							counterDamagedDirs ++;
						}
						displayDirectory(file);
					} else if (fsckFile(file.getAbsolutePath())) {
						counterFsckedFiles ++;
						counterDamagedFiles ++;
						if(fsckDir(file.getParent())) {
							counterFsckedDirs ++;
							counterDamagedDirs ++;
							displayDirectory(file);
						}
					}

				}
			}
			
       System.out.println("counterFsckedDirs: " + counterFsckedDirs + " counterDamagedDirs: " + counterDamagedDirs);
    }
	
}
