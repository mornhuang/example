package com.taifook.adminportal.common.right;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RightsManager {

	Log log = LogFactory.getLog(this.getClass());

	Map rightsMap;

	public RightsManager() {
		rightsMap = new HashMap();
	}

	public Map getRightsMap() {
		return this.rightsMap;
	}

	public RightsManager(String userRoles) {
		rightsMap = new HashMap();
		this.setRights(userRoles);
	}

	public void addRight(String action, boolean hasRight) {
		this.rightsMap.put(action, Boolean.valueOf(hasRight));
	}

	public void removeRight(String action) {
		this.rightsMap.remove(action);
	}

	public boolean hasRight(String action) {
		Boolean right = (Boolean) this.rightsMap.get(action);
		if (right == null) {
			return true;
		} else {
			return right.booleanValue();
		}
	}

	// reader from a XML file
	/*
	 * public void setRights(String userRoles) { try { List rightsList = (new
	 * RightsParser()).parser();
	 * 
	 * ContainsSplitTag split = new ContainsSplitTag();
	 * split.setCompare(userRoles);
	 * split.setDelimiter(Constants.RIGHT_DELIMITER);
	 * 
	 * Iterator it = rightsList.iterator(); while (it.hasNext()) { Right right =
	 * (Right) it.next(); split.setTarget(right.getRoles()); boolean hasRight =
	 * split.isContains(); if (right.getAction() != null) { StringTokenizer st =
	 * new StringTokenizer(right.getAction(), ";"); while (st.hasMoreElements()) {
	 * this.addRight(st.nextToken(), hasRight); } } System.out.println(right); }
	 *  } catch (Exception e) { rightsMap.clear(); e.printStackTrace(); } }
	 */

	// reader from a properties file
	public void setRights(String userRoles) {
//		if (userRoles == null) {
//			return;
//		}
//		try {
//			ReaderPropertiesUtil reader = new ReaderPropertiesUtil(
//					"rightRoles.properties");
//			Properties rightPros = reader.reader();
///*			ContainsSplitTag split = new ContainsSplitTag();
//			split.setCompare(userRoles);
//			split.setDelimiter(Constants.RIGHT_DELIMITER);*/
//
//			Set keySet = rightPros.keySet();
//			Iterator it = keySet.iterator();
//			int index = 0;
//			log.info((new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"))
//					.format(new Date())
//					+ "================== user 's rights==================");
//			while (it.hasNext()) {
//				String actionName = ((String) it.next()).trim();
//				String roles = rightPros.getProperty(actionName).trim();
//				boolean hasRight = false;
//				try {
//					hasRight = MgtLoginControlUtilizer.checkAccess(userRoles,
//							new Long(roles));
//				} catch (Exception e) {
//					hasRight = false;
//				}
//				// split.setTarget(roles);
//				// boolean hasRight = split.isContains();
//				// hasRight=true;
//				this.addRight(actionName, hasRight);
//				index++;
//				log.info(index + "  actionName: " + actionName + "		roles: "
//						+ roles + "	hasRight: " + hasRight);
//			}
//
//		} catch (Exception e) {
//			rightsMap.clear();
////			e.printStackTrace();
//		}
	}

	public static void main(String[] args) {
		(new RightsManager()).setRights("701");
	}

}
