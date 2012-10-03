package com.taifook.adminportal.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.sht.common.Consts;
import com.taifook.adminportal.common.Constants;
import com.taifook.adminportal.common.util.page.Page;
import com.taifook.adminportal.dao.SecurityDAO;
import com.taifook.adminportal.dto.User;

public class SecurityService implements SecurityDAO {

	private Log log = LogFactory.getLog(SecurityService.class);

	public void save(Object object) {
	}

	public void update(Object object) {
	}

	public void delete(Object object) {
	}

	public Object findById(Serializable id) {
		return null;
	}

	public List findAll() {
		return null;
	}

	public boolean deleteAll() {
		return true;
	}

	public Page findByPage(Object[] Paras, int pageNumber, int pageSize) {
		return null;
	}

	public User userLogin(String userId, String pwd) throws Exception {
//		try {
			User loginUser = new User();
//			MgtLoginControlDTO rt = ESAccessDelegate.getInstance().eIPOLogin(null, new MgtLoginControlPK(userId), pwd);
//			String aeCode = rt.getUserID();
//			String userType = rt.getUserType();
//			String account_status = rt.getAccStat();
//			String acl = rt.getAcl();
//			loginUser.setUserid(userId);
//			loginUser.setPowerStr(acl);
//			return loginUser;
//		} catch (EJBException e) {
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date()) + e.getMessage());
//			throw new Exception("login failed ,please check input or eService status!");
//		} catch (RemoteException e) {
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date()) + e.getMessage());
//			throw new Exception("login failed ,please check input or eService status!");
//		} catch (MgtUserNotFoundException e) {
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date()) + e.getMessage());
//			throw new Exception("user id not found!");
//		} catch (MgtWrongPasswordException e) {
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date()) + e.getMessage());
//			throw new Exception("the password is wrong!");
//		} catch (MgtAccountLockedException e) {
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date()) + e.getMessage());
//			throw new Exception("userid account locked!");
//		} catch (UpdateMgtLoginControlException e) {
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date()) + e.getMessage());
//			throw new Exception("the password is wrong!");
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date()) + e.getMessage());
//			throw new Exception("login fail! the maybe user or password are wrong.");
//		}
			
			return loginUser;
	}

	public boolean userLogout(HttpSession userSession) {
		if (userSession != null) {
			//userSession.removeAttribute(Constants.SESSION_USER);
			userSession.removeAttribute(Consts.AdminPortal.userContext.USERCONTEXT);
			userSession.invalidate();
			userSession = null;
			return true;
		} else {
			return false;
		}
	}

	public boolean changePwd(String userID, String oldPwd, String newPwd) throws Exception {
//		int success = -1;
//		try {
//			success = ESAccessDelegate.getInstance().eIPOChangePassword(null, userID, userID, oldPwd, newPwd);
//			if (success == 1) {
//				log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date())
//						+ "Change Password Successfully!");
//				return true;
//			} else {
//				log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date()) + "Change Passwrod Failed!");
//				return false;
//			}
//		} catch (EJBException e) {
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date()) + e.getMessage());
//			throw new Exception("change password failed ,please check input or eService status!");
//		} catch (RemoteException e) {
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date()) + e.getMessage());
//			throw new Exception("change password failed ,please check input or eService status!");
//		} catch (MgtUserNotFoundException e) {
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date()) + e.getMessage());
//			throw new Exception("user id not found!");
//		} catch (MgtWrongPasswordException e) {
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date()) + e.getMessage());
//			throw new Exception("the old password is wrong!");
//		} catch (UpdateMgtLoginControlException e) {
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date()) + e.getMessage());
//			throw new Exception("change password fail!");
//		} catch (Exception e) {
//			e.printStackTrace();
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date()) + e.getMessage());
//			throw new Exception("change password fail! " + e.getMessage());
//		}
		
		return false;
	}

	public Collection queryAcByAe(String aeCode) throws Exception {
//		try {
//			Collection acList = ESAccessDelegate.getInstance().findAcListByAeCode(aeCode);
//			return acList;
//		} catch (QueryAEMappingException e) {
//			log.error(e);
//			throw new QueryAEMappingException();
//		} catch (EJBException e) {
//			log.error(e);
//			throw new EJBException();
//		} catch (RemoteException e) {
//			log.error(e);
//			throw new RemoteException();
//		} catch (Exception e) {
//			log.error(e);
//			throw e;
//		}
		return null;
	}

}
