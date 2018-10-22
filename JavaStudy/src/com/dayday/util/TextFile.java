package com.dayday.util;
/*
 * 18.7 文件读写的实用工具
 * */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class TextFile extends ArrayList<String> {
	/**
	 * 读取文件
	 * @param 文件名
	 * @return 单个字符串输出
	 * */
	public static String read(String fileName) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader in = 
					new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
			try {
				String s;
				while((s = in.readLine()) != null) {
					sb.append(s);
					sb.append("\n");
				}
			} finally {
				in.close();
			}
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
		return sb.toString();
	}
	/**
	 * 往文件中写字符串
	 * @param fileName 目标文件
	 * @param text 待写内容
	 * */
	public static void write(String fileName, String text) {
		try {
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			try {
				out.print(text);
			} finally {
				out.close();
			}
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public TextFile(String fileName, String splitter) {
		super(Arrays.asList(read(fileName).split(splitter)));
		// Regular expression split() often leaves an empty
		// String at the first position
		if(get(0).equals("")) remove(0);
	}
	
	public TextFile(String fileName) {
		this(fileName, "\n");
	}
	/**
	 * 将本对象内容输出至文件
	 * @param fileName 目标文件
	 * */
	public void write(String fileName) {
		try {
			PrintWriter out = 
					new PrintWriter(new File(fileName).getAbsoluteFile());
			try {
				for(String item : this)
					out.println(item);
			} finally {
				out.close();
			}
		} catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	public static void main(String[] args) {
		String file = read("src/com/dayday/util/TextFile.java");
		write("src/io/text/test.txt", file);
		TextFile text = new TextFile("src/io/text/test.txt");
		text.write("src/io/text/test2.txt");
		//
		TreeSet<String> words = 
				new TreeSet<String>(new TextFile("src/com/dayday/util/TextFile.java", "\\W+"));
		// Display
		System.out.println(words.headSet("a"));
		System.out.println(words);
	}
}
