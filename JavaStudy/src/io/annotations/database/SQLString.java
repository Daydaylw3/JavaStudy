package io.annotations.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName io.annotations.database.SQLString
 * @Description 20.2.3 生成外部文件<p>
 * 为修饰JavaBean域准备的注解
 * 
 * @see io.annotations.database.Constraints
 * 
 * @author daydaylw3
 * @date 2019年2月2日
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {

	int value() default 0;
	
	String name() default "";
	
	Constraints constraints() default @Constraints;
	
}
