package exceptions;

//对于在构造阶段可能会抛出的异常，并且
//要求清理的类，最安全的方式是使用嵌套
//的try子句
public class Cleanup {
	public static void main(String[] args) {
		try {
			InputFile in = new InputFile("src/exceptions/Cleanup.java");
			try {
				String s;
				int i = 1;
				while((s = in.getLine()) != null) {
					;//
				}
			}catch(Exception e) {
				System.out.println("caught exception in main");
				e.printStackTrace(System.out);
			}finally {
				in.dispose();
			}
		}catch(Exception e) {
			System.out.println("InputFile construction failed");
		}
	}
}
