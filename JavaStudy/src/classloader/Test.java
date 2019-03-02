package classloader;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println(System.getProperty("sun.boot.class.path"));
		System.out.println(System.getProperty("java.ext.dirs"));
		System.out.println(System.getProperty("java.class.path"));
		
		System.out.println(" -- 2019.3.2 --- ");
		ClassLoader classLoader = Test.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader.getParent());
        System.out.println(classLoader.getParent().getParent());
	}
}
