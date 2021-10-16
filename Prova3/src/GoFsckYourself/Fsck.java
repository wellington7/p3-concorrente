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
		currentDir = new File(System.getProperty("user.dir") + "/treeRoot");
		System.out.println("* FSCK - CURRENT DIR: " + currentDir);
	}
	
	public void run() {
		displayDirectory(currentDir);
	}
	
	public boolean fsckDir(String dirPath) {
		String[] dirPathSplited = dirPath.split("_");
		//System.out.println(Arrays.toString(dirPathSplited));
		if (dirPathSplited.length>1 && dirPathSplited[1] != null && dirPathSplited[dirPathSplited.length-1].equals("dmg")) {
			return true;
		}
		return false;
	}
	
	public boolean fsckFile(String dirPath) {
		String[] dirPathSplited = dirPath.split("_");
		//System.out.println("---------" + Arrays.toString(dirPathSplited));
		if(dirPathSplited.length > 1 && dirPathSplited[1] != null && dirPathSplited[dirPathSplited.length-1].equals("dmg.txt")) {
			return true;
		}
		return false;
	}
	
	public void displayDirectory(File dir) {
		System.out.println("* FSCK - " + " Threads rodando: " + Integer.toString(Thread.activeCount() - 2));
		File[] files = dir.listFiles();

		if (files != null) {

			for (File file : files) {
				System.out.println(file);
				if (file.isDirectory()) {
					counterFsckedDirs++;
					if (fsckDir(file.getAbsolutePath())) {
						counterDamagedDirs++;
					}
					new Thread() {
						@Override
						public void run() {
							displayDirectory(file);
						}
					}.start();
				} else {
					counterFsckedFiles++;
					if (fsckFile(file.getAbsolutePath())) {
						counterDamagedFiles++;
					}
				}

			}
		}

		System.out.println("* FSCK - Fscked Dirs: " + counterFsckedDirs + " Damaged Dirs: " + counterDamagedDirs
				+ " Fscked Files: " + counterFsckedFiles + " Damaged Files: " + counterDamagedFiles);
	}
	
}
