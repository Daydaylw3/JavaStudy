package sort;

import java.io.FileReader;
import java.util.Properties;

public class SortTest {

	public static void main(String[] args) throws Exception{
		Class clazz = Class.forName(getValue("packageName") + "." + getValue("className"));
		Sort sort = (Sort)clazz.getConstructor().newInstance();
//		clazz.getMethod("say", null).invoke(sort, null);
		int[] array = {
			3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 48, 46, 4, 19, 50, 48,	
		};
		sort.sort(array);
		for(int i = 0; i < array.length; i++)
			System.out.print(array[i] + " ");
	}

	private static String getValue(String key) throws Exception{
		Properties pro = new Properties();
		FileReader in = new FileReader("/Users/daydaylw3/eclipse-workspace/JavaProject/src/sort/sort.config");
		pro.load(in);
		in.close();
		return pro.getProperty(key);
	}
}
