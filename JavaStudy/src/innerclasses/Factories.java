package innerclasses;

interface Service {
	void method1();
	void method2();
}
interface ServiceFactory {
	Service getService();
}

class Implement1 implements Service {
	private Implement1() {}
	public void method1() { System.out.println("imp1.method1()"); }
	public void method2() { System.out.println("imp1.method2()"); }
	
	public static ServiceFactory getFactory() {
		return new ServiceFactory() {
			
			@Override
			public Service getService() {
				// TODO Auto-generated method stub
				return new Implement1();
			}
		};
	}
}

public class Factories {
	public static void main(String[] args) {
		ServiceFactory factory =Implement1.getFactory();
		Service se = factory.getService();
		se.method1();
		se.method2();
	}
}
