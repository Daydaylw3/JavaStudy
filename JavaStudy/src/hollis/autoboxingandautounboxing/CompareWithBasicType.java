package hollis.autoboxingandautounboxing;
/*
 * 包装类与基本数据类型进行比较
 * 到底比较的是什么
 * 会将包装类拆包为基本数据类型，然后再进行比较
 * */
public class CompareWithBasicType {
	public static void main(String... args) {
		Integer a = 1;
		System.out.println(a==1?"等于":"不等于");
		Boolean bool = false;
		System.out.println(bool?"真":"假");
	}
}
