package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

public class BasicFileOutput {
	static String file = "src/io/BasicFileOutput.out";
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(
				new StringReader(BufferedInputFile.read("src/io/BasicFileOutput.java")
						)
				);
		PrintWriter out = new PrintWriter(
				new BufferedWriter(new FileWriter(file)));
		int linecount = 1;
		String s;
		while((s = in.readLine()) != null) {
			out.println(linecount++ + (linecount <= 10 ? " : " : ": ") + s);
		}
		out.flush();
//		out.close();	
		System.out.println(BufferedInputFile.read(file));
	}
}
