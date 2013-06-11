package com.amaker.test;

public class MyActivity implements Activity{
	@Override
	public void onCreate() {
		System.out.println("onCreate...........");
	}
	@Override
	public void onDestroy() {
		System.out.println("onDestroy...........");
	}
	@Override
	public void onStart() {
		System.out.println("onStart...........");
	}
}
