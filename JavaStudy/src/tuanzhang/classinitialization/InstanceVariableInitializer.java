package tuanzhang.classinitialization;

/**
 * 如果我们对实例变量直接赋值或者使用实例代码块赋值
 * 那么编译器会将其中的代码放到类的构造函数中去，并
 * 且这些代码会被放在对超类构造函数的调用语句之后
 * */
public class InstanceVariableInitializer {
	//特别需要注意的是，Java是按照编程顺序来执行实例变量初始化器
	//和实例初始化器中的代码的， 并且不允许顺序靠前的实例代码块初
	//始化在其后面定义的实例变量
//	private int k = i + 2;	无法编译
	private int i = 1;
	private int j = i + 1;
	
	public InstanceVariableInitializer(int var) {
		//实际上是这样的顺序
//		super();
//		i = i;
//		j = i + 1;
//		j += 3;
		System.out.println(i);
		System.out.println(j);
		this.i = var;
		System.out.println(i);
		System.out.println(j);
	}
	{
		j += 3;
	}
	
	
	public static void main(String[] args) {
		new InstanceVariableInitializer(8);
	}

}
