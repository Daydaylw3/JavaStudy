package typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/*
 * java动态代理可以动态的创建代理并动态地处理所代理的方法的调用
 * 在动态代理上所做的所有调用都会被重定向到单一的调用处理器上
 * 
 * PS. AOP的原理就是java的动态代理机制
 * 
 * 在java的动态代理机制中，有两个重要的类或接口，一个是 InvocationHandler(Interface)
 * 另一个则是 Proxy(Class)，这一个类和接口是实现我们动态代理所必须用到的
 * */

//实现 InvocationHandler 接口
class DynamicProxyHandler implements InvocationHandler{
	private Object proxied;
	public DynamicProxyHandler(Object proxied) {
		this.proxied = proxied;
	}
	
	/*
	 * proxy:指代我们所代理的那个真实对象
	 * method:指代的是我们所要调用真实对象的某个方法的Method对象
	 * args:指代的是调用真实对象某个方法时接受的参数
	 * */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("*** proxy: " + proxy.getClass() +
				", method: " + method + ", args: " + args);
		if(args != null)
			for(Object arg : args)
				System.out.println("  " + arg);
		
		return method.invoke(proxied, args);
	}
	
}


class SimpleDynamicProxy {
	public static void consumer(Interface iface) {
		iface.doSomething();
		iface.somethingElse("banana");
	}
	public static void main(String[] args) {
		RealObject real = new RealObject();
		consumer(real);
		System.out.println("******************* use proxy *******************");
		/*
		 * 调用 Proxy.newProxyInstance() 方法创建动态代理
		 * 方法需要的参数：
		 * ①类加载器
		 * ②希望该代理实现的接口列表（不是类或者是抽象类）
		 * ③InvocationHandler接口的一个实现
		 * */
		Interface proxy = (Interface)Proxy.newProxyInstance(
				Interface.class.getClassLoader(), 
				new Class[] {Interface.class}, 
				new DynamicProxyHandler(real));
		System.out.println(proxy.getClass().getName());
		consumer(proxy);
	}
}
