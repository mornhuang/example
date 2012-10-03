package com.itsz.sht.common.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.itsz.sht.admin.channel.util.ChannelInfo;
import com.itsz.sht.common.Constants;
import com.taifook.adminportal.model.OnLineUserInfo;
  
/**
 * 
 * $Id: UserManagement.java,v 1.31 2008/09/05 04:32:03 kyzou Exp $
 * @Project:portal
 * @File:UserManagement.java
 * @Description:
 * @Author:
 * @Date:2007-8-1
 * @Created on 2005-3-29
 */
public class UserManagement {
	private static Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_COMMON);
	//private static HashSet sessionIDSet = new HashSet();
	private static HashMap channelMap = new HashMap();
	
	//��ȡ��ǰ���channel���ߵ��û���
    public static List getUserCount(){
        if(channelMap==null){
            return null;
        }
        List list = new ArrayList();
        Iterator it = channelMap.keySet().iterator();
		while (it.hasNext()) {
            String key = (String) it.next();
            HashMap Login_SID = (HashMap) channelMap.get(key);
            int count=Login_SID.size();
            ChannelInfo ci = new ChannelInfo();
            ci.setCode(key);
            ci.setUsercount(count);
            list.add(ci);
        }
        return list;
    }
    
    /**
     * @author kyzou
     * @Description: get User in channelMap
     * @Time:2008-6-24 12:13:01
     * @param channelType
     * @param loginId
     * @return
     */    
    public static List getOnLineUserInfo(String channelType,String loginId){
        if(channelMap==null){
            return null;
        }
        Map Login_SID = (HashMap) channelMap.get(channelType);
        List list = new ArrayList();
        if(StringUtils.isBlank(loginId)){
            Iterator it = Login_SID.keySet().iterator();
    		while (it.hasNext()) {
    			String key = (String) it.next();
    			OnLineUserInfo user = new OnLineUserInfo();
    			user.setChannelCode(channelType);
    			user.setLoginId(loginId);
    			list.add(user);
    		}
        }else{
        	if (Login_SID.containsKey(loginId)) {
    			OnLineUserInfo user = new OnLineUserInfo();
    			user.setChannelCode(channelType);
    			user.setLoginId(loginId);
    			list.add(user);
        	}
        }
		return list;
    }
    
    /**
     * @author kyzou
     * @Description: remove User from channelMap
     * @Time:2008-6-24 12:13:01
     * @param channelType
     * @param loginId
     * @return
     */   
    public static int RemoveUser(String channelType,String loginName){
		HashMap Login_SID = (HashMap) channelMap.get(channelType);
		//check Login_map is Exist
		if (Login_SID == null) {
			return -1;
		}
		//check isLogined
		if (Login_SID.containsKey(loginName)) {
			log_debug.info("========remove user========LoginName="+loginName);
			Login_SID.remove(loginName);
			channelMap.put(channelType,Login_SID);
			return 1;
		}else{
			return 0;
		}
    }

    /**
     * 
     * @Description:
     * @Time:2007-8-1 9:48:01
     * @param sessionId
     * @param channelType
     * @param loginId
     * @return
     */
	public static boolean allowUser(String sessionId,String channelType,String loginId){ 
	    //TODO throw NullPointerException
		//return sessionIDSet.contains(sessionID);
	    HashMap channel;
	    if(channelType==null||loginId==null||channelMap==null){
		    return false;
		}
		if(!channelMap.containsKey(channelType)){
			return false;
		}
		channel = (HashMap) channelMap.get(channelType);
		if(channel == null){
		    return false;
		}else{
		    if(sessionId.equals(channel.get(loginId))){
		        return true;
		    }
		}
		return false;
	}
	
    /**
     * @Author:kyzou
     * @Time:2008-8-20 下午02:34:12
     * @param sessionId
     * @param channelType
     * @param loginId
     * @return
     */
     
	public static void clearChannelMap(String channelType){ 
	    HashMap Login_SID;
	    if(channelMap==null){
		    channelMap=new HashMap();
		}
		if(!channelMap.containsKey(channelType)){
		    Login_SID=new HashMap();
			channelMap.put(channelType,Login_SID);
			return;
		}else{
		    Login_SID = (HashMap) channelMap.get(channelType);
		    if(Login_SID!=null){
		        Login_SID=new HashMap();
				channelMap.put(channelType,Login_SID);
				return;
		    }
		}
	}
	
    /**
     * @Author:kyzou
     * @Time:2008-8-20 下午02:34:12
     * @param sessionId
     * @param channelType
     * @param loginId
     * @return
     */
     
	public static boolean isExistSessionId(String sessionId,String channelType,String loginId){ 
	    if(channelType==null||loginId==null||channelMap==null){
		    return false;
		}
		if(!channelMap.containsKey(channelType)){
			return false;
		}
	    HashMap channel = (HashMap) channelMap.get(channelType);
		if(channel == null){
		    return false;
		}		
		synchronized (channel) {
			if(channel.containsKey(loginId)){
				//同样的sessionID和同样的loginid, 按照普通登入方式处理
				//用户已经用loginId在别的机器登录, 按照普通登入方式处理			
	        	//ctrl+n出来的上一个页面已用不同loginId登录,同时用户已经用loginId在别的机器登录 阻止
				String mSessionId = (String)channel.get(loginId);
				return (!sessionId.equals(mSessionId) && channel.containsValue(sessionId));
			} else {
				//ctrl+n出来的上一个页面已用不同loginId登录, 阻止
				//不是ctrl+n出来的, 按照普通登入方式处理
				return channel.containsValue(sessionId);
			}			
		}
	}
	
	public static boolean activeUser(String channelType,String loginId){ 
	    HashMap channel;
	    if(channelType==null||loginId==null||channelMap==null){
		    return false;
		}
		if(!channelMap.containsKey(channelType)){
			return false;
		}
		channel = (HashMap) channelMap.get(channelType);
		if(channel == null){
		    return false;
		}else{
		    if(channel.containsKey(loginId)){
		        return true;
		    }
		}
		return false;
	}

	/**
	 * add channel by PropertyConfig,when server startup
	 * @param channelType
	 */
	public static synchronized void setChannelMap(String channelType){
	    HashMap Login_SID;
	    if(channelMap==null){
		    channelMap=new HashMap();
		}
		if(!channelMap.containsKey(channelType)){
		    Login_SID=new HashMap();
			channelMap.put(channelType,Login_SID);
			return;
		}else{
		    Login_SID = (HashMap) channelMap.get(channelType);
		    if(Login_SID==null){
		        Login_SID=new HashMap();
				channelMap.put(channelType,Login_SID);
				return;
		    }
		}
	    
	}
	/**
	 * remove channel from the system
	 * @param channelType
	 */
	public static synchronized void removeChannelMap(String channelType){
		channelMap.remove(channelType);
	}


	public static synchronized boolean addOrUpdateUser(UserObject user){
	    String channelType = user.getChannelType();
	    String loginName = user.getLoginName();
	    String sessionID = user.getSessionID();
		HashMap Login_SID = (HashMap) channelMap.get(channelType);
		//check Login_map is Exist
		if (Login_SID == null) {
			log_debug.info("new Login-session MAP: "+channelType);
			//setChannelMap(channelType);
			//Login_SID = (HashMap) channelMap.get(channelType);
			//error channel type.
			return false;
		}
		
		//log_debug.info(">>>>>Login_SID>>>>>>>>>>>Start");
//		Iterator it = Login_SID.keySet().iterator();
//		while (it.hasNext()) {
//            String key = (String) it.next();
//            String comment = (String) Login_SID.get(key);
//            //log_debug.info(">>>>>Login_SID>>>>>>>>>>>"+key + ":" + comment);
//        }
		//check isLogined
		if (Login_SID.containsKey(loginName)) {
			//has logon
			//get old session id
			String old_sid = (String) Login_SID.get(loginName);
			
			if(!old_sid.equals(sessionID)){
				//update user management
				removeUser(channelType,loginName,old_sid);
				//addToSet(sessionID);
				Login_SID = (HashMap) channelMap.get(channelType);
				Login_SID.put(loginName, sessionID);
				channelMap.put(channelType,Login_SID);
				log_debug.info("addOr[Update]User: " + loginName
						+ " has login,and old SID=" + old_sid + " , update to "
						+ sessionID);
			}
		} else {
			log_debug.info("[add]OrUpdateUser: " + loginName + " ,sid="
					+ sessionID);

			// add new user
			Login_SID.put(loginName, sessionID);
			channelMap.put(channelType,Login_SID);
			//addToSet(sessionID);
		}

		return true;
	}
	/**
	 * remove user from the UserManagement when session timeout or user logout
	 * @param channelType
	 * @param loginName
	 * @param sessionID
	 * @return
	 */
	
	public static synchronized boolean removeUser(String channelType,String loginName,String sessionID){
		HashMap Login_SID = (HashMap) channelMap.get(channelType);
		//check Login_map is Exist
		if (Login_SID == null) {
			return false;
		}
		//check isLogined
		if (Login_SID.containsKey(loginName)) {
			log_debug.info("========remove user========LoginName="+loginName+"  sessionid="+sessionID);
			String oldId = (String) Login_SID.get(loginName);
			if(sessionID.equals(oldId)){
			    Login_SID.remove(loginName);
			}
			    channelMap.put(channelType,Login_SID);			
			//removeFromSet(sessionID);
		} 
		return true;
	}
	private  String getSIDFromChannelMap(String channelType,String loginName){
		HashMap Login_SID=(HashMap)channelMap.get(channelType);
		if(Login_SID==null){
			return null;
		}else{
			return Login_SID.get(loginName).toString();
		}
	}
    
	public static void setChannelMap(HashMap map){
        if(map==null) return;
        Iterator it=channelMap.keySet().iterator();
        Object key = null;
        while(it.hasNext()){
            key = it.next();
            if(key!=null&&map.containsKey(key))
                channelMap.put(key,map.get(key));
        }
	}
    
	public static HashMap getChannelMap(){
		return channelMap;
	}
}
