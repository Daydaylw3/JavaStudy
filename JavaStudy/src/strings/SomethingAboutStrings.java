package strings;

/*
 * 和String有关的那点事
 * */
public class SomethingAboutStrings {
	public static void main(String[] args) {
		String s1 = "dayday";
		String s2 = new String("dayday");
		String s3 = new String("dayday").intern();
		
		System.out.println("s1 == s2: " + (s1 == s2));
		System.out.println("s1 == s3: " + (s1 == s3));
		
		String a = "abc";
		String b = "ab";
		String c = "ab" + "c";
		String d = b + "c";
		final String e = "ab";
		String f = e + "c";
		System.out.println("a == c: " + (a == c));
		System.out.println("a == d: " + (a == d));
		System.out.println("a == f: " + (a == f));
		
		/*
		 *  --------------------- 
		 *  作者：唐大麦 
		 *  来源：CSDN 
		 *  原文：https://blog.csdn.net/soonfly/article/details/70147205 
		 *  版权声明：本文为博主原创文章，转载请附上博文链接！
		 *  JDK 1.7后，intern方法还是会先去查询常量池中是否有已经存在，如果
		 *  存在，则返回常量池中的引用，这一点与之前没有区别，区别在于，如
		 *  果在常量池找不到对应的字符串，则不会再将字符串拷贝到常量池，而
		 *  只是在常量池中生成一个对原字符串的引用。简单的说，就是往常量池
		 *  放的东西变了：原来在常量池中找不到时，复制一个副本放到常量池，
		 *  1.7后则是将在堆上的地址引用复制到常量池。
		 * */
		String str1 = new String("laker ") + new String("championship");
		str1.intern();
		String str2 = "laker championship";
		System.out.println("str1 == str2: " + (str1 == str2));
		/*
		 * 将中间两行调换位置以后，因为在进行字面量赋值(String str1 = “str01″)的
		 * 时候，常量池中不存在，所以str1指向的常量池中的位置，而str2指向的是
		 * 堆中的对象，再进行intern方法时，对str1和str2已经没有影响了，所以返
		 * 回false
		 * */
		String str3 = new String("rocket ") + new String("championship");
		String str4 = "rocket championship";
		str3.intern();
		System.out.println("str3 == str4: " + (str3 == str4));
		
		// 案例1		false
//		String str1 = new String("abc") + new String("def");
//		String str2 = "abcdef";
//		str1.intern();
//		System.out.println(str1 == str2);

		// 案例2		1.8 true		1.6 false
//		String str1 = new String("abc") + new String("def");
//		str1.intern();
//		String str2 = "abcdef";
//		System.out.println(str1 == str2);

		// 案例3		false
//		String str1 = new String("abcdef");
//		String str2 = "abcdef";
//		str1.intern();
//		System.out.println(str1 == str2);

		// 案例4		false
//		String str1 = new String("abcdef");
//		str1.intern();
//		String str2 = "abcdef";
//		System.out.println(str1 == str2);
	}
}
