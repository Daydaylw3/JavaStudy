package reflection;

import java.lang.reflect.Field;

public class Fields {

	public static void main(String[] args) throws Exception{
		//1.获取Class对象
		Class clazz = Class.forName("reflection.Student");
		
		//2.获取字段
		System.out.println("************获取所有公有的字段********************");  
        Field[] fieldArray = clazz.getFields();
        for(Field f : fieldArray) {
        		System.out.println(f);
        }
        
        //3.获取所有字段（包括私有、受保护、默认的）
        System.out.println("************获取所有的字段(包括私有、受保护、默认的)********************");  
        fieldArray = clazz.getDeclaredFields();
        for(Field f : fieldArray) {
        		System.out.println(f);
        }
        
        //4.获取共有字段，并调用
        System.out.println("*************获取公有字段**并调用***********************************");  
        Student stu = (Student)clazz.getConstructor().newInstance();
        Field f = clazz.getField("name");
        System.out.println(f);
        f.set(stu, "刘德华");
        
        //5.获取私有字段，并调用
        System.out.println("**************获取私有字段****并调用********************************");  
        f = clazz.getDeclaredField("phoneNum");
        System.out.println(f);
        f.setAccessible(true);
        f.set(stu, "12306");
        System.out.println(stu);
	}

}
