package classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ClassLoaderTest {
	
	static void test() {
		ClassLoader loader = new DeClassLoader("/Users/daydaylw3");
        try {
             //加载class文件
             Class c = loader.loadClass("test.Test");
             if(c != null) {
                  try {
//                        Object obj = c.newInstance();
                        Object t = loader.loadClass("test.Test").newInstance();
                        
                        Method method = c.getMethod("say", null);
                        method.invoke(t, null);
                  }catch(InstantiationException | IllegalAccessException
                             | NoSuchMethodException
                             | SecurityException |
                             IllegalArgumentException
                             | InvocationTargetException e) {
                        e.printStackTrace();
                  }
             }
        }catch(ClassNotFoundException e) {
             e.printStackTrace();
        }
	}
	
	public static void main(String[] args) {
		test();
	}

}
