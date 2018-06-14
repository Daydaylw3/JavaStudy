package generics;

interface FactoryI<T> {
	T create();
}
class Foo2<T> {
	private T x;
	public <F extends FactoryI<T>> Foo2(F factory){
		x = factory.create();
	}
}
class IntegerFactory implements FactoryI<Integer>{
	@Override
	public Integer create() {
		return new Integer(0);
	}
}
class Widge {
	public static class Factory implements FactoryI<Widge> {
		@Override
		public Widge create() {
			return new Widge();
		}
	}
}

public class FactoryConstraint {
	public static void main(String[] args) {
		new Foo2<Integer>(new IntegerFactory());
		new Foo2<Widge>(new Widge.Factory());
	}
}
