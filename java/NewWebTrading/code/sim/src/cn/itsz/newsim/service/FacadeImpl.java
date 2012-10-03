package cn.itsz.newsim.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.dao.AdminProfileDao;
import cn.itsz.newsim.dao.ParameterDao;
import cn.itsz.newsim.dao.UserProfileDao;
import cn.itsz.newsim.dao.hibernate.model.AdminProfileModel;
import cn.itsz.newsim.dao.hibernate.model.ParameterModel;
import cn.itsz.newsim.dto.ChangePwdModel;
import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.TProtectionModel;
import cn.itsz.newsim.dto.UserProfileModel;
import cn.itsz.newsim.dto.UserRegisterModel;
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
import cn.itsz.newsim.dto.response.AdminLoginResponse;
import cn.itsz.newsim.dto.response.EnquireRTQResponse;
import cn.itsz.newsim.dto.response.EnquireShortRTQResponse;
import cn.itsz.newsim.dto.response.LoginResponse;
import cn.itsz.newsim.dto.response.ParameterResponse;
import cn.itsz.newsim.dto.response.RegResponse;
import cn.itsz.newsim.dto.response.TransactionProtectionrResponse;
import cn.itsz.newsim.dto.response.UserProfileResponse;
import cn.itsz.newsim.dto.response.entity.EnquireShortRTQResponseEntity;
import cn.itsz.newsim.exception.ITSZException;
import cn.itsz.newsim.manage.OrderManager;
import cn.itsz.newsim.manage.QsManager;

@Service
public class FacadeImpl implements Facade {
	@Resource(name = "userProfileDao")
	private UserProfileDao userProfileDao;
	@Resource(name = "parameterDao")
	private ParameterDao parameterDao;
	@Resource(name = "adminProfileDao")
	private AdminProfileDao adminProfileDao;
	@Autowired
	private QsManager qsManager;
	@Autowired
	private OrderManager orderManager;

	public UserProfileDao getUserProfileDao() {
		return userProfileDao;
	}

	public ResultMessage login(LoginRequest loginRequest) throws ITSZException {
		UserRegisterModel userRegisterModel = new UserRegisterModel();
		ResultMessage resultMessage=new ResultMessage();
		userRegisterModel.setLoginId(loginRequest.getLoginId());
		userRegisterModel.setPassword(loginRequest.getPassword());
		LoginResponse loginResponse = new LoginResponse();
		resultMessage = userProfileDao.Userlogin(userRegisterModel);
		if (resultMessage.getReturnCode()==Constants.Status.NORMAL) {
			loginResponse.setReturnCode(Constants.Status.NORMAL);
			loginResponse.setLoginResponseEntity(((LoginResponse)resultMessage).getLoginResponseEntity());
			orderManager.initAccount(loginRequest.getLoginId());
		} else {
			loginResponse.setReturnCode(Constants.Status.FAILED);
		}
		return loginResponse;
	}

	@Override
	public ResultMessage enquireShortRTQInfo(EnquireRTQRequest enquireRTQRequest) {
		EnquireShortRTQResponse result = new EnquireShortRTQResponse();
		List<EnquireShortRTQResponseEntity> list = qsManager.callShortRTQInfo(enquireRTQRequest);
		result.setEnquireShortRTQResponse(list);
		return result;
	}

	public ResultMessage register(RegRequest regRequest) {
		UserProfileModel userProfileModel = new UserProfileModel();
		userProfileModel.setAddNo(regRequest.getAddNo());
		userProfileModel.setClient(regRequest.getClient());
		userProfileModel.setClientNo(regRequest.getClientNo());
		userProfileModel.setEmail(regRequest.getEmail());
		userProfileModel.setLoginId(regRequest.getLoginId());
		userProfileModel.setPassWord(regRequest.getPassWord());
		userProfileModel.setTelephone(regRequest.getTelephone());
		userProfileModel.setUsername(regRequest.getUsername());
		userProfileModel.setClientMoney(regRequest.getClientMoney());
		userProfileModel.setTransactionProtection(regRequest.getTransactionProtection());
		userProfileModel.setAllowTradeStatusFlag(regRequest.getAllowTradeStatusFlag());
		userProfileModel.setLastUpdate(regRequest.getLastUpdate());
		RegResponse response = new RegResponse();
		int i = userProfileDao.saveUserProfile(userProfileModel);
		if (i != 0) {
			response.setReturnCode(Constants.Status.NORMAL);
		}
		return response;
	}

	public boolean isUserExist(UserProfileModel userProfileModel) throws ITSZException {
		if (userProfileDao.IsUserExist(userProfileModel)) {
			return false;
		} else
			return true;
	}

	@Override
	public ResultMessage changPwd(ChangePwdRequest changePwdRequest) throws ITSZException {
		ChangePwdModel changePwdModel = new ChangePwdModel();
		ResultMessage resultMessage = new ResultMessage();
		UserRegisterModel userRegisterModel = new UserRegisterModel();
		userRegisterModel.setLoginId(changePwdRequest.getLoginId());
		userRegisterModel.setPassword(changePwdRequest.getOldPassWrod());
		changePwdModel.setLoginId(changePwdRequest.getLoginId());
		changePwdModel.setPassWord(changePwdRequest.getPassWord());
		resultMessage = userProfileDao.checkUserlogin(userRegisterModel);
		if (resultMessage.getReturnCode()==Constants.Status.NORMAL) {
			if (userProfileDao.changePwd(changePwdModel)) {
				resultMessage.setReturnCode(Constants.Status.NORMAL);
			} else
				resultMessage.setReturnCode(Constants.Status.WARN);
		} else {
			resultMessage.setReturnCode(Constants.Status.FAILED);
		}
		return resultMessage;
	}

	@Override
	public ResultMessage enquireRTQInfo(EnquireRTQRequest enquireRTQRequest) {
		EnquireRTQResponse enquireRTQResponse = new EnquireRTQResponse();
		enquireRTQResponse.setEnquireRTQResponse(qsManager.callBmpRTQInfo(enquireRTQRequest));
		return enquireRTQResponse;
	}

	@Override
	public ResultMessage updateTransactionProtection(TProtectionRequset protectionRequset) {
		UserRegisterModel userRegisterModel = new UserRegisterModel();
		ResultMessage resultMessage = new ResultMessage();
		TransactionProtectionrResponse transactionProtectionrResponse=new TransactionProtectionrResponse();
		userRegisterModel.setLoginId(protectionRequset.getLoginId());
		userRegisterModel.setPassword(protectionRequset.getPassword());
		TProtectionModel protectionModel = new TProtectionModel();
		protectionModel.setLoginId(protectionRequset.getLoginId());
		protectionModel.setTransactionProtection(protectionRequset.getTransactionProtection());
	
		if(protectionRequset.getPassword()!=null){
			resultMessage = userProfileDao.checkUserlogin(userRegisterModel);
			if (resultMessage.getReturnCode().equals(Constants.Status.NORMAL)) {
				if (userProfileDao.changeTProtection(protectionModel)) {
					transactionProtectionrResponse.setReturnCode(Constants.Status.NORMAL);
					transactionProtectionrResponse.setTransactionProtectionStatus(protectionModel.getTransactionProtection());
				} else
					transactionProtectionrResponse.setReturnCode(Constants.Status.WARN);
			} else {
				transactionProtectionrResponse.setReturnCode(Constants.Status.FAILED);
				transactionProtectionrResponse.setTransactionProtectionStatus(protectionModel.getTransactionProtection());
			}
		}else{
			if (userProfileDao.changeTProtection(protectionModel)) {
				transactionProtectionrResponse.setTransactionProtectionStatus(protectionModel.getTransactionProtection());
				transactionProtectionrResponse.setReturnCode(Constants.Status.NORMAL);
			} else
				transactionProtectionrResponse.setReturnCode(Constants.Status.WARN);
		}

		return transactionProtectionrResponse;
	}

	@Override
	public ResultMessage calOrderFee(OrderFeeRequest orderFeeRequest) {
		return orderManager.calOrderFee(orderFeeRequest);
	}

	@Override
	public ResultMessage placeOrder(InputOrderRequest inputOrderRequest) {
		return orderManager.addOrder(inputOrderRequest);
	}

	@Override
	public ResultMessage cancelOrder(CancelOrderRequest cancelOrderRequest) {
		return orderManager.cancelOrder(cancelOrderRequest);
	}

	@Override
	public ResultMessage listOrder(ListOrderRequest listOrderRequest) {
		return orderManager.listOrder(listOrderRequest);
	}

	@Override
	public ResultMessage modifyOrder(ModfiyOrderRequest modfiyOrderRequest) {
		return orderManager.modfiyOrder(modfiyOrderRequest);
	}

	@Override
	public ResultMessage orderDetail(OrderDetailRequest orderDetailRequest) {
		return orderManager.enquiryOrderDetail(orderDetailRequest);
	}

	@Override
	public ResultMessage enquireTradeList(TradeListRequest requestModel) {
		return orderManager.listTrade(requestModel);
	}

	@Override
	public ResultMessage enquiryPosition(EnquiryPositionRequest enquiryPositionRequest) {
		return orderManager.listPosition(enquiryPositionRequest);
	}

	@Override
	public UserProfileResponse  enquireUserProfileList(UserProfileRequest userProfileRequest) {
		UserProfileResponse res = new UserProfileResponse();
		res.setPageSize( userProfileRequest.getPageSize());
		List<UserProfileModel> list=userProfileDao.findUserProfile(userProfileRequest.getLoginId(), userProfileRequest.getPageSize(), 
				userProfileRequest.getPageNo());
		int totalsize=userProfileDao.getCount();
		if (null!=list&&list.size()>0) {
			pageInfo(res, list, totalsize, userProfileRequest.getPageNo(), userProfileRequest.getPageSize());
		} else {
			res.setUserProfiles(new ArrayList<UserProfileModel>());
		}
		return res;
	}

	@Override
	public void addParameter(ParameterModel param) {
		this.parameterDao.saveParameter(param);
	}

	@Override
	public ParameterModel enquireParameter(String pname) {
		return this.parameterDao.findParameter(pname);
	}

	@Override
	public ParameterResponse enquireParameterList(ParameterRequest paramRequest) {

		ParameterResponse res = new ParameterResponse();
		res.setPageSize( paramRequest.getPageSize());
		List<ParameterModel> list=this.parameterDao.findParameterList(paramRequest);
		int totalsize=parameterDao.getCount(paramRequest);
		if (null!=list&&list.size()>0) {
			
			int pageNo = paramRequest.getPageNo();
			int pageSize = paramRequest.getPageSize();
			
			res.setParameterModels(list);
			int totalPage = 0;
			if (totalsize % pageSize > 0) {
				totalPage = ((int) Math.floor(totalsize / pageSize)) + 1;
			} else {
				totalPage = (int)Math.floor(totalsize / pageSize);
			}
			int currentPage = pageNo;
			int prevPage = currentPage <= 1 ? 1 : currentPage - 1;
			int nextPage = totalPage > currentPage ? currentPage + 1 : currentPage;
			int pageAmount = totalPage;
			res.setTotalPage(totalPage);
			res.setCurrentPage(currentPage);
			res.setPrevPage(prevPage);
			res.setNextPage(nextPage);
			res.setPageAmount(pageAmount);
		} else {
			res.setParameterModels(new ArrayList<ParameterModel>());
		}
		return res;
	
		 
	
	}

	@Override
	public boolean isParameterExist(ParameterModel param) {
		return this.parameterDao.isParameterExist(param);
	}

	@Override
	public ResultMessage adminLogin(AdminProfileModel adminProfileModel) {
		ResultMessage resultMessage=new ResultMessage();
		AdminLoginResponse adminLoginResponse = new AdminLoginResponse();
		resultMessage = adminProfileDao.adminLogin(adminProfileModel);
		if (resultMessage.getReturnCode()==Constants.Status.NORMAL) {
			adminLoginResponse.setReturnCode(Constants.Status.NORMAL);
			adminLoginResponse.setAdminLoginResponseEntity(((AdminLoginResponse)resultMessage).getAdminLoginResponseEntity());
		} else {
			adminLoginResponse.setReturnCode(Constants.Status.FAILED);
		}
		return adminLoginResponse;
	}
	@Override
	public boolean adminChangePwd(AdminChangePwdRequest adminChangePwdRequest) {
		return this.adminProfileDao.adminChangePwd(adminChangePwdRequest);
		
	}

	@Override
	public void updateParameter(ParameterModel param) {
		
		this.parameterDao.updateParameter(param);
		
	}
	@Override
	public void deleteParameter(ParameterModel param) {
		
		this.parameterDao.deleteParameter(param);
		
	}

	@Override
	public void addUserProfile(UserProfileModel userProfileModel) {
		this.userProfileDao.saveUserProfile(userProfileModel);
		
	}

	@Override
	public UserProfileResponse enquireUserProfile(String loginId) {
		UserProfileResponse res = new UserProfileResponse();
		res.setUserProfiles(this.userProfileDao.findUserProfile(loginId));
		return res;
	}

	@Override
	public boolean updateUserProfile(UserProfileModel userProfileModel) {
		return this.userProfileDao.updateUserProfile(userProfileModel);
		
	}

	@Override
	public void deleteUserProfile(String loginId) {
		this.userProfileDao.deleteUserProfile(loginId);
		
	}

	public UserProfileResponse searchUserProfileList(SearchRequest searchRequest) {
		UserProfileResponse res = new UserProfileResponse();
//		res.setPageSize(searchRequest.getPageSize());
		List<UserProfileModel> list=userProfileDao.findUserProfile(searchRequest.getKeyword(),
				searchRequest.getPageSize(), searchRequest.getPageNo());
//		int totalsize=userProfileDao.getCount();
		if (null!=list&&list.size()>0) {
			//pageInfo(res, list, totalsize, searchRequest.getPageNo(), searchRequest.getPageSize());
			res.setUserProfiles(list);
		} else {
			res.setUserProfiles(null);
		}
		
		return res;
	}
	
	private static void pageInfo(UserProfileResponse res, List<UserProfileModel> list, int totalsize, int pageNo, int pageSize) {
		res.setUserProfiles(list);
		int totalPage = totalsize % pageSize ==0 ? totalsize / pageSize : totalsize / pageSize +1;
		int currentPage = pageNo;
		int prevPage = currentPage <= 1 ? 1 : currentPage - 1;
		int nextPage = totalPage > currentPage ? currentPage + 1 : currentPage;
		int pageAmount = totalPage;
		res.setTotalPage(totalPage);
		res.setCurrentPage(currentPage);
		res.setPrevPage(prevPage);
		res.setNextPage(nextPage);
		res.setPageAmount(pageAmount);
	}

	@Override
	public ResultMessage enquireUserProfileAsy(String loginId) {
		return this.userProfileDao.findUserProfileAsy(loginId);
	}
	
}
