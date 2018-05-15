package exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;

//你可以不写try-catch子句或者异常说明，让异常沿着调用栈向上冒泡
//或者你可以用getCause()捕获并处理特定的异常：
class WrapCheckedException {
	void throwRuntimeException(int type) {
		try {
			switch(type) {
			case 0: throw new FileNotFoundException();
			case 1: throw new IOException();
			case 2: throw new RuntimeException("where am i?");
			default: return;
			}
		}catch(Exception e) {	//adapt to unchecked
			throw new RuntimeException(e);
		}
	}
}

class SomeOtherException extends Exception {}

public class TurnOffChecking {

	public static void main(String[] args) {
		WrapCheckedException wce = new WrapCheckedException();
		//you can call throwRuntimeException() without a try
		//block, and let exceptions leave the method
		wce.throwRuntimeException(3);
		//or choose to catch exceptions
		for(int i = 0; i < 4; i ++) {
			try {
				if(i < 3)
					wce.throwRuntimeException(i);
				else
					throw new SomeOtherException();
			}catch(SomeOtherException e) {
				System.out.println("SomeOtherException: " + e);
			}catch(RuntimeException e) {
				try {
					throw e.getCause();
				}catch(FileNotFoundException re) {
					System.out.println("FileNotFoundException: " + re);
				}catch(IOException re) {
					System.out.println("IOException: " + re);
				}catch(Throwable re) {
					System.out.println("Throwable: " + re);
				}
			}
		}
	}

}
