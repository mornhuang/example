package com.itsz.sht.admin.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.model.CsParameter;
import com.taifook.adminportal.service.ParameterService;

/*
 * ��ȡ�� com.taifook.adminportal.common.Constants;
 */

public class ChannelMgrService extends ParameterService {
	private final String CHANNEL_CLASSID = "7";

	private final String STARTED = "started";

	private final String STOPPED = "stopped";

	private final String CHANNELSERVER_ACTIVE_COUNT = "ChannelServerActiveCount";

	private static ChannelMgrService service;	

	private ChannelMgrService() {

	}

	// �õ�ȫ������Channel,����Ϊcom.taifook.adminportal.model.CsParameter
	public List findChannelsByAll() {
		return super.loadChannels(CHANNEL_CLASSID);
	}

	// ��key�õ�Channel,����Ϊcom.taifook.adminportal.model.CsParameter
	public Object findChannelsByChannelCode(String channelCode) {
		return super.findById(channelCode);
	}

	// ����һ��channel��״̬
	public void setChannelState(String channelcode, String state) {
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);	
			CsParameter channel = (CsParameter)this.findChannelsByChannelCode(channelcode);
			channel.setValue(state);
			session.update(channel);
			session.flush();
			executeFlag=true;
		} catch (Exception e) {
			log.error(e);
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}		
	}

	//�������channel�Ƿ�򿪹���
	public boolean canService(String key) {
		return ((CsParameter) super.findById(key)).getValue().equalsIgnoreCase(
				this.STARTED) ? true : false;
	}

	// ��ȡ��ǰ���ChannelServer����
	public int loadCurrentChannelServerActiveCount() {

		CsParameter para = (CsParameter) super
				.findById(this.CHANNELSERVER_ACTIVE_COUNT);
		if (para != null) {
			return Integer.parseInt(para.getValue());
		}
		return 0;
	}

	// ���/����(��increaseΪ����ʱ)��ǰ���ChannelServer����
	public boolean increaseCurrentChannelServerActiveCount(int increase) {
		CsParameter para;
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);	
			para = (CsParameter) session.get(CsParameter.class,
					this.CHANNELSERVER_ACTIVE_COUNT);
			session.flush();			
			if (para == null) {				
				return false;
			}
			int currentActiveCount = 0;
			try {
				currentActiveCount = Integer.parseInt(para.getValue());
			} catch (Exception e) {
				currentActiveCount = 0;
			}
			currentActiveCount = currentActiveCount + increase;
			currentActiveCount = currentActiveCount < 0 ? 0
					: currentActiveCount;
			para.setValue(String.valueOf(currentActiveCount));
			session.update(para);
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
			log.error(e);			
			return false;
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return true;
	}

	// �õ���ǰ���ChannelServer����
	public int getCurrentChannelServerActiveCount() {
		CsParameter para;
		int currentActiveCount = 0;
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);	
			para = (CsParameter) session.get(CsParameter.class,
					this.CHANNELSERVER_ACTIVE_COUNT);
			session.flush();
			if (para == null) {				
				return 0;
			}

			try {
				currentActiveCount = Integer.parseInt(para.getValue());
			} catch (Exception e) {
				currentActiveCount = 0;
			}
			executeFlag=true;
		} catch (HibernateException e) {
			log.error(e);			
			return currentActiveCount;
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}

		return currentActiveCount;
	}

	public static ChannelMgrService getInstance() {
		if (service == null) {
			service = new ChannelMgrService();
		}
		return service;
	}

	public static void main(String[] args) {
		ChannelMgrService service = ChannelMgrService.getInstance();
		CsParameter para = (CsParameter) service
				.findChannelsByChannelCode(Constants.CHANNEL_CODE_WEB);
		/*
		 * List list=service.findChannelsByAll(); Iterator it=list.iterator();
		 * while(it.hasNext()){ CsParameter para=(CsParameter)it.next();
		 */

		StringBuffer sb = new StringBuffer();
		sb.append("===============================\r\n");
		sb.append("Classid: " + para.getClassid() + "\r\n");
		sb.append("readonly: " + para.getReadonly() + "\r\n");
		sb.append("updateTime: " + para.getUpdateTime() + "\r\n");
		sb.append("key: " + para.getKey() + "\r\n");
		sb.append("value: " + para.getValue() + "\r\n");
		sb.append("description: " + para.getDescription() + "\r\n");
		System.out.println(sb.toString());
	}
}
