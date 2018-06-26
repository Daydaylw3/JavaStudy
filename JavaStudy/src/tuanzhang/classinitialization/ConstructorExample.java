package tuanzhang.classinitialization;

public class ConstructorExample {
	private int i;
	private String name;
	//如果我们在一个构造函数中调用另外一个构造函数
	//对于这种情况，Java只允许在ConstructorExample(int i)内
	//调用超类的构造函数
	ConstructorExample() {
		this(2, "dayday");
	}
	
	ConstructorExample(int i, String name){
		this.i = i;
		this.name = name;
	}
	
	//下面两种情形的代码编译是无法通过的
	ConstructorExample(int i) {
		super();
		//Constructor call must be the first statement in a constructor
//		this(i, "V3");
	}
	
	ConstructorExample(String name){
		this(1, name);
		//Constructor call must be the first statement in a constructor
//		super();
	}
	
}
