package innerclasses;

public class Parcel11 {
	private static class Inner implements Contents {
		private int i = 11;
		@Override
		public int value() {
			// TODO Auto-generated method stub
			return i;
		}
		
	}
	protected static class Inner2 implements Destination {
		private String label;
		private Inner2(String whereTo) { label = whereTo; }
		@Override
		public String readLabel() {
			// TODO Auto-generated method stub
			return label;
		}
		public static void f() {};
		static int x = 10;
		//又一个嵌套类
		static class AnotherInner {
			public static void f() {}
			static int x = 10;
		}
		
	}
	public static Contents content() {
		return new Inner();
	}
	public static Destination dest() {
		return new Inner2("helo");
	}
	public static void main(String[] args) {
		Contents c = Parcel11.content();
		Destination d = Parcel11.dest();
	}
}
