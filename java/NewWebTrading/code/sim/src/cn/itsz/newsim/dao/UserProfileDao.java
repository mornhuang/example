package cn.itsz.newsim.dao;

import java.util.List;

import cn.itsz.newsim.dto.ChangePwdModel;
import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.TProtectionModel;
import cn.itsz.newsim.dto.UserProfileModel;
import cn.itsz.newsim.dto.UserRegisterModel;
import cn.itsz.newsim.dto.response.UserProfileResponseAsy;


public interface UserProfileDao {
	public int saveUserProfile(UserProfileModel userProfileModel);
	public void rmoveUserProfile();
	public boolean updateUserProfile(UserProfileModel userProfileModel);
	public List<UserProfileModel> findUserProfile(String loginId,int pageSize,int offset);
	public boolean IsUserExist(UserProfileModel userProfileModel);
	public ResultMessage checkUserlogin(UserRegisterModel userRegisterModel);
	public boolean changePwd(ChangePwdModel changePwdModel);
	public boolean changeTProtection(TProtectionModel protectionModel);
	public int getCount();
	ResultMessage Userlogin(UserRegisterModel userRegisterModel);
	
	public List<UserProfileModel> findUserProfile(String  loginId);
	public UserProfileResponseAsy findUserProfileAsy(String  loginId);
	public void deleteUserProfile(String loginId);
}
