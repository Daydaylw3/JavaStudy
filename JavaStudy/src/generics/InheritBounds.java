package generics;

//HoldItem 直接持有一个对象，这种行为被继承到了Colored2中
class HoldItem<T> {
	T item;
	HoldItem(T item) { this.item = item; }
	T getItem() { return item; }
}

class Color2<T extends HasColor> extends HoldItem<T> {
	Color2(T item) {
		super(item);
	}
	java.awt.Color color() { return item.getColor(); }
}

class ColoredDimension2<T extends Dimension & HasColor> extends Color2<T> {
	ColoredDimension2(T item) {
		super(item);
	}
	int getX() { return item.x; }
	int getY() { return item.y; }
	int getZ() { return item.z; }
}

class Solid2<T extends Dimension & HasColor & Weight> extends ColoredDimension2<T> {

	Solid2(T item) {
		super(item);
	}
	int weight() { return item.weight(); }
}

public class InheritBounds {
	public static void mian(String[] args) {
		Solid2<Bounded> solid2 = new Solid2<Bounded>(new Bounded());
		solid2.color();
		solid2.weight();
	}
}
