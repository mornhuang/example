package com.taifook.adminportal.web.ipo.dao.hibernate;

import junit.framework.TestCase;

public class AELoginTestCase extends TestCase {

//    private static final String ERROR_NULL_REMOTE = "Remote interface reference is null.  It must be created by calling one of the Home interface methods first.  Make sure that the setUp() method calls one of the create() or finder Home interface wrappers, or the userProduct remote reference is assigned there in some other way.";
//    private static final int MAX_OUTPUT_LINE_LENGTH = 100;
//    private boolean logging = true;
//    private MgtLoginControlFacade accessBean = null;
//    private MgtLoginControlFacadeHome accessHome = null;
//    private AEInfoFacade aeBean = null;
//    private AEInfoFacadeHome aeHome = null;
//
//    //Construct the EJB test client
//    public AELoginTestCase(String name) {
//        super(name);
//    }
//
//    public void initialize() throws Exception {
//        long startTime = 0;
//        if (logging) {
//            log("Initializing bean access.");
//            startTime = System.currentTimeMillis();
//        }
//
//        //get naming context
//        Context context = getInitialContext();
//        //look up jndi name
//        Object ref = context.lookup("ejb/MgtLoginControlFacade");
//        Object ref1 = context.lookup("ejb/AEInfoFacade");
//        //look up jndi name and cast to Home interface
//        accessHome = (MgtLoginControlFacadeHome) PortableRemoteObject.narrow(ref,
//        		MgtLoginControlFacadeHome.class);
//        aeHome = (AEInfoFacadeHome)PortableRemoteObject.narrow(ref1, AEInfoFacadeHome.class);
//        if (logging) {
//            log("Succeeded initializing bean access through Home interface.");
//            long endTime = System.currentTimeMillis();
//            log("Execution time: " + (endTime - startTime) + " ms.");
//        }
//
//    }
//
//
//    public Context getInitialContext() throws Exception {
//        String url = "t3://192.168.89.102:8001";
//        String user = null;
//        String password = null;
//        Properties properties;
//        try {
//            properties = new Properties();
//            properties.put(Context.INITIAL_CONTEXT_FACTORY,
//                           "weblogic.jndi.WLInitialContextFactory");
//            properties.put(Context.PROVIDER_URL, url);
//            if (user != null) {
//                properties.put(Context.SECURITY_PRINCIPAL, user);
//                properties.put(Context.SECURITY_CREDENTIALS,
//                               password == null ? "" : password);
//            }
//            return new javax.naming.InitialContext(properties);
//        } catch (Exception e) {
//            log("Unable to connect to WebLogic server at " + url);
//            log("Please make sure that the server is running.");
//            throw e;
//        }
//    }
//
//    public void setUp() throws Exception {
//        super.setUp();
//        initialize();
//        create();
//        createAeBean();
//        /**@todo Call one of the create() or finder Home interface wrappers below, to create a Remote interface reference to the bean.*/
//    }
//
//    public void tearDown() throws Exception {
//        accessHome = null;
//        accessBean = null;
//        aeHome = null;
//        aeBean = null;
//        super.tearDown();
//    }
//
//    public void testAeLogin() {
//		MgtLoginControlDTO rt ;
//		String loginId = "511";
//			//login sample
//			/*
//			 * login function
//			 * @param: session, object constructed by aecode, password.
//			 * @return MgtLoginControlDTO including( aecode, usertype, account status, access right string )
//			 * @exception EJBException,RemoteException, MgtUserNotFoundException, MgtWrongPasswordException, MgtAccountLockedException, UpdateMgtLoginControlException, MgtLoginFailException;
//			 */
//			try {
//				rt = accessBean.login(null, new MgtLoginControlPK(loginId), "tf0665");
//				String aeCode = rt.getUserID();
//				String userType = rt.getUserType();
//				String account_status = rt.getAccStat();
//				String acl = rt.getAcl(); // access right string '1010101011100111100111'
//				//MgtAccessDTOs r = rt.getMgtAccessDTOs(); // including group collection and all access right collection
//				if(MgtLoginControlUtilizer.checkAccess(acl, new Long(86))) //defualt access right: change password's access no: 86
//					System.out.println("Has Access right for access no: 86");
//				else
//					System.out.println("Has not Access right for access no: 86");
//
//				if(MgtLoginControlUtilizer.checkAccess(acl, new Long(87))) //defualt access right: change password's access no: 86
//					System.out.println("Has Access right for access no: 87");
//				else
//					System.out.println("Has not Access right for access no: 87");
//
//			} catch (MgtUserNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (MgtWrongPasswordException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (MgtAccountLockedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (UpdateMgtLoginControlException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (MgtLoginFailException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (EJBException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (RemoteException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}//
//
//
//			/*
//			 * change password sample
//			 * @param: activityID, aecode, aecode, oldpassword, newPassword
//			 * @return 0 or 1
//			 * @exception  EJBException, RemoteException, MgtUserNotFoundException, MgtWrongPasswordException, UpdateMgtLoginControlException, MgtChangePasswordException
//
//			int success;
//			try {
//				success = accessBean.changePassword(null, loginId, loginId, "87654321", "12345678");
//				if(success == 1)
//					System.out.println("Change Password Successfully!");
//				else
//					System.out.println("Change Passwrod Failed!");
//			} catch (MgtUserNotFoundException e) {//
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (MgtWrongPasswordException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (UpdateMgtLoginControlException e) {// update login time into es db
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (MgtChangePasswordException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (EJBException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (RemoteException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}//
//             */
//			/*
//			 * get account list by ae code
//			 * @param: acCode
//			 * @return Arraylist: account list
//			 * @exception: QueryAEMappingException
//			 */
//			try {
//				Collection acList = aeBean.findAcListByAeCode(loginId);
//				Iterator itr = acList.iterator();
//                System.out.println("here=");
//				while(itr.hasNext()){
//					String account = itr.next().toString();
//
//					System.out.println("account: "+account);
//				}
//			} catch (QueryAEMappingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (EJBException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (RemoteException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} //
//
//
//	}
//
//    //----------------------------------------------------------------------------
//    // Methods that use Home interface methods to generate a Remote interface reference
//    //----------------------------------------------------------------------------
//
//    public MgtLoginControlFacade create() throws Exception {
//        long startTime = 0;
//        if (logging) {
//            log("Calling create()");
//            startTime = System.currentTimeMillis();
//        }
//
//        accessBean = accessHome.create();
//        if (logging) {
//            log("Succeeded: create()");
//            long endTime = System.currentTimeMillis();
//            log("Execution time: " + (endTime - startTime) + " ms.");
//        }
//        if (logging) {
//            log("Return value from create(): " + accessBean + ".");
//        }
//        return accessBean;
//    }
//    public AEInfoFacade createAeBean() throws Exception {
//        long startTime = 0;
//        if (logging) {
//            log("Calling create()");
//            startTime = System.currentTimeMillis();
//        }
//
//        aeBean = aeHome.create();
//        if (logging) {
//            log("Succeeded: create()");
//            long endTime = System.currentTimeMillis();
//            log("Execution time: " + (endTime - startTime) + " ms.");
//        }
//        if (logging) {
//            log("Return value from create(): " + accessBean + ".");
//        }
//        return aeBean;
//    }
//
//
//    //----------------------------------------------------------------------------
//    // Utility Methods
//    //----------------------------------------------------------------------------
//
//    private void log(String message) {
//
//        if (message == null) {
//            System.out.println("-- null");
//        }
//        if (message.length() > MAX_OUTPUT_LINE_LENGTH) {
//            System.out.println("-- " +
//                               message.substring(0, MAX_OUTPUT_LINE_LENGTH) +
//                               " ...");
//        } else {
//            System.out.println("-- " + message);
//        }
//    }

}
