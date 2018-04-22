package classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 解密加密后的classen文件的类
 * */
public class DeClassLoader extends ClassLoader {
	private String mLibPath;
	
	protected  DeClassLoader(String path) {
		// TODO Auto-generated constructor stub
		mLibPath = path;
	}

	@Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
		String fileName = getFileName(name);
		File file = new File(mLibPath, fileName);
		try {
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			int len = 0;
			byte b = 0;
			
			while((len = fis.read()) != -1) {
				b = (byte)(len ^ 2);
				bos.write(b);
			}
			
			byte[] data = bos.toByteArray();
			fis.close();
			bos.close();
			
			return defineClass(name, data, 0, data.length);
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		return super.findClass(name);
	}
	
	private String getFileName(String name) {
		int index = name.lastIndexOf('.');
		if (index == -1) {
			return name + ".classen";
		}else {
			return name.substring(index + 1) + ".classen";
		}
		
	}
	public static void main(String[] args) {
		DeClassLoader loader = new DeClassLoader("/Users/daydaylw3");
		try {
			Class c = loader.findClass("test.Test");
			if(c != null) {
                try {
                      Object obj = c.newInstance();
                      Method method = c.getMethod("say", null);
                      method.invoke(obj, null);
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
}
