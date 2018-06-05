package typeinfo;

import java.util.ArrayList;


public class Staff extends ArrayList<Position>{
	public void add(String title, Person person) {
		add(new Position(title, person));
	}
	public void add(String... titles) {
		for(String title : titles)
			add(new Position(title));
	}
	public Staff(String... titles) {
		add(titles);
	}
	/*
	 * 在某些地方仍然需要检测空对象，这与检查是否为null没有差异
	 * 但是在其他地方（例如toString()转换）就不必执行额外的测试了
	 * */
	public boolean positionAvailable(String title) {
		for(Position position : this)
			if(position.getTitle().equals(title) &&
					position.getPerson() == Person.NULL)
				return true;
		return false;
	}
	public void fillPosition(String title, Person hire) {
		for(Position position : this)
			if(position.getTitle().equals(title) && 
					position.getPerson() == Person.NULL) {
				position.setPerson(hire);
				return;
			}
		throw new RuntimeException("Position " + title + " not available");
	}
	public static void main(String[] args) {
		Staff staff = new Staff("President", "CTO", "Market manager");
		staff.fillPosition("President", new Person("kevin", "durant", "goldengate"));
		staff.fillPosition("CTO", new Person("lebron", "james", "kecun"));
		System.out.println(staff);
	}
}
