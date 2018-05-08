package exception;

class SimpleException extends Exception {
	
}

public class InheritingExceptions {
	public void f() throws SimpleException {
		System.out.println("抛出SimpleException");
		throw new SimpleException();
	}
	public static void main(String[] args) {
		InheritingExceptions i = new InheritingExceptions();
		try {
			i.f();
		}catch(SimpleException e) {
			System.out.println("catch it");
		}
	}
}
