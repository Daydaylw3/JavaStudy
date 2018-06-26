package tuanzhang.classinitialization;


/*
 * 要想创建一个类的实例，必须先将该类加载到内存并进行初始化
 * 也就是说，类初始化操作是在类实例化操作之前进行的;
 * 但并不意味着：只有类初始化操作结束后才能进行类实例化操作
 * 例如下面这个经典案例:
 * */
public class StaticTest {

	public static void main(String[] args) {
		staticFunction();
	}
	
	static StaticTest st = new StaticTest();
	
	static {	//静态代码块
		System.out.println("1");
	}
	
	{	//实例代码块
		System.out.println("2");
	}
	
	StaticTest() {	//实例构造器
		System.out.println("3");
		System.out.println("a = " + a + ", b = " + b);
	}
	
	public static void staticFunction() {	//静态方法
		System.out.println("4");
	}
	
	int a = 110;			//实例变量
	static int b = 112;		//静态变量
}
