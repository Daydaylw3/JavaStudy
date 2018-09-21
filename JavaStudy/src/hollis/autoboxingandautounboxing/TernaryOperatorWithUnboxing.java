package hollis.autoboxingandautounboxing;

public class TernaryOperatorWithUnboxing {
	public static void main(String... args) {
		boolean flag = true;
		Integer i = 0;
		int j = 1;
		int k = flag ? i : j;
	}
}
