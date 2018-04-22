package reuse;

class Plate{
	Plate(int i){
		System.out.println("Plate con");
	}
}
class DinnerPlate extends Plate{
	DinnerPlate(int i){
		super(i);
		System.out.println("DinnerPlate con");
	}
}
class Utensil {
	public Utensil(int i) {
		System.out.println("Utensil con");
	}
}
class Spoon extends Utensil {
	Spoon(int i){
		super(i);
		System.out.println("Spoon con");
	}
}
class Fork extends Utensil {
	Fork(int i){
		super(i);
		System.out.println("Fork con");
	}
}
class Knife extends Utensil {
	Knife(int i){
		super(i);
		System.out.println("Knife con");
	}
}
class Custom{
	Custom(int i){
		System.out.println("Custom con");
	}
}
public class PlaceSetting extends Custom{
	private Spoon sp;
	private Fork frk;
	private Knife kn;
	private DinnerPlate pl;
	public PlaceSetting(int i) {
		super(i + 1);
		sp = new Spoon(i + 2);
		frk = new Fork(i + 3);
		kn = new Knife(i + 4);
		pl = new DinnerPlate(i + 5);
		System.out.println("PlaceSetting con");
	}
	public static void main(String[] args) {
		PlaceSetting x = new PlaceSetting(9);
	}

}
