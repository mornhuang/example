package com.amaker.test;

public class AndroidSystem {
	public static final int CREATE=1;
	public static final int START=2;
	public static final int DESTORY=3;
	public void run(Activity a,int state){
		switch (state) {
		case CREATE:
			a.onCreate();
			break;
		case START:
			a.onStart();
			break;
		case DESTORY:
			a.onDestroy();
			break;
		}
	}
}
