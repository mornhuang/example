package com.taifook.adminportal.common;

import java.io.Serializable;
import java.util.List;

import com.taifook.adminportal.common.util.page.Page;
/**
 * <p> * Title: admin_portal           * </p>
 * <p> * Description:                  * </p>
 * <p> * Copyright: Copyright (c) 2006 * </p>
 * <p> * Company: TaiFook itsz         * </p>
 * @author hsli
 * @version 1.0
 */
public interface CommonDAO {
	public void save(Object object) throws Exception;
	public void update(Object object) throws Exception;
	public void delete(Object object) throws Exception;
	public Object findById(Serializable id) throws Exception;
	public List findAll() throws Exception;	
	public boolean deleteAll();
	public Page findByPage(Object[] Paras, int pageNumber, int pageSize) throws Exception;

}
