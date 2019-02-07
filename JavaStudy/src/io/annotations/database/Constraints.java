package io.annotations.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName io.annotations.database.Constraints
 * @Description 20.2.3 生成外部文件<p>
 * 为修饰JavaBean域准备的注解
 * 
 * @author daydaylw3
 * @date 2019年2月2日
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Constraints {
	
	boolean primaryKey() default false;
	
	boolean allowNull() default true;
	
	boolean unique() default false;
}
