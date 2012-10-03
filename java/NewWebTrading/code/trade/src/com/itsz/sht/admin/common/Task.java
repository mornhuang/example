package com.itsz.sht.admin.common;

import javax.servlet.http.HttpServletRequest;

public interface Task extends Runnable {
	public void runTask();
}
