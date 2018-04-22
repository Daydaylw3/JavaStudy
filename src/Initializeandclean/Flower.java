package Initializeandclean;

public class Flower {
	int petalCount = 0;
	String s = "initial value";
	Flower(int petals){
		petalCount = petals;
		System.out.println("Constructor w/ int arg only, petalCount= "
				+ petalCount);
	}
	Flower(String ss){
		System.out.println("Constructor w/ String arg only, s = " + ss);
		s = ss;
	}
	Flower(String s, int petals){
		this(petals);
		//!	this(s);	//不能这样调用
		this.s = s;
		System.out.println("String & int args");
	}
	Flower() {
		this("hi", 47);
		System.out.println("Default constructor (no args)");
	}
	void printPetalCount() {
		//! this(11);	//不能这样调用
		System.out.println("petalCount = " + petalCount + " s = " + s);
	}
	
	public static void main(String[] args) {
		Flower x = new Flower();
		x.printPetalCount();
	}

}
