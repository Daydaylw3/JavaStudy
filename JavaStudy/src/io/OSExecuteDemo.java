package io;
/*
 * 18.9 进程控制
 * */

import com.dayday.util.OSExecute;

public class OSExecuteDemo {
	public static void main(String[] args) {
		OSExecute.command("javap bin/io/OSExecuteDemo");
	}
}
