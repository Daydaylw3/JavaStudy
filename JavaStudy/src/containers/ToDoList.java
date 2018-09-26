package containers;

import java.util.PriorityQueue;

/*
 * 17.7.1 优先级队列
 * */
class ToDoList extends PriorityQueue<ToDoList.ToDoItem>{
	static class ToDoItem implements Comparable<ToDoItem> {
		private char primary;
		private int secondary;
		private String item;
		public ToDoItem(String td, char pri, int sec) {
			primary = pri;
			secondary = sec;
			item = td;
		}
		@Override
		public int compareTo(ToDoItem arg) {
			if(primary > arg.primary)
				return +1;
			if(primary == arg.primary)
				if(secondary > arg.secondary)
					return +1;
				else if(secondary == arg.secondary)
					return 0;
			return -1;
		}
		@Override
		public String toString() {
			return Character.toString(primary)
					 + secondary + ": " + item;
		}
	}
	
	//并非重写
	public void add(String td, char pri, int sec) {
		super.add(new ToDoItem(td, pri, sec));
	}
	
	public static void main(String... args) {
		ToDoList toDoList = new ToDoList();
		toDoList.add("倒垃圾", 'C', 4);
		toDoList.add("做饭", 'A', 4);
		toDoList.add("喂狗", 'D', 4);
		toDoList.add("浇花", 'B', 4);
		toDoList.add("make love", 'A', 2);
		
//		System.out.println(toDoList.element());
		while(!toDoList.isEmpty()) {
			System.out.println(toDoList.remove());
		}
	}
}
