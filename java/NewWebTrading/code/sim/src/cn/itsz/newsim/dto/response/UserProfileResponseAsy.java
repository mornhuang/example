package cn.itsz.newsim.dto.response;

import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.UserProfileModel;

public class UserProfileResponseAsy extends ResultMessage {

	private UserProfileModel userProfile;
	
	public UserProfileModel getUserProfile() {
		return userProfile;
	}
	public void setUserProfile(UserProfileModel userProfile) {
		this.userProfile = userProfile;
	}
}
