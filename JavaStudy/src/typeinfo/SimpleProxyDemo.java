package typeinfo;

interface Interface {
	void doSomething() ;
	void somethingElse(String arg);
}

class RealObject implements Interface {
	public void doSomething() {
		System.out.println("doSomething");
	}
	public void somethingElse(String arg) {
		System.out.println("somethingElse: " + arg);
	} 
}

class SimpleProxy implements Interface {
	
}

class SimpleProxyDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
