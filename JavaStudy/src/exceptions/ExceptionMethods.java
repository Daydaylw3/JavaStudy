package exceptions;

public class ExceptionMethods {

	public static void main(String[] args) {
		try {
			throw new Exception("My exception");
		}catch(Exception e) {
			System.out.println("caught exception");
			System.out.println("getMessage() : " + e.getMessage());
			System.out.println("getLocalizedMessage() : " + e.getLocalizedMessage());
			System.out.println("toString() : " + e.toString());
			e.printStackTrace(System.out);
		}
	}

}
