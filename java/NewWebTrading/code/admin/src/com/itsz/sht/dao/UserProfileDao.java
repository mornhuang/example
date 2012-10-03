package com.itsz.sht.dao;


import java.util.List;

import org.springframework.dao.DataAccessException;

import com.itsz.sht.dao.hibernate.model.UserProfile;



public interface UserProfileDao {

	public void updateUserProfile(UserProfile userProfile)throws DataAccessException;
	
    public void deleteUserProfile(String clientId)throws DataAccessException;
	
	public void addUserProfile(UserProfile userProfile) throws DataAccessException;
	
	public UserProfile getUserProfile(String clientId) throws DataAccessException;
	
	public List<UserProfile> listAll() throws DataAccessException; 
	
	public UserProfile getUserProfile(String clientId, String clientLoginId) throws DataAccessException;
}
