package typeinfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collections;
import java.util.List;

/*
 * 假设存在许多不同类型的Robot，我们相对每一种Robot类型都创建一个空对象
 * 去执行某些特殊操作，在本例中，即提供空对象所代表的Robot确切类型的信息
 * 这些信息是通过动态代理捕获的
 * */
class NullRobotProxyHandler implements InvocationHandler {
	private String nullName;
	private Robot proxied = new NRoot();
	public NullRobotProxyHandler(Class<? extends Robot> type) {
		nullName = type.getSimpleName() + " NullRobot";
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		return method.invoke(proxied, args);
	}
	
	//实现Null、Robot接口的，代表空Robot类
	private class NRoot implements Null, Robot {

		@Override
		public String name() {
			return nullName;
		}

		@Override
		public String model() {
			return nullName;
		}

		@Override
		public List<Operation> operations() {
			return Collections.emptyList();
		}
		
	}
}

public class NullRobot {
	/*
	 * 无论何时，如果需要一个空Robot对象，只需要调用newNullRobot()
	 * 并传递需要代理的Robot类型。代理便会满足Robot和Null接口的需求
	 * 并提供它所代理的类型的确切名字
	 * */
	/**
	 * 通过动态代理创建Null Robot实例
	 * */
	public static Robot
	newNullRobot(Class<? extends Robot> type) {
		return (Robot)Proxy.newProxyInstance(NullRobot.class.getClassLoader(),
				new Class[] { Null.class, Robot.class },
				new NullRobotProxyHandler(type));
	}
	public static void main(String[] args) {
		Robot[] robs = {
				new SnowRemoveRobot("SnowBee"),
				newNullRobot(SnowRemoveRobot.class)
		};
		for(Robot rob : robs)
			Robot.Test.test(rob);
	}
}
