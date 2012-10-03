package com.taifook.adminportal.eservice.delegate;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.taifook.adminportal.dto.User;

public class EserviceSecurityDelegate {

	private Log log = LogFactory.getLog(this.getClass());

	// create MgtLoginControlFacade bean instance
//	private MgtLoginControlFacade createMgtLoginControlFacadeBean()
//			throws ServiceException {
//		MgtLoginControlFacade bean = null;
//		log.info((new SimpleDateFormat("yyy-mm-dd HH:mm:ss"))
//				.format(new Date())
//				+ " create MgtLoginControlFacadeBean starting......");
//		try {
//			MgtLoginControlFacadeHome home = (MgtLoginControlFacadeHome) EJBHomeDelegate
//					.getInstance().lookUp("ejb/MgtLoginControlFacade",
//							MgtLoginControlFacadeHome.class);
//			bean = home.create();
//			log.info((new SimpleDateFormat("yyy-mm-dd HH:mm:ss"))
//					.format(new Date())
//					+ " create MgtLoginControlFacadeBean finished......");
//		} catch (Exception ex) {
//			log.error((new SimpleDateFormat("yyy-mm-dd HH:mm:ss"))
//					.format(new Date())
//					+ " create MgtLoginControlFacadeBean throw Exception: "+ex.getMessage());
//			throw new ServiceException(
//					"can't connect to the Eservice server,it may be server shutdown or network disconnection!");// this
//		}
//		return bean;
//	}

//	private AEInfoFacade createAEInfoFacadeBean() throws EJBException,
//			Exception {
//		AEInfoFacade bean = null;
//		log.info((new SimpleDateFormat("yyy-mm-dd HH:mm:ss"))
//				.format(new Date())
//				+ " create AEInfoFacadeBean starting......");
//		AEInfoFacadeHome home = (AEInfoFacadeHome) EJBHomeDelegate
//				.getInstance().lookUp("ejb/AEInfoFacade",
//						AEInfoFacadeHome.class);
//		try {
//			bean = home.create();
//			log.info((new SimpleDateFormat("yyy-mm-dd HH:mm:ss"))
//					.format(new Date())
//					+ " create AEInfoFacadeBean finished......");
//		} catch (Exception ex) {
//			log.error((new SimpleDateFormat("yyy-mm-dd HH:mm:ss"))
//					.format(new Date())
//					+ " create AEInfoFacadeBean throw Exception: "+ex.getMessage());
//			throw new EJBException(" Creating AEInfoFacadeBean Failed", ex);// this
//		}
//		return bean;
//	}

	// user login by userid method
	public User userLogin(String userID, String pwd)
			throws Exception {
//		MgtLoginControlDTO rt;
//		String loginId = userID;
		/*
		 * login function @param: session, object constructed by aecode,
		 * password. @return MgtLoginControlDTO including( aecode, usertype,
		 * account status, access right string ) @exception
		 * EJBException,RemoteException, MgtUserNotFoundException,
		 * MgtWrongPasswordException, MgtAccountLockedException,
		 * UpdateMgtLoginControlException, MgtLoginFailException;
		 */
		User loginUser = new User();
//		try {
///*			Context context=EJBHomeDelegate.getInstance().getContext();
//			Object obj = context.lookup("ejb/MgtLoginControlFacade");
//			System.out.println(obj.getClass().toString());
//			MgtLoginControlFacadeHome ejbHome = (MgtLoginControlFacadeHome) PortableRemoteObject.narrow(obj, MgtLoginControlFacadeHome.class);
//			MgtLoginControlFacade bean = ejbHome.create();*/
//			MgtLoginControlFacade bean = createMgtLoginControlFacadeBean();
//			rt = bean.login(null, new MgtLoginControlPK(loginId), pwd);
//			String aeCode = rt.getUserID();
//			String userType = rt.getUserType();
//			String account_status = rt.getAccStat();
//			String acl = rt.getAcl();
//						
//			loginUser.setUserid(userID);
//			loginUser.setPowerStr(acl);
//
//			return loginUser;
//		} catch (ServiceException e) {
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date())
//					+ e.getMessage());
//			throw new Exception("login failed ,please check input or eService status!");
//		}catch(EJBException e){
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date())
//					+ e.getMessage());
//			throw new Exception("login failed ,please check input or eService status!");
//		}catch(MgtUserNotFoundException e){
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date())
//					+ e.getMessage());
//			throw new Exception("user id not found!");
//		}catch(MgtWrongPasswordException e){
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date())
//					+ e.getMessage());
//			throw new Exception("the password is wrong!");
//		}catch(UpdateMgtLoginControlException e){
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date())
//					+ e.getMessage());
//			throw new Exception("change password fail!");
//		}catch(MgtAccountLockedException e){
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date())
//					+ e.getMessage());
//			throw new Exception("userid account locked!");
//		}catch(MgtLoginFailException e){
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date())
//					+ e.getMessage());
//			throw new Exception("login fail! it maybe user or password are wrong.");
//		}catch(RemoteException e){
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date())
//					+ e.getMessage());
//			throw new Exception("login failed ,please check input or eService status!");
//		}
//		catch (Exception e) {
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date())
//					+ e.getMessage());
//			throw new Exception("login fail! the maybe user or password are wrong.");
//		}
		return loginUser;
	}

	// change user's password method
	public boolean changePwd(String userID, String oldPwd, String newPwd)
			throws Exception {
		/*
		 * change password sample @param: activityID, aecode, aecode,
		 * oldpassword, newPassword @return 0 or 1 @exception EJBException,
		 * RemoteException, MgtUserNotFoundException, MgtWrongPasswordException,
		 * UpdateMgtLoginControlException, MgtChangePasswordException
		 */
//		int success;
//		try {
//			MgtLoginControlFacade bean = createMgtLoginControlFacadeBean();
//			success = bean.changePassword(null, userID, userID, oldPwd, newPwd);			
//			if (success == 1) {
//				log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date())+"Change Password Successfully!");
//				return true;
//			} else {
//				log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date())+"Change Passwrod Failed!");
//				return false;
//			}
//		} catch (ServiceException e) {
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date())
//					+ e.getMessage());
//			throw new Exception("change password failed ,please check input or eService status!");
//		}catch(EJBException e){
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date())
//					+ e.getMessage());
//			throw new Exception("change password failed ,please check input or eService status!");
//		}catch(RemoteException e){
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date())
//					+ e.getMessage());
//			throw new Exception("change password failed ,please check input or eService status!");
//		}catch(MgtUserNotFoundException e){
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date())
//					+ e.getMessage());
//			throw new Exception("user id not found!");
//		}catch(MgtWrongPasswordException e){
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date())
//					+ e.getMessage());
//			throw new Exception("the old password is wrong!");
//		}catch(UpdateMgtLoginControlException e){
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date())
//					+ e.getMessage());
//			throw new Exception("change password fail!");
//		}catch(MgtChangePasswordException e){
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date())
//					+ e.getMessage());
//			throw new Exception("change password fail!");
//		}
//		catch (Exception e) {
//			log.error((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ")).format(new Date())
//					+ e.getMessage());
//			throw new Exception("change password fail! "+e.getMessage());
//		}
		return false;
	}

	// add by mao

	/*
	 * get account list by ae code @param: aeCode @return Arraylist: account
	 * list @exception: QueryAEMappingException
	 */

	public Collection queryAcByAe(String aeCode) throws Exception {

		Collection acList = new ArrayList();

//		try {
//			AEInfoFacade aeBean = createAEInfoFacadeBean();
//			acList = aeBean.findAcListByAeCode(aeCode);
//			return acList;
//		} catch (QueryAEMappingException e) {
//			log.debug(e);
//			throw new QueryAEMappingException();
//		} catch (EJBException e) {
//			log.debug(e);
//			throw new EJBException();
//		} catch (RemoteException e) {
//			log.debug(e);
//			throw new RemoteException();
//		} //
            return null;
	}

}
