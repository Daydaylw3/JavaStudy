package typeinfo;

import java.util.Random;

class Initable{
	static final int staticFinal = 47;
	//对Initable.staticFinal2的访问讲强制进行类的初始化
	static final int staticFinal2 = 
			ClassInitialization.rand.nextInt(1000);
	static {
		System.out.println("Initializing Initable");
	}
}
class Initable2 {
	static int staticNonFinal = 147;
	static {
		System.out.println("Initializing Initable2");
	}
}
class Initable3 {
	static int staticNonFinal = 74;
	static {
		System.out.println("Initializing Initable3");
	}
}
public class ClassInitialization {
	public static Random rand = new Random(47);
	public static void main(String[] args) throws Exception{
		//仅使用.class语法来获得对类的引用不会引发初始化
		Class initable = Initable.class;
		System.out.println("after creating Initable ref");
		System.out.println(Initable.staticFinal);
		System.out.println(Initable.staticFinal2);
		System.out.println(Initable2.staticNonFinal);
		//Class.forName()立即进行了初始化
		Class initable3 = Class.forName("typeinfo.Initable3");
		System.out.println("after creating Initable3 ref");
		System.out.println(Initable3.staticNonFinal);
	}

}
