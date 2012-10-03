package cn.itsz.newsim.dao;

import cn.itsz.newsim.dao.hibernate.model.AdminProfileModel;
import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.request.AdminChangePwdRequest;

public interface AdminProfileDao {
	
	public ResultMessage adminLogin(AdminProfileModel adminProfileModel);
	
	public boolean adminChangePwd(AdminChangePwdRequest adminChangePwdRequest);
	
}
