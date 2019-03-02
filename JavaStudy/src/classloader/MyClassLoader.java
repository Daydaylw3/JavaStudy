package classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName classloader.MyClassLoader
 * @Description 自定义的类加载器 来实现 热加载类</br>
 * 自己实现ClassLoader只需要继承ClassLoader类, 然后覆盖 findClass(String name) 方法
 * 即可完成一个带有双亲委派模型的类加载器
 * 
 * @see classloader.Test
 * 
 * @author dayday
 * @date 2019年3月2日
 */
public class MyClassLoader extends ClassLoader {
	/** 用于读取.class文件的路径 */
	private String swapPath;
	/** 用于标记这些name的类是由自身加载的 */
	private Set<String> useMyClassLoader;
	
	public MyClassLoader(String swapPath, Set<String> useMyClassLoader) {
		this.swapPath = swapPath;
		this.useMyClassLoader = useMyClassLoader;
	}
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		Class<?> c = findLoadedClass(name);
		if (c == null && useMyClassLoader.contains(name)) {
			c = findClass(name);
			if (c != null) {
				return c;
			}
		}
		return super.loadClass(name);
	}
	
	@SuppressWarnings("unused")
	@Override
	protected Class<?> findClass(String name) {
		byte[] classBytes = getClassByte(name);
		// 调用ClassLoader提供的方法, 将二进制数组转化为Class类的实例
		return defineClass(name, classBytes, 0, classBytes.length);
	}
	
	@SuppressWarnings("resource")
	private byte[] getClassByte(String name) {
		String className = name.substring(name.lastIndexOf(".") + 1, name.length()) + ".class";
		try {
			FileInputStream fileInputStream = new FileInputStream(swapPath + className);
			byte[] buffer = new byte[1024];
			int length = 0;
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			while ((length = fileInputStream.read(buffer)) > 0) {
				byteArrayOutputStream.write(buffer, 0, length);
			}
			
			return byteArrayOutputStream.toByteArray();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return new byte[] {};
	}
	
	public static void main(String[] args) {
		// 定时任务, 不断加载类并执行 Test#printVersion 方法
		new Timer().schedule(new TimerTask() {
			@Override
			public void run() {
				String swapPath = MyClassLoader.class.getResource("").getPath() + "swap/";
				String className = "classloader.Test";
				
				// 每次都实例化一个ClassLoader, 这里传入swap路径, 和需要特殊加载的类名
				@SuppressWarnings("serial")
				MyClassLoader myClassLoader = new MyClassLoader(swapPath, new HashSet<String>() {{
					add(className);
				}}) ;
				try {
					Object o = myClassLoader.loadClass(className).newInstance();
					o.getClass().getMethod("printVersion").invoke(o);
				} catch (InstantiationException |
								IllegalAccessException |
								ClassNotFoundException |
								NoSuchMethodException |
								InvocationTargetException ignored) {
					// do nothing
				}
			}
		}, 0, 2000);
	}
}
