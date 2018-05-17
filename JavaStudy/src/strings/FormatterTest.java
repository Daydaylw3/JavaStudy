package strings;

import java.util.Formatter;

public class FormatterTest {

	public static void main(String[] args) {
		/*
		 * 	格式：%[argument_index$][flags][width][.precision]conversion
		 * argument_index$ --> 指定对应的内容参数位置，默认按照顺序依次对应
		 * .precision --> 对于浮点型数据，表示显示的小数位数；对于字符串数据，表示显示的最大字符数量
		 * conversion --> 类型转换字符
		 * */
		
		Formatter f = new Formatter(System.out);
		//String类型
		f.format("① *** %-5.7s ***\n", "123");
		f.format("② *** %5.7s ***\n", "123");
		f.format("③ *** %5.7s ***\n", "123456789");
		f.format("④ *** %.7s ***\n", "123");
		
		//float类型
		f.format("⑤*** %3.4f ***\n", 1.0);
		f.format("⑥*** %-3.4f ***\n", 1.0);
		f.format("⑦*** %3.4f ***\n", 1.01115);
		f.format("⑧*** %3.4f ***\n", 12345.0);
		f.format("⑨*** %+3.4f %+3.4f ***\n", 1.0, -1.0);
		f.format("⑩*** % 3.4f % 3.4f ***\n", 1.0, -1.0);
		f.format("①①*** %,3.1f ***\n", 1000000.0);
		f.format("①②*** %04f ***\n", 100.0);
		
		//int类型
		f.format("①③*** %04d ***\n", 100);
	}

}
