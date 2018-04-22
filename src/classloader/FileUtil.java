package classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

	public static void test(String path) {
		File file = new File(path);
		
		try {
			FileInputStream fis = new FileInputStream(file);
			FileOutputStream fos = new FileOutputStream(path + "en");
			
			int b = 0;
			int b1 = 0;
			try {
				while((b = fis.read()) != -1) {
					fos.write(b ^ 2);
				}
				fis.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new FileUtil().test("/Users/daydaylw3/Test.class"); 
	}

}
