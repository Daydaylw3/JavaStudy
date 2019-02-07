package io.annotations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName io.annotations.UseCaseTracker
 * @Description 20.0 编写注解处理器<p>
 * 下面是一个非常简单的注解处理器，我们用它来读取PasswordUtils类, 并使用反射机制
 * 寻找@UseCase标记. 我们为其提供一组id, 然后它会列出在PasswordUtils中找到的用
 * 例, 以及缺失的用例
 * 
 * @see io.annotations.UseCase
 * @see io.annotations.PasswordUtils
 * 
 * @author daydaylw3
 * @date 2019年2月2日
 */
public class UseCaseTracker {
	public static void trackUseCases(List<Integer> useCases, Class<?> cl) {
		for (Method m : cl.getDeclaredMethods()) {
			UseCase uc = m.getAnnotation(UseCase.class);
			if (uc != null) {
				System.out.println("found use case: " + uc.id() + " " + uc.description());
				useCases.remove(new Integer(uc.id()));
			}
		}
		for (int i : useCases) {
			System.out.println("warning: missing use case-" + i);
		}
	}
	
	public static void main(String[] args) {
		List<Integer> useCases = new ArrayList<Integer>();
		Collections.addAll(useCases, 47, 48, 49, 50);
		trackUseCases(useCases, PasswordUtils.class);
	}
}