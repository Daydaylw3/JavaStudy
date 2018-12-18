package strings;

/*
 * 和String有关的那点事
 * */
public class SomethingAboutStrings {
	public static void main(String[] args) {
		String s1 = "dayday";
		String s2 = new String("dayday");
		String s3 = new String("dayday").intern();
		
		System.out.println(s1 == s2);
		System.out.println(s1 == s3);
	}
}
