package strings;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegularExpression {
	
	public static void main(String[] args) {
		String[] arg = {"abcabcabcdefabc", "abc+", "(abc)+", "(abc){2,}"};
		System.out.println("input: \"" + arg[0] + "\"");
		for(String a : arg) {
			System.out.println("regular expression: \"" + a + "\"");
			//Pattern对象表示编译后的正则表达式
			Pattern p = Pattern.compile(a);
			Matcher m = p.matcher(arg[0]);
			while(m.find()) {
				System.out.println("match \"" + m.group() + "\" at positions " + 
						m.start() + "-" + (m.end() - 1));
			}
			System.out.println("-----------------------");
		}
	}

}
