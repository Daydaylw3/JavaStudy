package Initializeandclean;

class Book {
	boolean checkedOut = false;
	Book(boolean checkOut){
		checkedOut = checkOut;
	}
	void checkIn() {
		checkedOut = false;
	}
	protected void finalize() {
		if(checkedOut) {
			System.out.println("Error: checked out");
			//一般情况下，你都不需要做这个
		}
	}
}

public class TerminationCondition {
	public static void main(String[] args) {
		Book novel = new Book(true);
		//有可能清理
		novel.checkIn();
		new Book(true);
		System.gc();
	}
}
