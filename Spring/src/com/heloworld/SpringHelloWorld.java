package com.heloworld;

public class SpringHelloWorld implements HelloWorld {

	@Override
	public void sayHelo(String helo) {
		System.out.println("Spring say helo " + helo);
	}

}
