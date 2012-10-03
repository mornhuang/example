package cn.itsz.newsim.service;

import cn.itsz.newsim.dao.hibernate.model.AdminProfileModel;
import cn.itsz.newsim.dao.hibernate.model.ParameterModel;
import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.UserProfileModel;
import cn.itsz.newsim.dto.request.AdminChangePwdRequest;
import cn.itsz.newsim.dto.request.CancelOrderRequest;
import cn.itsz.newsim.dto.request.ChangePwdRequest;
import cn.itsz.newsim.dto.request.EnquireRTQRequest;
import cn.itsz.newsim.dto.request.EnquiryPositionRequest;
import cn.itsz.newsim.dto.request.InputOrderRequest;
import cn.itsz.newsim.dto.request.ListOrderRequest;
import cn.itsz.newsim.dto.request.LoginRequest;
import cn.itsz.newsim.dto.request.ModfiyOrderRequest;
import cn.itsz.newsim.dto.request.OrderDetailRequest;
import cn.itsz.newsim.dto.request.OrderFeeRequest;
import cn.itsz.newsim.dto.request.ParameterRequest;
import cn.itsz.newsim.dto.request.RegRequest;
import cn.itsz.newsim.dto.request.SearchRequest;
import cn.itsz.newsim.dto.request.TProtectionRequset;
import cn.itsz.newsim.dto.request.TradeListRequest;
import cn.itsz.newsim.dto.request.UserProfileRequest;
import cn.itsz.newsim.dto.response.ParameterResponse;
import cn.itsz.newsim.dto.response.UserProfileResponse;



public interface Facade {
	public ResultMessage login(LoginRequest loginRequest);
	public ResultMessage register(RegRequest regRequest);
	public boolean isUserExist(UserProfileModel userProfileModel);
	public ResultMessage changPwd(ChangePwdRequest changePwdRequest);
	public ResultMessage cancelOrder(CancelOrderRequest cancelOrderRequest);
	public ResultMessage listOrder(ListOrderRequest listOrderRequest);
	public ResultMessage modifyOrder(ModfiyOrderRequest modfiyOrderRequest);
	public ResultMessage orderDetail(OrderDetailRequest orderDetailRequest);
	public ResultMessage enquireRTQInfo(EnquireRTQRequest enquireRTQRequest);
	public ResultMessage updateTransactionProtection(TProtectionRequset protectionRequset);
	public ResultMessage calOrderFee(OrderFeeRequest orderFeeRequest);
	public ResultMessage placeOrder(InputOrderRequest inputOrderRequest);
	public ResultMessage enquireTradeList(TradeListRequest requestModel);
	public ResultMessage enquireShortRTQInfo(EnquireRTQRequest enquireRTQRequest);
	public ResultMessage enquiryPosition(EnquiryPositionRequest enquiryPositionRequest);
	
	public UserProfileResponse enquireUserProfileList(UserProfileRequest userProfileRequest); 
	public UserProfileResponse searchUserProfileList(SearchRequest searchRequest); 
	public void addParameter(ParameterModel param);
	public void updateParameter(ParameterModel param);
	public void deleteParameter(ParameterModel param);
	public ParameterModel enquireParameter(String pname);
	public ParameterResponse enquireParameterList(ParameterRequest paramRequest);
	public boolean isParameterExist(ParameterModel param);
	public ResultMessage adminLogin(AdminProfileModel adminProfileModel);
	public boolean adminChangePwd(AdminChangePwdRequest adminChangePwdRequest);
	public UserProfileResponse enquireUserProfile(String  loginId);
	public ResultMessage enquireUserProfileAsy(String  loginId);
	public boolean updateUserProfile(UserProfileModel userProfileModel);
	public void addUserProfile(UserProfileModel userProfileModel);
	public void deleteUserProfile(String  loginId);
}
