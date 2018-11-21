package com.heloworld;

public class StrutsHelloWorld implements HelloWorld {

	@Override
	public void sayHelo(String helo) {
		System.out.println("Struts say helo + " + helo);
	}

}
