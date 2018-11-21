package io;
/*
 * 18.1.2 目录实用工具
 * 教你如何使用Directory实用工具
 * */

import java.io.File;

import com.dayday.util.Directory;
import com.dayday.util.PPrint;

public class DirectoryDemo {
	public static void main(String[] args) {
		PPrint.pprint(Directory.walk(".").dirs);
		// All files beginning with 'T'
		for(File file : Directory.walk(".", "T.*"))
			System.out.println(file);
		System.out.println("-----------------------------");
		// All Java files begining with 'T'
		for(File file : Directory.walk(".","T.*\\.java"))
			System.out.println(file);
		System.out.println("=============================");
		// Class files containing "Z" or "z"
		for(File file : Directory.walk(".", ".*[Zz].*\\.class"))
			System.out.println(file);
	}
}
