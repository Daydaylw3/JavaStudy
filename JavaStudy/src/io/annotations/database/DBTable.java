package io.annotations.database;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName io.annotations.database.DBTable
 * @Description 20.2.3 生成外部文件<p>
 * 假设你希望提供一些基本的对象/关系映射功能，能够自动生成数据库表, 用以存储JavaBean对
 * 象. 你可以选择使用XML描述文件, 指明类的名字、每个成员以及数据库映射的相关信息. 然而,
 * 如果使用注解的话, 你可以将所有信息都保存在JavaBean源文件中. <p>
 * 以下是一个注解的定义, 它告诉注解处理器, 你需要为我生成一个数据库表
 * 
 * @author daydaylw3
 * @date 2019年2月2日
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {
	
	public String name() default "";
	
}
