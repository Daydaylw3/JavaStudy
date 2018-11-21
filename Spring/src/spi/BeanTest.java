package spi;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanTest {

	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spi/dayday.xml");
		Person p = ctx.getBean("chinese", Person.class);
		
		p.useAxe();
	}

}
