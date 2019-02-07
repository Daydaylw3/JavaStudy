package io.annotations;

import java.util.List;

/**
 * @ClassName io.annotations.PasswordUtils
 * @Description 20.1.1 定义注解
 * @see io.annotations.UseCase
 * 
 * @author daydaylw3
 * @date 2019年2月2日
 */
public class PasswordUtils {
	@UseCase(id = 47, description = "Passwords must contain at least one numeric")
	public boolean validatePassword(String password) {
		return (password.matches("\\w*\\d\\w*"));
	}
	
	@UseCase(id = 48)
	public String encryptPassword(String password) {
		return new StringBuilder(password).reverse().toString();
	}
	
	@UseCase(id = 49, description = "New password can't equal previously used ones")
	public boolean checkForNewPassword(List<String> prevPasswords, String password) {
		return !prevPasswords.contains(password);
	}
}
