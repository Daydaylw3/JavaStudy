package tuanzhang.classinitialization;

public class CloneableStudent implements Cloneable {
	private int id;
	public CloneableStudent(Integer id) { this.id = id; }
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	public static void main(String[] args) throws CloneNotSupportedException {
		CloneableStudent stu1 = new CloneableStudent(123);
		CloneableStudent stu2 = (CloneableStudent)stu1.clone();
		System.out.println(stu1 == stu2);
	}
}
