package io.annotations.database;

/**
 * @ClassName io.annotations.database.Member
 * @Description 20.2.3 生成外部文件<p>
 * 一个简单的JavaBean定义, 我们在其中应用上了定义的注解
 * 
 * @see io.annotations.database.DBTable
 * @see io.annotations.database.Constraints
 * @see io.annotations.database.SQLString
 * @see io.annotations.database.SQLInteger
 * 
 * @author daydaylw3
 * @date 2019年2月2日
 */
@DBTable(name = "MEMBER")
public class Member {
	
	@SQLString(30) String firstName;		// 如果程序员的注解中定义了名为value的元素, 并且在应用该注解的时候, 如果
										// 该元素是唯一需要赋值的一个元素, 那么此时无需使用名-值对的这种语法,而只
										// 需在括号内给出value元素所需的值即可
	@SQLString(50) String lastName;
	@SQLInteger Integer age;
	@SQLString(value = 30, constraints = @Constraints(primaryKey = true)) String handle;
	static int memberCount;
	
	@Override
	public String toString() {
		return handle;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public Integer getAge() {
		return age;
	}
	
	public String getHandle() {
		return handle;
	}
	
}
