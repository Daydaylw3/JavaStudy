package io;
/*
 * 18.8.3 标准I/O重定向
 * */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class Redirecting {
	public static void main(String[] args) throws IOException{
		// System.out 是一个 PrintStream 而 PrintStream 是一个 OutputStream
		PrintStream console = System.out;
		BufferedInputStream in = 
				new BufferedInputStream(new FileInputStream("src/io/Redirecting.java"));
		PrintStream out = 
				new PrintStream(new BufferedOutputStream(new FileOutputStream("src/io/text/test.out")));
		System.setIn(in);
		System.setOut(out);
		System.setErr(out);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s;
		while((s = br.readLine()) != null)
			System.out.println(s);
		out.close();
		System.setOut(console);
	}
}
