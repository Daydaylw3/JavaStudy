package containers;
/*
 * 17.9.3 覆盖hashCode()
 * 设计hashCode()的一个最重要因素就是：无论何时，对同一个对象调用hashCode()都应该
 * 产生相同的值。所以如果hashCode()方法依赖于对象中易变得数据，就要当心了
 * 此外，也不应该使hashCode()依赖于具有唯一性的对象信息，尤其是使用this的值，这只能
 * 产生很糟糕的hashCode()。因为这样无法生成一个新的键，使之与put()中原始的键值对中
 * 的键相同。
 * hashCode()应该使用对象内有意义的识别信息
 * */


public class StringHashCode {
	public static void main(String... args) {
		String[] hellos = "Hello Hello".split(" ");
		System.out.println(hellos[0].hashCode());
		System.out.println(hellos[1].hashCode());
	}
}
