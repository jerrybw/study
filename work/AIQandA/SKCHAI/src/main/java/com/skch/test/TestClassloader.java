package com.skch.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class TestClassloader {

	public static void main(String[] args) throws IOException {
		ClassLoader classLoader = TestClassloader.class.getClassLoader();
		InputStream in = classLoader.getResourceAsStream("aaa/预约脚本样例.xml");
		File file = new File("d:/脚本");
		File[] listFiles = file.listFiles();
		for (File file2 : listFiles) {
			System.out.println(file2.getName());
		}
		System.out.println(file);
		System.out.println(in);
		in.close();
	}
	
}
