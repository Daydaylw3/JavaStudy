package innerclasses;

public class Parcel10 {
	public Destination destination(String dest, float price) {
		return new Destination() {
			private int cost;
			{
				//jdk8之后不必显式声明局部变量一定要是final的，但是实际效果还是一样的
				//Local variable price defined in an enclosing scope must be final or effectively final
//				price = 100F;
				cost = Math.round(price);
				if(cost > 100)
					System.out.println("Over budget");
			}
			private String label = dest;
			@Override
			public String readLabel() {
				return label;
			}
		};
	}
	public static void main(String[] args) {
		Parcel10 p = new Parcel10();
		p.destination("helo", 101);
	}
}
