package exceptions;

class StormyException extends Exception{}
class AnotherException extends Exception{}

class Stormy {
	public Stormy() throws StormyException {}
	public void event() {}
	void rain() {}
}
interface Inning {
	void event()  throws StormyException;
	void shine() throws StormyException;
}
public class StormyInning extends Stormy implements Inning{
	//构造器可以抛出任何异常，但是异常说明必须
	//包含父类构造器的异常说明
	public StormyInning() throws StormyException, AnotherException {
		super();
	}
	//由于基类Stormy实现了event()方法，所以不会在StormyInning类中
	//要求实现Inning接口的event()方法，但是由于基类的event()方法没有
	//声明要抛出StormyException异常，所以Inning里的之后的event()方
	//法将不会改变基类的event()方法的异常接口
	
	@Override
	public void shine() throws StormyException {
		
	}

}
