package typeinfo;


/*
 * 假设你获得了一大笔风险投资，现在要开始招兵买马了
 * 但是在虚位以待时，你可以将Person空对象放置每个
 * Position上：
 * */
class Position {
	private String title;
	private Person person;
	public Position(String jobTitle, Person employee) {
		title = jobTitle;
		person = employee;
		if(person == null) 
			person = Person.NULL;
	}
	public Position(String title) {
		//这样不能调用，构造方法不能相互调用?
//		Position(title, (Person)null);
		this.title = title;
		person = Person.NULL;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String newTitle) {
		title = newTitle;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person newPerson) {
		person = newPerson;
		if(person == null)
			person = Person.NULL;
	}
	public String toString() {
		return "Position: " + title + " " + person;
	}
}
