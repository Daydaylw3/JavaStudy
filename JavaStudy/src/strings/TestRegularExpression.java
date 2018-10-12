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
		System.out.println("----------分组---------");
		String test = "020-85653333";
		String reg = "(0\\d{2})-(\\d{8})";
		Pattern pattern = Pattern.compile(reg);
		Matcher mc = pattern.matcher(test);
		if(mc.find()) {
			System.out.println("分组的个数有：" + mc.groupCount());
			for (int i = 0 ; i <= mc.groupCount(); i ++)
				System.out.println("第" + i + "个组为：" + mc.group(i));
		}
		System.out.println("---------捕获组-----------");
		reg = "(?<quhao>0\\d{2})-(?<haoma>\\d{8})";
		pattern = Pattern.compile(reg);
		mc = pattern.matcher(test);
		if(mc.find()) {
			System.out.println("分组的个数有：" + mc.groupCount());
			System.out.println("quhao: " + mc.group("quhao"));
			System.out.println("haoma: " + mc.group("haoma"));
		}
		System.out.println("---------非捕获组----------");
		reg = "(?:0\\d{2})-(\\d{8})";
		pattern = Pattern.compile(reg);
		mc = pattern.matcher(test);
		if(mc.find()) {
			System.out.println("分组的个数有：" + mc.groupCount());
			for(int i = 0; i <= mc.groupCount(); i++)
				System.out.println("第" + i + "个组为：" + mc.group(i));
		}
	}

}
