package strings;

import java.util.Arrays;

public class Splitting {
	public static String knights = 
			"Then, when you have found the strubbery, you must " +
			"cut down the mightiest tree in the forest... " +
			"with... a herring";
	public static void split(String regex) {
		System.out.println(Arrays.toString(knights.split(regex)));
	}
	public static void main(String[] args) {
		split(" ");		//空格来划分
		split("\\W+");	// \W代表非单词字符
		split("n\\W+");	//字母n后面跟着一个或多个非单词字符
	}

}
