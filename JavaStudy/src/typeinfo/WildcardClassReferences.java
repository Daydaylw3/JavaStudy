package typeinfo;


/*
 * 为了在泛化的Class引用时放松限制，可以使用“通配符”
 * “通配符”就是“?”，表示任何事物。
 * Class<?> 优于平凡的 Class，即使他们是等价的，并且
 * 平凡的Class不会产生编译器警告信息。
 * Class<?>的好处是它表示你并非是碰巧或者是疏忽，而是
 * 有意使用了一个非具体的类引用
 * */
public class WildcardClassReferences {

	public static void main(String[] args) {
		Class<?> intClass = int.class;
		intClass = double.class;
	}

}
