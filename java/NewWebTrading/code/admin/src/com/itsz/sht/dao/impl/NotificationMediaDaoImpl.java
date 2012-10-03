package com.itsz.sht.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.NotificationMediaDao;
import com.itsz.sht.dao.hibernate.model.NotificationMedia;

public class NotificationMediaDaoImpl extends BaseDao<NotificationMedia> implements
		NotificationMediaDao {

	public NotificationMediaDaoImpl() {
		super(NotificationMedia.class);
	}

	@Override
	public void addNotificationMedia(NotificationMedia otificationMedia)
			throws DataAccessException {
		this.save(otificationMedia);
		
	}

	@Override
	public void deleteNotificationMedia(String otificationMediaId)
			throws DataAccessException {
		this.remove(otificationMediaId);
		
	}

	@Override
	public NotificationMedia getNotificationMedia(String otificationMediaId)
			throws DataAccessException {
		return  this.get(otificationMediaId);
		
	}

	@Override
	public void updateNotificationMedia(NotificationMedia otificationMedia)
			throws DataAccessException {
		this.update(otificationMedia);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public NotificationMedia getNotificationMediaByClntId(String clientId)
			throws DataAccessException {
		NotificationMedia notificationMedia = null;
		String hql = "from NotificationMedia nm where nm.clntId='" + clientId + "'";
		List<NotificationMedia> list = this.listByHql(hql);
		if(list!=null && list.size()>0){
			notificationMedia = list.get(0);
		}
		return notificationMedia;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<NotificationMedia> getNotificationMediaByType(String notfType)
			throws DataAccessException {
		String hql = "from NotificationMedia nm where nm.notfType='" + ((notfType==null||notfType.equals(""))?"OTCON":notfType) + "' order by nm.clntId,nm.updDate";
		return this.listByHql(hql);
	}

}
