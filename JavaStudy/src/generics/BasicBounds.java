package generics;

interface HasColor { java.awt.Color getColor(); }

class Color<T extends HasColor> {
	T item;
	Color(T item) { this.item = item; }
	T getItem() { return item; }
	//边界允许你调用该边界类型的方法
	java.awt.Color color() { return item.getColor(); }
}

class Dimension { public int x, y, z; }

//先类，再接口
//class ColoredDimension<T extends HasColor & Dimension> {

class ColoredDimension<T extends Dimension & HasColor> {
	T item;
	public ColoredDimension(T item) {
		this.item = item;
	}
	T getItem() { return item; }
	java.awt.Color color() { return item.getColor(); }
	int getX() { return item.x; }
	int getY() { return item.y; }
	int getZ() { return item.z; }
}

interface Weight { int weight(); }

class Solid<T extends Dimension & HasColor & Weight> {
	T item;
	public Solid(T item) { this.item = item; }
	T getItem() { return item; }
	java.awt.Color color() { return item.getColor(); }
	int getX() { return item.x; }
	int getY() { return item.y; }
	int getZ() { return item.z; }
	int weight() { return item.weight(); }
}

class Bounded extends Dimension implements HasColor, Weight {
	@Override
	public java.awt.Color getColor() {
		return null;
	}
	@Override
	public int weight() {
		return 0;
	}
}

public class BasicBounds {
	public static void main(String[] args) {
		Solid<Bounded> solid = new Solid<Bounded>(new Bounded());
		System.out.println(solid.color());
		System.out.println(solid.weight());
	}
}
