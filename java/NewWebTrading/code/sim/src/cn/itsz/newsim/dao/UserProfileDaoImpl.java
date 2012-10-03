package cn.itsz.newsim.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.itsz.newsim.common.Constants;
import cn.itsz.newsim.common.MD5;
import cn.itsz.newsim.dao.hibernate.model.ParameterModel;
import cn.itsz.newsim.dto.ChangePwdModel;
import cn.itsz.newsim.dto.ResultMessage;
import cn.itsz.newsim.dto.TProtectionModel;
import cn.itsz.newsim.dto.UserProfileModel;
import cn.itsz.newsim.dto.UserRegisterModel;
import cn.itsz.newsim.dto.response.LoginResponse;
import cn.itsz.newsim.dto.response.UserProfileResponseAsy;
import cn.itsz.newsim.dto.response.entity.LoginResponseEntity;
import cn.itsz.newsim.exception.ITSZException;

@Component(value = "userProfileDao")
public class UserProfileDaoImpl implements UserProfileDao {

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private ParameterDao parameterDao;
	
	private String sql = "select * from userprofile where loginId=?";
	private String sql2 = "insert into userprofile(loginId,passWord,email,addNo,telephone,username,client,clientNo,clientMoney,transactionProtection,allowTradeStatusFlag,tradeAccount,lastupdate)values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private String sql3 = "select * from userprofile where LOGINID=? AND PASSWORD=?";
	private String sql4 = "update userprofile  set password=? where loginId=?";
	private String sql5 = "update userprofile  set transactionProtection=? where loginId=?";
	private final String  sqlquerypage="SELECT * FROM userprofile order by lastupdate desc OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
	private final String  sqlsearch = "SELECT * FROM userprofile where loginId = ?";
    private final String counts="select count(*) from userprofile ";
    private String sql_update="update userprofile  set username=?,EMAIL=?,TELEPHONE=?,CLIENT=?,ADDNO=?,CLIENTNO=?,PASSWORD=? where loginId=?";
    private String sql_delete="delete from userprofile where loginId=?";

    public int getCount()throws ITSZException{
		Statement ps = null;
		ResultSet rl = null;
		int count=0;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.createStatement();
			rl = ps.executeQuery(counts);
			if (rl.next()) {
			    count=rl.getInt(1);
			} 
		} catch (SQLException e) {
			ITSZException exception = new ITSZException();
			exception.setErrorCode("DB-ERROR");
			throw exception;
		} finally {
			try {
				if (rl != null) {
					rl.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
    }
    
    
	@Override
	public List<UserProfileModel> findUserProfile(String loginId) {
		List<UserProfileModel> list = new ArrayList<UserProfileModel>();
		PreparedStatement ps = null;
		ResultSet rl = null;
		UserProfileModel userProfile=null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);
			rl = ps.executeQuery();
			while (rl.next()) {
				userProfile= new UserProfileModel();
				userProfile.setLoginId(rl.getString("loginId"));
				userProfile.setUsername(rl.getString("username"));
				userProfile.setPassWord(rl.getString("passWord"));
				userProfile.setEmail(rl.getString("EMAIL"));
				userProfile.setTelephone(rl.getString("TELEPHONE"));
				userProfile.setClient(rl.getString("client"));
				userProfile.setClientMoney((new BigDecimal(rl.getString("CLIENTMONEY"))).doubleValue());
				userProfile.setAddNo(rl.getString("ADDNO"));
				userProfile.setClientNo(rl.getString("CLIENTNO"));
				userProfile.setTransactionProtection(rl.getString("transactionProtection"));
				userProfile.setAllowTradeStatusFlag(rl.getString("allowTradeStatusFlag"));
				list.add(userProfile);
			} 
		} catch (SQLException e) {
			ITSZException exception = new ITSZException();
			exception.setErrorCode("DB-ERROR");
			throw exception;
		} finally {
			try {
				if (rl != null) {
					rl.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}


	public List<UserProfileModel> findUserProfile(
			String loginId,int pagesize,int pageNo) throws ITSZException {
		List<UserProfileModel> list = new ArrayList<UserProfileModel>();
		PreparedStatement ps = null;
		ResultSet rl = null;
		Connection conn = null;
		String sql = null;
		
		if (StringUtils.isBlank(loginId)) {
			sql = sqlquerypage;
		} else {
			sql = sqlsearch;
		}
			
		int start=0;
		if(pageNo==0){
			pageNo=1;
			start=0;
		}else{
			start=pagesize*(pageNo-1);
		}
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			if (StringUtils.isBlank(loginId)) {
				ps.setInt(1, start);
				ps.setInt(2, pagesize);
			} else {
				ps.setString(1, loginId);
			}
			rl=ps.executeQuery();
			while (rl.next()) {
				UserProfileModel userProfile= new UserProfileModel();
				userProfile.setLoginId(rl.getString("loginId"));
				userProfile.setUsername(rl.getString("username"));
				userProfile.setPassWord(rl.getString("passWord"));
				userProfile.setEmail(rl.getString("EMAIL"));
				userProfile.setTelephone(rl.getString("TELEPHONE"));
				userProfile.setClient(rl.getString("client"));
				userProfile.setClientMoney((new BigDecimal(rl.getString("CLIENTMONEY"))).doubleValue());
				userProfile.setAddNo(rl.getString("ADDNO"));
				userProfile.setClientNo(rl.getString("CLIENTNO"));
				userProfile.setTransactionProtection(rl.getString("transactionProtection"));
				userProfile.setAllowTradeStatusFlag(rl.getString("allowTradeStatusFlag"));
				list.add(userProfile);
			} 
		} catch (SQLException e) {
			ITSZException exception = new ITSZException();
			exception.setErrorCode("DB-ERROR");
			throw exception;
		} finally {
			try {
				if (rl != null) {
					rl.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	public boolean IsUserExist(UserProfileModel userProfileModel)
			throws ITSZException {
		PreparedStatement ps = null;
		ResultSet rl = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			String str = userProfileModel.getLoginId();
			ps.setString(1, str);
			rl = ps.executeQuery();
			if (rl.next()) {
				return false;
			} else
				return true;
		} catch (SQLException e) {
			ITSZException exception = new ITSZException();
			exception.setErrorCode("DB-ERROR");
			throw exception;
		} finally {
			try {
				if (rl != null) {
					rl.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int saveUserProfile(UserProfileModel userProfileModel)
			throws ITSZException {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql2);
			ps.setString(1, userProfileModel.getLoginId());
			ps.setString(2, userProfileModel.getPassWord());
			ps.setString(3, userProfileModel.getEmail());
			ps.setString(4, userProfileModel.getAddNo());
			ps.setString(5, userProfileModel.getTelephone());
			ps.setString(6, userProfileModel.getUsername());
			ps.setString(7, userProfileModel.getClient());
			ps.setString(8, userProfileModel.getClientNo());
			ps.setDouble(9, userProfileModel.getClientMoney());
			ps.setString(10, userProfileModel.getTransactionProtection());
			ps.setString(11, userProfileModel.getAllowTradeStatusFlag());
			ps.setString(12, "02-" + userProfileModel.getLoginId() + "-30");
			ps.setString(13, userProfileModel.getLastUpdate());
			int rl = ps.executeUpdate();
			conn.commit();
			return rl;
		} catch (SQLException e) {
			e.printStackTrace();
			ITSZException exception = new ITSZException();
			exception.setErrorCode("DB-ERROR");
			throw exception;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public boolean updateUserProfile(UserProfileModel userProfileModel) {
		PreparedStatement ps = null;
		Connection conn = null;
		int i = -1;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql_update);
			ps.setString(1, userProfileModel.getUsername());
			ps.setString(2, userProfileModel.getEmail());
			ps.setString(3, userProfileModel.getTelephone());
			ps.setString(4, userProfileModel.getClient());
			ps.setString(5, userProfileModel.getAddNo());
			ps.setString(6, userProfileModel.getClientNo());
			ps.setString(7, userProfileModel.getPassWord());
			ps.setString(8, userProfileModel.getLoginId());
			i = ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			ITSZException exception = new ITSZException();
			exception.setErrorCode("DB-ERROR");
			throw exception;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return i > 0;
	}

	@Override
	public void deleteUserProfile(String loginId) {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql_delete);
			ps.setString(1, loginId);
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			ITSZException exception = new ITSZException();
			exception.setErrorCode("DB-ERROR");
			throw exception;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public ResultMessage checkUserlogin(UserRegisterModel userRegisterModel)
			throws ITSZException {
		ResultMessage resultMessage = new ResultMessage();
		resultMessage.setReturnCode("");
		PreparedStatement ps = null;
		ResultSet rl = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql3);
			ps.setString(1, userRegisterModel.getLoginId());
			ps.setString(2, MD5.Md5(userRegisterModel.getPassword()));
			rl = ps.executeQuery();
			if (rl.next()) {
				resultMessage.setReturnCode(Constants.Status.NORMAL);
			} else {
				resultMessage.setReturnCode(Constants.Status.FAILED);
			}
			;
		} catch (SQLException e) {
			ITSZException exception = new ITSZException();
			exception.setErrorCode("DB-ERROR");
			throw exception;
		} finally {
			try {
				if (rl != null) {
					rl.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultMessage;

	}

	@Override
	public ResultMessage Userlogin(UserRegisterModel userRegisterModel)
			throws ITSZException {
		LoginResponse resultMessage = new LoginResponse();
		resultMessage.setReturnCode("");
		PreparedStatement ps = null;
		ResultSet rl = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql3);
			ps.setString(1, userRegisterModel.getLoginId());
			ps.setString(2, MD5.Md5(userRegisterModel.getPassword()));
			rl = ps.executeQuery();
			if (rl.next()) {
				LoginResponseEntity loginResponseEntity = new LoginResponseEntity();
				loginResponseEntity.setLoginId(rl.getString("loginId"));
				loginResponseEntity.setUserName(rl.getString("username"));
				loginResponseEntity.setPassword(rl.getString("passWord"));
				loginResponseEntity.setTransactionProtection(rl
						.getString("transactionProtection"));
				loginResponseEntity.setAllowTradeStatusFlag(rl
						.getString("allowTradeStatusFlag"));
				loginResponseEntity.setTradeAccount(rl
						.getString("tradeAccount"));
				
				loginResponseEntity.setTotalPurchasingPower(new BigDecimal(getMoney(Constants.Parameter.CLIENT_MONEY)));
				resultMessage.setReturnCode(Constants.Status.NORMAL);
				resultMessage.setLoginResponseEntity(loginResponseEntity);
			} else
				resultMessage.setReturnCode(Constants.Status.WARN);
		} catch (SQLException e) {
			ITSZException exception = new ITSZException();
			exception.setErrorCode("DB-ERROR");
			throw exception;
		} finally {
			try {
				if (rl != null) {
					rl.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return resultMessage;
	}

	@Override
	public boolean changePwd(ChangePwdModel changePwdModel)
			throws ITSZException {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql4);
			ps.setString(1, MD5.Md5(changePwdModel.getPassWord()));
			ps.setString(2, changePwdModel.getLoginId());
			int rl = ps.executeUpdate();
			conn.commit();
			if (rl > 0) {
				return true;
			} else
				return false;
		} catch (SQLException e) {
			ITSZException exception = new ITSZException();
			e.printStackTrace();
			exception.setErrorCode("DB-ERROR");
			throw exception;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public boolean changeTProtection(TProtectionModel protectionModel) {
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql5);
			ps.setString(1, protectionModel.getTransactionProtection());
			ps.setString(2, protectionModel.getLoginId());
			int rl = ps.executeUpdate();
			conn.commit();
			if (rl > 0) {
				return true;
			} else
				return false;
		} catch (SQLException e) {
			ITSZException exception = new ITSZException();
			e.printStackTrace();
			exception.setErrorCode("DB-ERROR");
			throw exception;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void rmoveUserProfile() {

	}

	private double getMoney(String name) {
		ParameterModel param = parameterDao.findParameter(name);
		
		ITSZException exception = new ITSZException();
		if (null != param) {
			String v = param.getPvalue();
			if (null != v) {
				try {
					double value = Double.parseDouble(v);
					return value;
				} catch (NumberFormatException e) {
					exception.setErrorMessage("Parameter: \""+ name + "\" value is not number");
					throw exception;
				}
			} else {
				exception.setErrorMessage("Parameter: \""+ name + "\" value is null");
				throw exception;
			}
		} else {
			exception.setErrorMessage("Parameter: \""+ name + "\" not found");
			throw exception;
		}
	}

	@Override
	public UserProfileResponseAsy findUserProfileAsy(String loginId) {
		UserProfileResponseAsy res = new UserProfileResponseAsy();
		PreparedStatement ps = null;
		ResultSet rl = null;
		UserProfileModel userProfile=null;
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, loginId);
			rl = ps.executeQuery();
			while (rl.next()) {
				userProfile= new UserProfileModel();
				userProfile.setLoginId(rl.getString("loginId"));
				userProfile.setUsername(rl.getString("username"));
				userProfile.setPassWord(rl.getString("passWord"));
				userProfile.setEmail(rl.getString("EMAIL"));
				userProfile.setTelephone(rl.getString("TELEPHONE"));
				userProfile.setClient(rl.getString("client"));
				userProfile.setClientMoney((new BigDecimal(rl.getString("CLIENTMONEY"))).doubleValue());
				userProfile.setAddNo(rl.getString("ADDNO"));
				userProfile.setClientNo(rl.getString("CLIENTNO"));
				userProfile.setTransactionProtection(rl.getString("transactionProtection"));
				userProfile.setAllowTradeStatusFlag(rl.getString("allowTradeStatusFlag"));
			} 
		} catch (SQLException e) {
			ITSZException exception = new ITSZException();
			exception.setErrorCode("DB-ERROR");
			throw exception;
		} finally {
			try {
				if (rl != null) {
					rl.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (null != userProfile) {
			res.setUserProfile(userProfile);
		}
		return res;
	}
}
