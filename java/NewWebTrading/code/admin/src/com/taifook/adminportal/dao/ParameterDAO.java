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
public interface ParameterDAO extends CommonDAO{
	public List loadChannels(String channels_status_pre);

}

