package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;

public class FileOutputShortcut {
	static String file = "src/io/FileOutputShortcut.out";
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(
				new StringReader(BufferedInputFile.read("src/io/FileOutputShortcut.java"))
				);
		PrintWriter out = new PrintWriter(file);
		int linecount = 1;
		String s;
		while((s = in.readLine()) != null)
			out.println(linecount++ + (linecount <= 10 ? " : " : ": ") + s);
		out.flush();
		out.close();
		System.out.println(BufferedInputFile.read(file));
	}

}
