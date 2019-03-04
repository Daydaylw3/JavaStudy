package classloader;

/**
 * @ClassName classloader.Test
 * @Description 
 * 
 * @see ClassLoader#loadClass(String)
 * @see ClassLoader#loadClass(String, boolean)
 * 
 * @author dayday
 * @date before 2019年3月2日
 */
public class Test {
	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println(System.getProperty("sun.boot.class.path"));
		System.out.println(System.getProperty("java.ext.dirs"));
		System.out.println(System.getProperty("java.class.path"));

		System.out.println(" --- 2019.3.2 --- ");
		ClassLoader classLoader = Test.class.getClassLoader();
		System.out.println(classLoader);
		System.out.println(classLoader.getParent());
		System.out.println(classLoader.getParent().getParent());
	}
	
	/*
	 * 修改该方法输出的版本号, 重新编译该类, 然后将.class文件替换对应目录下的.class文件
	 * 即可看到类被热加载
	 * */
	public void printVersion() {
		System.out.println("当前为版本1.0");
//		System.out.println("当前为版本2.0");
	}
}