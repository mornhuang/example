package com.itsz.sht.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.hibernate.model.NotificationMedia;

public interface NotificationMediaDao {

	public void addNotificationMedia(NotificationMedia otificationMedia)throws DataAccessException;
	public void updateNotificationMedia(NotificationMedia otificationMedia)throws DataAccessException;
	public void deleteNotificationMedia(String otificationMediaId)throws DataAccessException;
	public NotificationMedia getNotificationMedia(String otificationMediaId)throws DataAccessException;
	public NotificationMedia getNotificationMediaByClntId(String clientId)throws DataAccessException;
	public List<NotificationMedia> getNotificationMediaByType(String notfType)throws DataAccessException;
}
