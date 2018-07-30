package fortest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CleanAttachment {
	
	private static String BASE_PATH = "D:/微信文件/WeChat Files/KOBEFLOWER24";

	public static void main(String[] args) {
		File giflist = new File(BASE_PATH + "/" + "Attachment/giflist.txt");
		BufferedReader in = null;
		BufferedWriter out = null;
		try {
			//保存gif的map
			Map<String, String> gifMap = new HashMap<String, String>();
			//读giflist.txt文件
			in = new BufferedReader(new FileReader(giflist));
			String s = in.readLine();
			while(s != null) {
				String gifLength = s.substring(17, 35).trim();
				String gifName = s.substring(36);
				gifMap.put(gifLength, gifName);
				
				s = in.readLine();
			}
			//新建文件并初始化
			File remainGif = new File(BASE_PATH + "/" + "Attachment/remaingif.txt");
			remainGif.createNewFile();
			out = new BufferedWriter(new FileWriter(remainGif));
			for(String name : gifMap.keySet()) {
				System.out.println(gifMap.get(name));
				//写文件
				out.write(gifMap.get(name));
				out.newLine();
				out.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
				if (out != null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
