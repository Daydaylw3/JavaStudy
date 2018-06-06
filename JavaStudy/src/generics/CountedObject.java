package generics;

public class CountedObject {
	private static long count = 0;
	private final long id = count++;
	public long id() { return id; }
	public String toString() {
		return this.getClass().getSimpleName() + id;
//		return "countedObject " + id;
	}
}
