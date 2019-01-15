package io;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.RandomAccessFile;

public class TestFileInputAndFileOutput {
	
	public static void main(String[] args) throws Exception {
		File tmpFile = new File("/Users/daydaylw3/tmpFile");
		if (tmpFile.isFile() && tmpFile.exists()) {
			tmpFile.delete();
			tmpFile.createNewFile();
		} else {
			tmpFile.createNewFile();
		}
		// 读文件
		DataInputStream in = new DataInputStream(new FileInputStream(tmpFile));
		// 写文件
		DataOutputStream out = new DataOutputStream(new FileOutputStream(tmpFile));
		
		// 1.写文件头
		String head1 = "00000000";
//		out.write();
		out.write(head1.getBytes());
		out.flush();
		// 2.读文件头
		byte[] tmp = new byte[1024];
//		if (in.)
		in.read(tmp, 0, 8);
		System.out.println(new String(tmp));
		// 3.重写文件头
		String head2 = "00010004";
		out.write(head2.getBytes());
		out.close();
		// 4.读文件头
		in.read(tmp, 0, 8);
		System.out.println(new String(tmp));
		// 5.末尾追加内容
		FileWriter fw = new FileWriter(tmpFile, true);
		RandomAccessFile randomFile = new RandomAccessFile(tmpFile, "rw");
		long fileLength = randomFile.length();
		randomFile.seek(fileLength);
		randomFile.writeBytes("zhuijia");
		randomFile.close();
		// 6.读文件
		in = new DataInputStream(new FileInputStream(tmpFile));
		in.read(tmp);
		System.out.println(new String(tmp));
	}
	
}
