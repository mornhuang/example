package com.taifook.adminportal.service;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.taifook.adminportal.common.HibernateBase;
import com.taifook.adminportal.common.util.page.HibernatePage;
import com.taifook.adminportal.common.util.page.Page;
import com.taifook.adminportal.dao.OnLineUserDAO;
import com.taifook.adminportal.model.CsOnlineuser;
import com.taifook.adminportal.model.CsOnlineuserKey;
import com.taifook.adminportal.model.OnLineUserInfo;

/**
 * <p> * Title: admin_portal *
 * </p>
 * <p> * Description: *
 * </p>
 * <p> * Copyright: Copyright (c) 2006 *
 * </p>
 * <p> * Company: TaiFook itsz *
 * </p>
 * 
 * @author hsli
 * @version 1.0
 */
public class OnLineUserService extends HibernateBase implements OnLineUserDAO {

	public OnLineUserService() {
		super();
	}

	public Object findById(Serializable id) {
		Object object = null;
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag = false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			object = session.get(CsOnlineuser.class, id);
			session.flush();
			executeFlag = true;
		} catch (HibernateException e) {
			log.error("OnLineUserService-findById: findbyid exception!");
			log.error(e);
		} finally {
			this.endTransaction(session, transaction, executeFlag);
		}
		return object;
	}

	public List findAll() {
		List result;
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag = false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			result = session.createQuery("from CsOnlineuser").list();
			session.flush();
			executeFlag = true;
		} catch (HibernateException e) {
			result = null;
			log.error("OnLineUserService-findAll:findAll is Exception");
			log.error(e.getMessage());
		} finally {
			this.endTransaction(session, transaction, executeFlag);
		}
		return result;
	}

	public boolean deleteAll() {
		// TODO Auto-generated method stub
		//List result = null;
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag = false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			Query query = session.createQuery("delete from CsOnlineuser ");
			query.executeUpdate();
			session.flush();

			/*			result = session.createQuery("from CsOnlineuser").list();
			 Iterator it = result.iterator();
			 while (it.hasNext()) {
			 CsOnlineuser onlineuser = (CsOnlineuser) it.next();
			 session.delete(onlineuser);
			 session.flush();
			 }
			 result = null;*/
			executeFlag = true;
		} catch (HibernateException e) {
			//result = null;
			log.error("OnLineUserService-deleteAll:deleteAll is Exception");
			log.error(e.getMessage());
			return false;
		} finally {
			this.endTransaction(session, transaction, executeFlag);
		}

		return true;
	}

	public void deletebyUser(OnLineUserInfo user) {
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag = false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			StringBuffer str = new StringBuffer();
			str.append("delete from CsOnlineuser where channelcode_pk='");
			str.append(user.getChannelCode());
			str.append("' and userid_pk='");
			str.append(user.getLoginId());
			str.append("'");
			Query query = session.createQuery(str.toString());
			query.executeUpdate();
			session.flush();
			executeFlag = true;
		} catch (HibernateException e) {
			log.error(e);
		} finally {
			this.endTransaction(session, transaction, executeFlag);
		}
	}
	
	public void deletebyLiveTime(long livesecond) {
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag = false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			Query query = session.createQuery("delete from CsOnlineuser where ((SYSDATE-(to_date(to_char(logintime,'YYYY-MM-DD HH24:MI:SS'),'YYYY-MM-DD HH24:MI:SS')))*24*60*60) >= "+livesecond);
			query.executeUpdate();
			session.flush();
			executeFlag = true;
		} catch (HibernateException e) {
			log.error(e);
		} finally {
			this.endTransaction(session, transaction, executeFlag);
		}
	}
	
	public void saveOrUpdate(Object object) {
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag = false;
		try {
			if (object != null && object instanceof CsOnlineuser) {
				CsOnlineuser onlineuser = (CsOnlineuser) object;
				CsOnlineuserKey id = onlineuser.getId();
				if (id != null) {
					session = this.openSession();
					transaction = this.beginTransaction(session);
					//session.saveOrUpdate(object);
					try {
						CsOnlineuser del_onlineuser = (CsOnlineuser) session.get(CsOnlineuser.class, id);
						if (del_onlineuser != null) {
							session.delete(del_onlineuser);
						}
						session.save(onlineuser);
						session.flush();
					} catch (Exception e) {
						this.endTransaction(session, transaction, executeFlag);
						session = this.openSession();
						transaction = this.beginTransaction(session);
						session.update(onlineuser);
						session.flush();
					}
					//session.flush();
					executeFlag = true;
				}
			}
		} catch (HibernateException e) {
			log.error("OnLineUserService-saveOrUpdate:saveOrUpdate is exception!");
			log.error(e);
		} finally {
			this.endTransaction(session, transaction, executeFlag);
		}
	}

	public void deletebyId(Serializable id) {
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag = false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			CsOnlineuser onlineuser = (CsOnlineuser) session.get(CsOnlineuser.class, id);
			if (onlineuser != null) {
				session.delete(onlineuser);
			}
			session.flush();
			executeFlag = true;
		} catch (HibernateException e) {
			log.error(e);
		} finally {
			this.endTransaction(session, transaction, executeFlag);
		}
	}

	public List findByChannelCode() {
		List result;
		Session session = null;
		Transaction transaction = null;
		boolean executeFlag = false;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			result = session.createQuery("select onlineuser.id.channelcode,count(*) from CsOnlineuser onlineuser group by onlineuser.id.channelcode").list();
			session.flush();
			executeFlag = true;
		} catch (HibernateException e) {
			result = null;
			log.error("OnLineUserService-findByChannelCode:findByChannelCode is Exception");
			log.error(e);
		} finally {
			this.endTransaction(session, transaction, executeFlag);
		}
		return result;
	}

	public Page findByPage(Object[] Paras, int pageNumber, int pageSize) {
		boolean executeFlag = false;
		StringBuffer hql = new StringBuffer();
		StringBuffer filters = new StringBuffer("");
		if (Paras != null && Paras.length > 0) {
			filters.append("where 1=1 ");
			for (int index = 0; index < Paras.length; index++) {
				filters.append(" and " + Paras[index]);
			}
		}

		hql.append("from CsOnlineuser " + filters);
		log.info("hql:" + hql);
		Session session = null;
		Transaction transaction = null;
		HibernatePage hibernatePage = null;
		try {
			session = this.openSession();
			transaction = this.beginTransaction(session);
			int count = ((Integer) session.createQuery("select count(*)" + hql).uniqueResult()).intValue();
			Query theQuery = session.createQuery(hql.toString() + " order by logintime desc, id.channelcode asc");
			hibernatePage = new HibernatePage(theQuery, pageNumber, pageSize, count);
			session.flush();
			executeFlag = true;
			log.info("hql:" + hql + "count:" + count);
		} catch (Exception e) {
			log.error("OnLineUserService-findByPage:findByPage is Exception!");
			log.error(e.getMessage());
			hibernatePage = null;
		} finally {
			this.endTransaction(session, transaction, executeFlag);
		}
		return hibernatePage;
	}

}
