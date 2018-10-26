package io;
/*
 * 18.10.1 转换数据
 * 缓冲器容纳的是普通的字节, 为了把他们转换成字符
 * 我们要么在输入他们的时候对其进行编码, 要么将其
 * 从缓冲器输出的时候对他们进行解码
 * */

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.SortedMap;

public class AvailableCharSets {
	public static void main(String[] args) {
		SortedMap<String, Charset> charSets = Charset.availableCharsets();
		Iterator<String> it = charSets.keySet().iterator();
		while(it.hasNext()) {
			String csName = it.next();
			System.out.print(csName);
			Iterator aliases = charSets.get(csName).aliases().iterator();
			if(aliases.hasNext())
				System.out.print(": ");
			while(aliases.hasNext()) {
				System.out.print(aliases.next());
				if(aliases.hasNext())
					System.out.print(", ");
			}
			System.out.println();
		}
	}
}

