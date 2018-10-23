package com.dayday.util;
/*
 * 18.7.1 读取二进制文件
 * */

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class BinaryFile {
	/**
	 * 读取文本, 返回byte数组
	 * @param 文件
	 * @return 字节数组
	 * */
	public static byte[] read(File bFile) throws IOException {
		BufferedInputStream bf = new BufferedInputStream(new FileInputStream(bFile));
		try {
			byte[] data = new byte[bf.available()];
			bf.read(data);
			return data;
		}finally {
			bf.close();
		}
	}
	/**
	 * 读取文本, 返回byte数组
	 * @param 文件名
	 * @return 字节数组
	 * */
	public static byte[] read(String bFile) throws IOException{
		return read(new File(bFile).getAbsoluteFile());
	}
}
