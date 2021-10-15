package GoFsckYourself;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Fsck {
	
	private File currentDir;
	private int counterFsckedFiles = 0;
	private int counterDamagedFiles = 0;
	private int counterFsckedDirs = 0;
	private int counterDamagedDirs = 0;
	
	public Fsck() {
		currentDir = new File(System.getProperty("user.dir") + "/treeRoot");
		displayDirectory(currentDir);
	}
	
	public boolean fsckDir(String dirPath) {
		String[] dirPathSplited = dirPath.split("_");
		System.out.println(Arrays.toString(dirPathSplited));
		if(dirPathSplited.length > 1) {
			return true;
		}
		return false;
	}
	
	public boolean fsckFile(String dirPath) {
		String[] dirPathSplited = dirPath.split("_");
		System.out.println(Arrays.toString(dirPathSplited));
		if(dirPathSplited.length > 1) {
			return true;
		}
		return false;
	}

//	Ja existe uma funcao nativa na classe File file.listFiles();
//	public List<File> readDir() {
//		
//	}
//	
//	//Ja existe uma funcao nativa na classe File file.isDirectory();
//	public boolean isDir() {
//		
//	}
//	
//	public boolean fsckFile() {
//		
//	}
//	
	
//
//	Ja existe uma funcao nativa na classe File file.isParent();	
//	public File parent() {
//		
//	}
	
	public void displayDirectory(File dir) {
  
        try {
            File[] files = dir.listFiles();
  
            for (File file : files) {
                if (file.isDirectory()) {
                	counterFsckedDirs ++;
                	if(fsckDir(file.getAbsolutePath())) {
                		counterDamagedDirs ++;
                		displayDirectory(file);
                	}
                    System.out.println("directory:" + file.getCanonicalPath());
                    displayDirectory(file);
                }
                else {
                    System.out.println("     file:" + file.getCanonicalPath());
                }
            }
            
//            for (File file : files) {
//            	if (file.isDirectory()) {
//            		displayDirectory(file);
//            	}
//            	
//            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
       System.out.println("counterFsckedDirs: " + counterFsckedDirs + " counterDamagedDirs: " + counterDamagedDirs);
    }
	
}
