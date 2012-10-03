package com.itsz.sht.admin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.model.CsBroadcast;
import com.taifook.adminportal.service.BroadcastService;

/*
 * ��ȡ��admin_portal/com.taifook.adminportal.common.Constants;
 */

public class BroadcastMgrService extends BroadcastService {

	private static BroadcastMgrService service= new BroadcastMgrService();;

	private BroadcastMgrService() {

	}

	/*
	 * ���Channel�������е�Broadcast ��������ΪList
	 * List����Ϊcom.taifook.adminportal.model.CsBroadcast����
	 */
	public List findByChannel(String channel) {
		List result;
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag=false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);	
			result = session.createQuery(
					"from CsBroadcast broadcast where broadcast.channels like '%"
							+ channel + "%'").list();
			session.flush();
			executeFlag=true;
		} catch (HibernateException e) {
			result = null;
			log
					.error("BroadcastService-findByChannel:findByChannel is Exception");
			log.error(e.getMessage());			
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return result;
	}

	/*
	 * ���Channel��lang�������е�Broadcast ��������ΪList
	 * List����Ϊcom.taifook.adminportal.model.CsBroadcast����
	 */
	public List findByChannelAndLang(String channel, String lang) {
		boolean executeFlag=false;
		List result = new ArrayList();
		if (channel == null && lang != null) {
			result = findByLang(lang);
		} else if (channel != null && lang == null) {
			result = findByChannel(channel);
		} else if (channel == null && lang == null) {
			result = this.findAll();
		} else {
			StringBuffer queryHQL = new StringBuffer();

			queryHQL.append("select ");
			queryHQL.append("  broadcast.seqno ");
			queryHQL.append(", broadcast.starttime ");
			queryHQL.append(", broadcast.endtime ");
			queryHQL.append(", broadcast.broadcastLevel ");
			queryHQL.append(", broadcast.contentType ");
			queryHQL.append(", broadcast.channels ");
			queryHQL.append(", broadcast.lastupdatetime ");
			if (lang.equals(Constants.LANG_ENG)) {
				queryHQL.append(", broadcast.contentEnUs ");
			} else if (lang.equals(Constants.LANG_GB)) {
				queryHQL.append(", broadcast.contentZhCn  ");
			} else if (lang.equals(Constants.LANG_BIG5)) {
				queryHQL.append(", broadcast.contentZhTw ");
			} else {
				queryHQL
						.append(", broadcast.contentEnUs, broadcast.contentZhTw, broadcast.contentZhCn ");
			}			
			queryHQL
					.append("from CsBroadcast broadcast where broadcast.channels like '%"
							+ channel + "%' ");
			queryHQL.append(" order by broadcast.lastupdatetime desc,broadcast.seqno desc");
//			System.out.println(queryHQL.toString());
			
			Session session = null;
			Transaction transaction = null;			
			try {
				session = this.openSession();
				transaction = this.beginTransaction(session);
				List objList = session.createQuery(queryHQL.toString())
						.list();				
				session.flush();				
				Iterator it = objList.iterator();
				while (it.hasNext()) {
					result.add(Array2Object((Object[]) it.next(),lang));
				}		
				executeFlag=true;
			} catch (Exception e) {	
				log.error(e);
				return null;
			}finally{
				this.endTransaction(session, transaction, executeFlag);
			}
		}
		return result;
	}

	/*
	 * �������channel��lang����Broadcast ��������ΪList
	 * List����Ϊcom.taifook.adminportal.model.CsBroadcast����
	 */
	public List findByLang(String lang) {
		boolean executeFlag=false;
		List result = new ArrayList();
		StringBuffer queryHQL = new StringBuffer();
		queryHQL.append("select ");
		queryHQL.append("  broadcast.seqno ");
		queryHQL.append(", broadcast.starttime ");
		queryHQL.append(", broadcast.endtime ");
		queryHQL.append(", broadcast.broadcastLevel ");
		queryHQL.append(", broadcast.contentType ");
		queryHQL.append(", broadcast.channels ");
		queryHQL.append(", broadcast.lastupdatetime ");
		if (lang.equals(Constants.LANG_ENG)) {
			queryHQL.append(", broadcast.contentEnUs ");
		} else if (lang.equals(Constants.LANG_GB)) {
			queryHQL.append(", broadcast.contentZhCn  ");
		} else if (lang.equals(Constants.LANG_BIG5)) {
			queryHQL.append(", broadcast.contentZhTw ");
		} else {
			queryHQL
					.append(", broadcast.contentEnUs, broadcast.contentZhCn, broadcast.contentZhTw ");
		}		
		queryHQL.append("from CsBroadcast broadcast");
		
		Session session = null;
		Transaction transaction = null;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			List objList = session.createQuery(queryHQL.toString()).list();
			session.flush();			
			Iterator it = objList.iterator();
			while (it.hasNext()) {
				result.add(Array2Object((Object[]) it.next(),lang));
			}			
			executeFlag=true;
		} catch (Exception e) {
			log.error(e.getMessage());			
			result.clear();
		}finally{
			this.endTransaction(session, transaction, executeFlag);
		}
		return result;
	}
	
	private CsBroadcast Array2Object(Object[] obj,String lang){
		CsBroadcast bc = new CsBroadcast();
		for(int i=0;i<obj.length;i++){
			System.out.println(obj[i]);
		}
		
		bc.setSeqno((Long)obj[0]);
		bc.setStarttime((Date) obj[1]);
		bc.setEndtime((Date) obj[2]);
		bc.setBroadcastLevel((String) obj[3]);
		bc.setContentType((String) obj[4]);
		bc.setChannels((String) obj[5]);
		bc.setLastupdatetime((Date) obj[6]);
		if (lang.equals(Constants.LANG_ENG)) {
			bc.setContentEnUs((String) obj[7]);			
		} else if (lang.equals(Constants.LANG_GB)) {
			bc.setContentZhCn((String) obj[7]);
		} else if (lang.equals(Constants.LANG_BIG5)) {
			bc.setContentZhTw((String) obj[7]);
		} else {
			bc.setContentEnUs((String) obj[7]);
			bc.setContentZhCn((String) obj[8]);
			bc.setContentZhTw((String) obj[9]);			
		}
		return bc;
	}

	/*
	 * ������ʵ��
	 */
	public static BroadcastMgrService getInstance() {
		if (service == null) {
			service = new BroadcastMgrService();
		}
		return service;
	}
		

	public static void main(String[] args) {
		System.out.println("test starting......");
		BroadcastMgrService service = BroadcastMgrService.getInstance();
		List list = service.findByLang(Constants.LANG_BIG5);
		System.out.println("list count:" + list.size());
		Iterator it = list.iterator();
		while (it.hasNext()) {

			CsBroadcast bc = (CsBroadcast) it.next();
			StringBuffer sb = new StringBuffer();
			sb.append("=============================\r\n");
			sb.append(bc.getSeqno() + "\r\n");
			sb.append(bc.getStarttime() + "\r\n");
			sb.append(bc.getEndtime() + "\r\n");
			sb.append(bc.getContentType() + "\r\n");
			sb.append(bc.getChannels() + "\r\n");
			sb.append(bc.getContentZhTw() + "\r\n");
			sb.append(bc.getContentEnUs() + "\r\n");
			sb.append(bc.getContentZhCn() + "\r\n");			
//			System.out.println(sb.toString());
		}
		System.out.println("test end......");
	}

}
