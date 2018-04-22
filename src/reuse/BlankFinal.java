package reuse;

class Poppet {
	private int i;
	Poppet(int ii) {
		i = ii;
	}
}

public class BlankFinal {
	private final int i = 0;
	private final int j; //空值
	private final Poppet p; //空引用
	//空的final域必须在构造器中被初始化
	public BlankFinal() {
		j = i;
		p = new Poppet(1);
	}
	public BlankFinal(int x) {
		j = x;
		p = new Poppet(x);
	}
	public static void main(String[] args) {
		new BlankFinal();
		new BlankFinal(47);
	}

}
