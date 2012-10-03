package com.itsz.sht.dao.impl;




import java.util.List;

import org.springframework.dao.DataAccessException;


import com.itsz.sht.dao.UserProfileDao;
import com.itsz.sht.dao.hibernate.model.UserProfile;


public class UserProfileDaoImpl  extends BaseDao<UserProfile>   implements UserProfileDao {

	public UserProfileDaoImpl() {
		
		super(UserProfile.class);
	}

	@Override
	public void addUserProfile(UserProfile userProfile)
			throws DataAccessException {
		this.save(userProfile);
		
	}

	@Override
	public void deleteUserProfile(String clientId) throws DataAccessException {
		this.remove(clientId);
	}

	@Override
	public void updateUserProfile(UserProfile userProfile)
			throws DataAccessException {
		this.update(userProfile);
		
	}

	@Override
	public UserProfile getUserProfile(String clientId)
			throws DataAccessException {
		return this.get(clientId);
	}

	@Override
	public List<UserProfile> listAll() throws DataAccessException {
		return this.list();
	}

	@Override
	public UserProfile getUserProfile(String clientId, String clientLoginId)
			throws DataAccessException {
		UserProfile up = null;
		String hql = "from UserProfile up where	1=1 ";
		boolean bl = false;
		if(clientId!=null && !clientId.equals("")){
			hql += "and up.clntId='" + clientId + "' ";
			bl = true;
		}
		if(clientLoginId!=null && !clientLoginId.equals("")){
			hql += "and up.clntLoginId='" + clientLoginId + "' ";
			bl = true;
		}
		if(!bl){
			hql += "and 1=2 ";
		}
		hql += "order by up.updDate desc";
		List<UserProfile> list = this.listByHql(hql);
		if (list != null && list.size() > 0) {
			up = list.get(0);
		}
		return up;
	}

	
	
}
