package strings.exercise;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Exercise10 {
	public static void main(String[] args) {
		String java = "Java now has regular expressions";
		String[] arg = {"^Java", "\\Breg.*", "n.w\\s+h(a|i)s", "s?", "s*", "s+", "s{4}", "s{1}", "s{0,3}"};
		
		for(String a : arg) {
			System.out.println("regular expression: \"" + a + "\"");
			Pattern p = Pattern.compile(a);
			Matcher m = p.matcher(java);
			while(m.find()) {
				System.out.println("match \"" + m.group() + "\" at positions " + 
						m.start() + "-" + (m.end() - 1));
			}
			System.out.println("-------------------");
		}
	}
}
