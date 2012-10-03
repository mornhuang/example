package com.itsz.sht.dao.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.RtqApplicationDao;
import com.itsz.sht.dao.hibernate.model.RtqApplication;

public class RtqApplicationDaoImpl extends BaseDao<RtqApplication> implements RtqApplicationDao {

	public RtqApplicationDaoImpl() {
		super(RtqApplication.class);
	}

	@Override
	public void addRtqApplication(RtqApplication rtqApplication)
			throws DataAccessException {
		this.save(rtqApplication);
	}

	@Override
	public void deleteRtqApplication(String productId)
			throws DataAccessException {
		 this.remove(productId);
	}

	@Override
	public RtqApplication getRTQApplication(String productId)
			throws DataAccessException {
		return this.get(productId);
	}

	@Override
	public void updateRtqApplication(RtqApplication rtqApplication)
			throws DataAccessException {

		this.update(rtqApplication);
	}

	@Override
	public List<RtqApplication> listRTQApplication() throws DataAccessException {
		return this.list();
	}

	@Override
	public void addBatchRtqApp(List<RtqApplication> list)
			throws DataAccessException {
	  this.saveOrUpdateBatch(list);
		
	}
}
