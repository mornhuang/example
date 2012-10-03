package com.itsz.sht.admin.service;

import com.taifook.adminportal.model.CsServicemonitor;
import com.taifook.adminportal.service.SystemMonitorService;

public class SystemMonitorMgrService extends SystemMonitorService {

	private static SystemMonitorMgrService service = new SystemMonitorMgrService();

	private SystemMonitorMgrService() {

	}

	public void saveServiceMonitor(CsServicemonitor monitor) {
		this.save(monitor);
	}

	public static SystemMonitorMgrService getInstance() {
		if (service == null) {
			service = new SystemMonitorMgrService();
		}
		return service;
	}
}
