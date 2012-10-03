package com.itsz.sht.admin.common;

import com.itsz.sht.admin.service.thread.EduThreadPoolManager;

public abstract class AbstractTask implements Task {
	
	public abstract void runTask();
	
	public void run(){
		System.out.println("+++++++++++++++++"+Thread.currentThread().getName()+" start running+++++++++++++++++");
		runTask();
		System.out.println("+++++++++++++++++"+Thread.currentThread().getName()+" finished+++++++++++++++++");
	}

}
