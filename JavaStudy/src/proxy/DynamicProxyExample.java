package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用jdk方式实现动态代理
 * 
 * @ClassName proxy.DynamicProxyExample
 * 
 * @author daydaylw3
 * @date Jun 17, 2019
 */
public class DynamicProxyExample {
	public static void main(String[] args) {
		Service service = new ServiceImpl();
		MyInvocationHandler handler = new MyInvocationHandler(service);
		Service serviceProxy = (Service) handler.getProxy();
		serviceProxy.add();
	}
}

// 定义业务逻辑
interface Service {
	void add();
}

class ServiceImpl implements Service {
	public void add() {
		System.out.println("add");
	}
}

// 代理类的实现
class MyInvocationHandler implements InvocationHandler {
	
	private Object target;
	
	public MyInvocationHandler(Object target) {
		this.target = target;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("--- before ---");
		Object result = method.invoke(target, args);
		System.out.println("--- after ---");
		return result;
	}
	
	// 生成代理对象
	public Object getProxy() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Class<?>[] interfaces = target.getClass().getInterfaces();
		return Proxy.newProxyInstance(loader, interfaces, this);
	}
}
