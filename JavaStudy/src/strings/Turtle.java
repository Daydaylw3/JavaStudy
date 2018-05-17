package strings;

import java.io.PrintStream;
import java.util.Formatter;


/*
 * 可以将Formatter看做一个翻译器，他将你的格式化字符串与数据
 * 翻译成需要的结果，当你创建爱你一个Formatter对象的时候，需
 * 要向其构造器传递一些信息，告诉他最终的结果向哪里输出
 * */
public class Turtle {
	private String name;
	private Formatter f;
	public Turtle(String name, Formatter f) {
		this.name = name;
		this.f = f;
	}
	public void move(int x, int y) {
		f.format("%s the turtle is at (%d, %d)\n", name, x, y);
	}
	public static void main(String[] args) {
		PrintStream outAlias = System.out;
		Turtle tommy = new Turtle("tommy", new Formatter(outAlias));
		//输出的目的地位System.err时，输出为红色
		Turtle terry = new Turtle("terry", new Formatter(System.err));
		
		tommy.move(1, 0);
		terry.move(2, 0);
	}
}
