package com.itsz.sht.admin.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//��Ҫ���øü�ع���ʱ��startMonitor(),endMonitor()��canService�������ע��˵�����ֵ�ע��ȥ������
public class SystemMonitorManager{
	private static Map monitorsMap = new HashMap();

	private static Log log = LogFactory.getLog(SystemMonitorManager.class);

	private SystemMonitorManager() {

	}

	/*
	 * ��ؿ�ʼ���� ����:serverName(�������) ����ֵΪ:long���͵Ŀ�ʼʱ��
	 * �ڿ�ʼ��ʱ��������ڸ÷���ļ���������һ����������Map��
	 */
	public static long startMonitor(String serverName) {
		//���´���ι���ע�����μ�ع��ܣ���Ҫ���øü�ع��ܽ�ע��ȥ������
		return 1;
/*		if (!monitorsMap.containsKey(serverName)) {
			monitorsMap.put(serverName, new MonitorService(serverName));
		}
		return ((MonitorService) monitorsMap.get(serverName)).startMonitor();*/
	}

	/*
	 * ��ؽ���
	 * ����:serverName(�������),actionName(������Ϊ),startTime(��ʼʱ��),endTime(����ʱ��),isException(�Ƿ�����쳣)
	 * ����ִ�����ں�̨������Ӧ�Ĳ��Լ���̣߳�
	 */
	public static void endMonitor(String serverName, String actionName,
			long startTime, long endTime, boolean isException) {
		//���´���ι���ע�����μ�ع��ܣ���Ҫ���øü�ع��ܽ�ע��ȥ������
		/*try {
			((MonitorService) monitorsMap.get(serverName)).endMonitor(
					actionName, startTime, endTime, isException);
		} catch (Exception e) {
			log.error("no such service exception");
		}*/
	}
	
	public static void cancelMonitor(){
		Set keySet=monitorsMap.keySet();
		Iterator it=keySet.iterator();
		while(it.hasNext()){
			String key=(String)it.next();
			//((MonitorService) monitorsMap.get(key)).cancelMonitor();
		}
	}


	/*
	 * �����Ƿ����,trueΪ���ã�falseΪ������
	 */
	public static boolean canService(String serviceName) {
		return true;
		//���´���ι���ע�����μ�ع��ܣ���Ҫ���øü�ع��ܽ�ע��ȥ������
/*		try {
			return ((MonitorService) monitorsMap.get(serviceName)).canService();
		} catch (Exception e) {
			log.error("no such service exception");
			return true;
		}*/

	}

}
