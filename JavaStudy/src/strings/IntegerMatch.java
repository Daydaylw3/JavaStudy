package strings;

public class IntegerMatch {

	public static void main(String[] args) {
		//以一个“-”或者什么都没有开头
		System.out.println("-1234".matches("-?\\d+"));
		System.out.println("5678".matches("-?\\d+"));
		System.out.println("+911".matches("-?\\d+"));
		//以一个“+”或者一个“-”或者什么都没有开头
		System.out.println("+911".matches("(-|\\+)?\\d+"));
	}

}
