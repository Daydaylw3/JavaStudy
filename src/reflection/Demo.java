package reflection;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

public class Demo {

	public static void main(String[] args) throws Exception{
		Class clazz = Class.forName(getValue("className"));
		Method m = clazz.getDeclaredMethod(getValue("methodName"), String.class);
		m.invoke(clazz.newInstance(), "helo");
	}

	private static String getValue(String key) throws IOException {
		Properties pro = new Properties();
		FileReader in = new FileReader("src/reflection/pro.config");
		pro.load(in);
		in.close();
		return pro.getProperty(key);
	}
}
