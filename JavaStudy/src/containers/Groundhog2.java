package containers;
/*
 * 17.9 散列与散列码
 * 重写了hashCode()方法 & equals()方法
 * */
public class Groundhog2 extends Groundhog {
	public Groundhog2(int n) {
		super(n);
	}
	@Override
	public int hashCode() { return number; }
	@Override
	public boolean equals(Object obj) { 
		return obj instanceof Groundhog2 &&
				(number == ((Groundhog2)obj).number);
	}
}
