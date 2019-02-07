package io.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName io.annotations.UseCase
 * @Description 20.1.1 定义注解
 * @see io.annotations.PasswordUtils
 * 
 * @author daydaylw3
 * @date 2019年2月2日
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCase {
	public int id();
	public String description() default "no description";
}
