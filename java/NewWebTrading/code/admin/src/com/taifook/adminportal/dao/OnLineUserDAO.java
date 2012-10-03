package com.taifook.adminportal.dao;

import java.io.Serializable;
import java.util.List;

import com.taifook.adminportal.common.CommonDAO;
import com.taifook.adminportal.model.OnLineUserInfo;

/**
 * <p> * Title: admin_portal        * </p>
 * <p> * Description:                     * </p>
 * <p> * Copyright: Copyright (c) 2006 * </p>
 * <p> * Company: TaiFook itsz         * </p>
 * @author hsli
 * @version 1.0
 */
public interface OnLineUserDAO extends CommonDAO{
	public void saveOrUpdate(Object object);
	public List findByChannelCode();
	public void deletebyId(Serializable id);
	public void deletebyUser(OnLineUserInfo user);

}

