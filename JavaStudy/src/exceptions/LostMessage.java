package exceptions;


//演示我们如何丢失异常
class VeryImportantException extends Exception {
	@Override
	public String toString() {
		return "a very important exception";
	}
}
class HoHumException extends Exception {
	@Override
	public String toString() {
		return "a trivial exception";
	}
}

public class LostMessage {

	void f() throws VeryImportantException {
		throw new VeryImportantException();
	}
	void dispose() throws  HoHumException {
		throw new HoHumException();
	}
	
	public static void main(String[] args) {
		try {
			LostMessage lm = new LostMessage();
			
			try {
				lm.f();
			}finally {
				//调用f()方法抛出VeryImportantException异常后，进入了
				//finally分支，这时抛出HoHumException异常，于是在外层
				//的catch分支则只能捕获到HoHumException异常
				lm.dispose();
			}
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
