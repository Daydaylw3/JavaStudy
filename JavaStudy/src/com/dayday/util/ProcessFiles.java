package com.dayday.util;
/*
 * 18.1.2 目录实用工具
 * 创建一个工具, 它可以在目录中穿行, 并且根据Strategy对象来处理这些目录中的文件
 * */

import java.io.File;
import java.io.IOException;



public class ProcessFiles {
	/**
	 * Strategy接口内嵌在ProcessFiles中, 使得如果你希望实现它, 就必须实现
	 * ProcessFiles.Strategy, 它为读者提供了更多上下文信息
	 * */
	public interface Strategy {
		void process(File file);
	}
	private Strategy strategy;
	/**
	 * 拓展名
	 * */
	private String ext;
	public ProcessFiles(Strategy strategy, String ext) {
		this.strategy = strategy;
		this.ext = ext;
	}
	public void start(String[] args) {
		try {
			if(args.length == 0)
				processDirectoryTree(new File("."));
			else
				for(String arg : args) {
					File fileArg = new File(arg);
					if(fileArg.isDirectory())
						processDirectoryTree(fileArg);
					else {
						// Allow user to leave off extension
						if(!arg.endsWith("." + ext))
							arg += "." + ext;
						strategy.process(new File(arg).getCanonicalFile());
					}
				}
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	public void processDirectoryTree(File root) throws IOException {
		for(File file : Directory.walk(root.getAbsolutePath(), ".*\\." + ext))
			strategy.process(file);
	}
	public static void main(String[] args) {
		// args: ["src/generics", "src/io", "src/generics/ListMaker", "src/fortest/DuoTai.java", 
		//			"src/fortest/DuoTai1.java", "src/fortest/DuoTai2"]
		new ProcessFiles(new ProcessFiles.Strategy() {
			@Override
			public void process(File file) {
				System.out.println(file);
			}
		}, "java").start(args);
	}
}
