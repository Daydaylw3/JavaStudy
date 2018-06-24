package tuanzhang.classinitialization;

//有办法绕过编译器的变量检查
public class InstanceInitializer {
	private int j = getI();
	private int i = 1;
	public InstanceInitializer() {
		i = 2;
	}
	
	private int getI() {
		return i;
	}
	
	public static void main(String[] args) {
		InstanceInitializer ii = new InstanceInitializer();
		System.out.println(ii.j);	//Output : 0
		//我们可以确信，变量j被赋予了i的默认值0
		//这一动作发生在实例变量i初始化之前和构造函数调用之前
	}

}
