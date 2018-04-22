package Initializeandclean;

public class InitialValues {
	boolean t;
	char c;
	byte b;
	short s;
	int i;
	long l;
	float f;
	double d;
	InitialValues reference;
	void printInitialValues() {
		System.out.println(t);
		System.out.println(c);
		System.out.println(b);
		System.out.println(s);
		System.out.println(i);
		System.out.println(l);
		System.out.println(f);
		System.out.println(d);
		System.out.println(reference);
	}
	public static void main(String[] args) {
		InitialValues iv = new InitialValues();
		iv.printInitialValues();
	}
}
