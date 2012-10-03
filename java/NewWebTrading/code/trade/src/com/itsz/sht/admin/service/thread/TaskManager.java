package com.itsz.sht.admin.service.thread;

import java.util.Date;
import java.util.LinkedList;
import java.util.Vector;
import java.util.Collections;

import com.itsz.sht.admin.common.Task;

public class TaskManager {
	
/*	private static LinkedList taskV = new LinkedList();
	
	private TaskManager() {
		
	}
	
	public static LinkedList getTask(){
		return taskV;
	}

	public static void addTask(Task task) {
		synchronized (taskV) {
			taskV.add(task);			
			taskV.notify();
		}
	}

	public static boolean hasTask() {
		return !taskV.isEmpty();
	}

	public synchronized static Task getNextTask() {
		if (taskV.size() > 0) {
			return (Task) taskV.removeFirst();
		}
		return null;
	}*/

	public static void processTask(Task task) {
		//ThreadPool.getInstance().processTask(task);
		EduThreadPoolManager.processTask(task);
	}

	public static void cancelThread() {
		//ThreadPool.getInstance().cancel();
		EduThreadPoolManager.cancelThread();
	}

}
