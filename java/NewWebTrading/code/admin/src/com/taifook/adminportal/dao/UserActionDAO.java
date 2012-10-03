package com.taifook.adminportal.dao;

import java.util.List;

import com.taifook.adminportal.common.CommonDAO;

/**
 * <p> * Title: admin_portal        * </p>
 * <p> * Description:                     * </p>
 * <p> * Copyright: Copyright (c) 2006 * </p>
 * <p> * Company: TaiFook itsz         * </p>
 * @author hsli
 * @version 1.0
 */
public interface UserActionDAO extends CommonDAO{
	public List statisticsByTime(String time);
	public boolean deleteByKey(String key) ;
	public boolean deleteByKey(String[] keys) ;
	public boolean deleteAll();
	public List findAll(Object[] Paras);
}

