package com.skch.test;

import java.lang.reflect.Method;

public class TestReflect {

	
	public static void main(String[] args) throws Exception {
		Class<?> forName = Class.forName("com.skch.test.Man");
		Object newInstance = forName.newInstance();
		Method method = forName.getMethod("say");
		method.invoke(newInstance);
	}
}

interface Person{
	public void say();
}

class Man implements Person{

	@Override
	public void say() {
		System.out.println("i`m a man");
	}
	
}

class Woman implements Person{

	@Override
	public void say() {
		System.out.println("i`m a Woman");
	}
	
}
