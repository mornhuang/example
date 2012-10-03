package cn.itsz.newsim.manage;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.itsz.newsim.dto.response.entity.LoginResponseEntity;

public class WebActiveUserManager {
	protected static final Log log = LogFactory.getLog(WebActiveUserManager.class);
	private static final Map<String, String> activeUsersLocal = new HashMap<String, String>();
	private static final Map<String, LoginResponseEntity> activeUsersEntity = new HashMap<String, LoginResponseEntity>();
	private static final WebActiveUserManager instance = new WebActiveUserManager();
	
	private WebActiveUserManager() {}
	
	public static WebActiveUserManager getInstance() {
		return instance;
	}
	
	public void put(String loginID, String sessionID, LoginResponseEntity entity) {
		activeUsersLocal.put(loginID, sessionID);
		activeUsersEntity.put(loginID, entity);
	}
	
	public void removeUser(String loginID, String sessionID) {
		if (validateUser(loginID, sessionID)) {
			log.info("Remove Session User: " + loginID);
			activeUsersLocal.remove(loginID);
			activeUsersEntity.remove(loginID);
		}
	}
	
	public Set<String> getAllActiveUsers() {
		return activeUsersLocal.keySet();
	}
	
	public LoginResponseEntity getUserEntity(String loginId) {
		return activeUsersEntity.get(loginId);
	}
	
	public boolean containsUser(String loginID) {
		return activeUsersLocal.containsKey(loginID);
	}
	
	public boolean validateUser(String loginID, String sessionID) {
		boolean result = false;
		if (containsUser(loginID)) {
			result = activeUsersLocal.get(loginID).equals(sessionID);
		}
		return result;
	}
	
	public Map<String, String> getActiveUsersLocal()
	{
		return activeUsersLocal;
	}
}
