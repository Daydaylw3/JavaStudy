package typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

class MethodSelector implements InvocationHandler {

	private Object proxied;
	public MethodSelector(Object proxied) {
		this.proxied = proxied;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if(method.getName().equals("interesting"))
			System.out.println("proxy detected interesting method");
		
		return method.invoke(proxied, args);
	}
	
}

interface SomeMethods {
	void boring1();
	void boring2();
	void interesting(String arg);
}

class Implementation implements SomeMethods {

	@Override
	public void boring1() {
		System.out.println("boring1");
	}

	@Override
	public void boring2() {
		System.out.println("boring2");
	}

	@Override
	public void interesting(String arg) {
		System.out.println("interesting " + arg);
	}
	
}

class SelectingMethods {

	public static void main(String[] args) {
		Implementation proxied = new Implementation();
		MethodSelector proxy = new MethodSelector(proxied);
		SomeMethods obj = (SomeMethods)Proxy.newProxyInstance(
				proxied.getClass().getClassLoader(), 
				proxied.getClass().getInterfaces(), 
				proxy);
		obj.boring1();
		obj.boring2();
		obj.interesting("naizi");
	}

}
