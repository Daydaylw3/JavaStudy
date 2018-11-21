package io;
/*
 * 18.6	I/O流的典型使用方式
 * 	18.6.1 缓冲输入文件
 * */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedInputFile {
	public static String read(String filename) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(filename));
		String s;
		StringBuilder sb = new StringBuilder();
		while((s = in.readLine()) != null)
			sb.append(s + "\n");
		in.close();
		return sb.toString();
	}
	public static void main(String[] args) throws IOException{
		System.out.print(read("src/io/BufferedInputFile.java"));
	}
}
