package reuse.exercise;

class Exercise8Father {
	public Exercise8Father(int i) {
		System.out.println("Exercise8Father " + i);
	}
}

public class Exercise8 extends Exercise8Father{
	public Exercise8() {
		super(1);
	}
	public Exercise8(int i) {
		super(2);
	}
	public static void main(String[] args) {
		Exercise8 e8;
		e8 = new Exercise8(1);
		e8 = new Exercise8();
	}

}
