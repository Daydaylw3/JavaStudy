package innerclasses;

public interface ClassInInterface {
	void howdy();
	
	//在接口中可以用嵌套类来创建公共代码
	//只能被实现这个接口的类所用
	class Test implements ClassInInterface {

		@Override
		public void howdy() {
			System.out.println("helo");
		}
		public static void main(String[] args) {
			new Test().howdy();
		}
	}
}

class Test1 implements ClassInInterface {

	@Override
	public void howdy() {
		// TODO Auto-generated method stub	
	}
	
	public ClassInInterface getClassInInterface() {
		return new Test();
	}
}

//class Test2 {
//	public ClassInInterface getClassInInterface() {
//		//can not
////		return new Test();
//	}
//}
