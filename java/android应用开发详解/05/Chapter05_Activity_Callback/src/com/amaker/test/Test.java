package com.amaker.test;

public class Test {
	public static void main(String[] args) {
		AndroidSystem system = new AndroidSystem();
		Activity a = new MyActivity();
		system.run(a, AndroidSystem.CREATE);
		system.run(a, AndroidSystem.START);
		system.run(a, AndroidSystem.DESTORY);
	}
}
