package com.itsz.Contingency;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itsz.common.Constants;
import com.itsz.common.util.DateUtil;
import com.itsz.parameter.util.ParameterManager;
import com.itsz.util.RTQSessionUtil;
import com.itsz.util.database.DBConnPoolUtil;
import com.itsz.web.rtq.obj.RTQAccount;
/**
 * 
 * @author clyao(修改)
 *
 */
public class ClientMain {
	public static String secStatus = "I";

	public static String futStatus = "I";

	//new for eService
	public static String eSerStatus = "I";

	public static String refreshMin = "30";

	public static String refreshMax = "60";

	public static String authPass = "";

	public static String authUser = "";

	public static String SEC_COMPANY = "SEC";

	public static String FUT_COMPANY = "FUT";
	private static Log log_debug = LogFactory.getLog(Constants.LOG_DEBUG_COMMON);

	public ClientMain() {
	}

	public String chkLoginId(String loginId) {
		if (loginId == null)
			return null;
		if (loginId.trim().equals(""))
			return null;
		String id = "";
		try {
			id = "" + Integer.parseInt(loginId);
			for (int i = id.length(); i < 7; i++) {
				id = "0" + id;
			}
		} catch (NumberFormatException ex) {
			id = loginId;
		}
		
	
		return id;
	}


	@SuppressWarnings("unchecked")
	public Map getQuoteInfo1(String custCode, String lang) {
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		StringBuffer quoteUrl = new StringBuffer();
		String RTQChnlId = "";
		String RTQChnlName = "";
		String RTQProdName = "";
		String RTQChnlUsrID = "";
		String RTQChnlUsrPwd = "";
		String RTQChnlURL = "";
		String RTQPrimalURL = "";
		String RTQStatus = "";
		String Status = "";
		String ProdStatus = "";
		Map map=new HashMap();
		try {

			conn=DBConnPoolUtil.getConnection();
			String sql = "SELECT aa.CLNT_ID,bb.PROD_ID,bb.RTQ_PRDR,bb.RTQ_STATUS,cc.STATUS,dd.PROD_STATUS,bb.RTQ_URL "
				+ "FROM NW_USR_PROF aa,NW_RTQ_APP bb,NW_USR_PROD cc,NW_PROD dd "
				+ "where aa.CLNT_ID = cc.CLNT_ID and cc.PROD_ID=bb.PROD_ID and cc.PROD_ID=dd.PROD_ID "
				+ "and bb.PROD_ID in ('SSTR_AAST','SSTR_AAST_CN','SSTR_IQS','SSTR_IQS_CN') "
				+ "and cc.EXPR_DATE = to_date('" + DateUtil.getStringforDate(DateUtil.getThisMonthLastDateofMonth(new java.util.Date())) + "','yyyy-mm-dd hh24:mi:ss') and aa.CLNT_ID = '"
				+ custCode + "'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				RTQChnlName = rs.getString("RTQ_PRDR");
				RTQChnlId = rs.getString("PROD_ID");
				RTQStatus = rs.getString("RTQ_STATUS");
				Status = rs.getString("STATUS");
				ProdStatus = rs.getString("PROD_STATUS");
				if(RTQStatus.equals("AVAIL") && Status.equals("AVAIL") && ProdStatus.equals("AVAIL")){
					Map mapRTQASGN = this.getQuoteAccount(custCode);
					if(mapRTQASGN.get("userid") != null){
						RTQChnlUsrID = mapRTQASGN.get("userid").toString();
						RTQChnlUsrPwd = mapRTQASGN.get("password").toString();
					}else{
						List<RTQAccount> list = this.getRemainingAccount(rs.getString("PROD_ID"));
						if(list.size() > 0){
							insertRTQAccount(list.get(0), custCode);
							RTQChnlUsrID = list.get(0).getRtqLoginId();
							RTQChnlUsrPwd = list.get(0).getRtqLoginPwd();
						}
					}
					RTQChnlURL = rs.getString("RTQ_URL");
					RTQPrimalURL=rs.getString("RTQ_URL");
				}
			}

			if (!RTQChnlName.equalsIgnoreCase("")) {
				String language;
				String fnt_lan;
				if (lang.equalsIgnoreCase("cn")) {
					language = "GB";
					fnt_lan = "2";
				} else if (lang.equalsIgnoreCase("tw")) {
					language = "C";
					fnt_lan = "1";
				} else {
					language = "E";
					fnt_lan = "0";
				}

				if (RTQChnlURL == null) {
					RTQChnlURL = "";
				}
				
				if(RTQChnlId.equals("SSTR_AAST") || RTQChnlId.equals("SSTR_AAST_CN")){
					RTQProdName = "aastock";
				}else if(RTQChnlId.equals("SSTR_IQS") || RTQChnlId.equals("SSTR_IQS_CN")){
					RTQProdName = "ETNet";
				}

				map.put("company","Htisec");
				map.put("username",RTQChnlUsrID);
				map.put("password",RTQChnlUsrPwd);
				map.put("UD2",fnt_lan);
				map.put("rtqurl",RTQPrimalURL);
				map.put("uid",RTQChnlUsrID);
				map.put("pwd",RTQChnlUsrPwd);
				map.put("rtqstatus", RTQStatus);
				map.put("status", Status);
				map.put("prodstatus", ProdStatus);
				map.put("lang",language);
				map.put("stype","1");
				map.put("order_lang",language);
				map.put("id",RTQChnlUsrID);
				map.put("language",language);
				map.put("tradePlatform","c");
				map.put("group","tf");
				map.put("connectionType","http80");
				map.put("userPasswd",RTQChnlUsrPwd);
				map.put("userLogin",RTQChnlUsrID);
				map.put("userLang","big5");
				map.put("quoteurl",RTQChnlURL);
				map.put("prodId",RTQChnlId);
				map.put("provider",RTQChnlId);
				map.put("rtqProdName",RTQProdName);
			}
		} catch (Exception ex) {
			log_debug.info(ex);
		} finally {
			DBConnPoolUtil.closeConnection(conn, ps, rs);
		}

		log_debug.info("customer code: "+custCode);
		return map;
	}
	
	/***
	 * 查看用户购买的RTQ是否已经分配
	 * @param custCode
	 * @return Map {uid,pwd}
	 */
	public Map getQuoteAccount(String custCode){
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Map map = new HashMap();
		try {
			conn=DBConnPoolUtil.getConnection();
			String sql = "select * from NW_RTQ_ACC_ASGN aa where aa.CLNT_ID = '"+custCode+"'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				map.put("userid", rs.getString("RTQ_LOGN_ID"));
				map.put("password", rs.getString("RTQ_LOGN_PWD"));
			}
		} catch (Exception e) {
			log_debug.info(e);
		}finally{
			DBConnPoolUtil.closeConnection(conn, ps, rs);
		}
		return map;
	}
	
	/***
	 * 查询出还没有分配完RTQ类型的所有账号
	 * @param prodId
	 * @return
	 */
	public List<RTQAccount> getRemainingAccount(String prodId){
		List<RTQAccount> list = new ArrayList<RTQAccount>();
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			conn=DBConnPoolUtil.getConnection();
			String sql = "SELECT * FROM NW_RTQ_ACC WHERE PROD_ID = '"+prodId+"'"
					+"and RTQ_LOGN_ID not in (select RTQ_LOGN_ID FROM NW_RTQ_ACC_ASGN WHERE PROD_ID='"+prodId+"')";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				RTQAccount account = new RTQAccount();
				account.setProdId(rs.getString("PROD_ID"));
				account.setRtqLoginId(rs.getString("RTQ_LOGN_ID"));
				account.setRtqLoginPwd(rs.getString("RTQ_LOGN_PWD"));
				account.setInsertDate(rs.getDate("INIT_DATE"));
				account.setInitBy(rs.getString("INIT_BY"));
				account.setUpdateDate(rs.getDate("UPD_DATE"));
				account.setUpdateBy(rs.getString("UPD_BY"));
				list.add(account);
			}
		} catch (Exception e) {
			log_debug.info(e);
		}finally{
			DBConnPoolUtil.closeConnection(conn, ps);
		}
		return list;
	}
	
	/***
	 * 给用户分配账号
	 * @param account
	 * @param custCode
	 */
	public void insertRTQAccount(RTQAccount account, String custCode){
		Connection conn = null;
		PreparedStatement ps=null;
		try {
			conn=DBConnPoolUtil.getConnection();
			String sql = "insert into NW_RTQ_ACC_ASGN values(?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, account.getProdId());
			ps.setString(2, account.getRtqLoginId());
			ps.setString(3, account.getRtqLoginPwd());
			ps.setString(4, custCode);
			ps.setDate(5, new Date(0));
			ps.setString(6, account.getInitBy());
			ps.setDate(7, new Date(0));
			ps.setString(8, account.getUpdateBy());
			ps.executeUpdate();
		} catch (Exception e) {
			log_debug.info(e);
		}finally{
			DBConnPoolUtil.closeConnection(conn, ps);
		}
	}

	/**
	 * 
	 * @param loginId
	 * @param lang
	 * @return aeInfo[]{AE_CODE,AE_NAME,AE_PHONE}
	 */
	public String[] getAEInfo(String custCode, String company) {
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String[] aeInfo = new String[] { "", "", "" };
		try {
			// conn = TomcatConnection.getConnection();
			conn = DBConnPoolUtil.getConnection();
			String sql = "SELECT AE_CODE,AE_NAME,AE_PHONE FROM USER_BROKER_INFO where CUSTCODE='"
				+ custCode + "'" + " AND COMPANY_CODE ='" + company + "'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				aeInfo[0] = rs.getString("AE_CODE");
				aeInfo[1] = rs.getString("AE_NAME");
				aeInfo[2] = rs.getString("AE_PHONE");
			}
		} catch (Exception ex) {
			log_debug.info(ex);
		} finally {
			DBConnPoolUtil.closeConnection(conn, ps, rs);
		}
		return aeInfo;
	}

	/**
	 * 
	 * @param userID
	 * @param market
	 * @param lng
	 * @return
	 */
	public FuturesLoginInfo getFuturesLoginInfo(String userID, String market,
			String lang) {
		//Connection conn = TomcatConnection.getConnection();
		Connection conn = DBConnPoolUtil.getConnection();
		FuturesLoginInfo info = new FuturesLoginInfo();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		if (conn != null) {
			try {
				pstmt = conn
				.prepareStatement("SELECT * FROM FUT_RTQ_USER_INFO WHERE CUSTCODE=? AND MARKET=?");
				pstmt.setString(1, userID);
				pstmt.setString(2, market);
				rs = pstmt.executeQuery();
				if (rs != null && rs.next()) {
					info.setLoginID(rs.getString("PROVIDER_USERID"));
					info.setMarket(rs.getString("MARKET"));
					info.setPassword(rs.getString("PROVIDER_PASSWORD"));
					info.setProduct(rs.getString("TFFO_RTQ_PRODUCT"));
					info.setRtqDisplay(rs.getString("RTQ_CODE_DISPLAY"));
					info.setUserID(rs.getString("CUSTCODE"));
					StringBuffer url = new StringBuffer();
					if ("TFF".equalsIgnoreCase(info.getRtqDisplay())) {
						url.append(ParameterManager.getValue(Constants.TFF_CONN_URL)+"?version=0&source=OAPI&LANG=");
						if ("C".equalsIgnoreCase(lang)) {
							url.append("BIG5");
						} else if ("GB".equalsIgnoreCase(lang)) {
							url.append("GB");
						} else {
							url.append("ENG");
						}
						url
						.append("&session="
								+ new RTQSessionUtil().encode(info
										.getUserID()));
					} else if ("AAF".equalsIgnoreCase(info.getRtqDisplay())) {
						url
						.append("https://secure.aastocks.com/pkages/broker/login_broker/get.asp?broker=taifook&lang=");
						if ("C".equalsIgnoreCase(lang)) {
							url.append("chi");
						} else if ("GB".equalsIgnoreCase(lang)) {
							url.append("chi");
						} else {
							url.append("eng");
						}
						url.append("&uname=" + info.getLoginID() + "&password="
								+ info.getPassword());
					} else if ("QPIF".equalsIgnoreCase(info.getRtqDisplay())) {
						url
						.append("http://www.quotepower.com/web/taifook/qpmain.asp");
						url.append("?uid=" + info.getLoginID() + "&pwd="
								+ info.getPassword());
					} else if ("TFFND".equalsIgnoreCase(info.getRtqDisplay())) {
						url.append(ParameterManager.getValue(Constants.TFFND_CONN_URL)+"?version=1&source=OAPI&LANG=");//                    	
						if ("C".equalsIgnoreCase(lang)) {
							url.append("BIG5");
						} else if ("GB".equalsIgnoreCase(lang)) {
							url.append("GB");
						} else {
							url.append("ENG");
						}
						url
						.append("&session="
								+ new RTQSessionUtil().encode(info
										.getUserID()));

					} else if ("TFFO".equalsIgnoreCase(info.getRtqDisplay())) {

						url.append("http://rtqgwy13.taifook.com/rtq/rtqoverseasChannel.jsp?source=OAPI&lang=");

						if ("C".equalsIgnoreCase(lang)) {
							url.append("C");
						} else if ("GB".equalsIgnoreCase(lang)) {
							url.append("G");
						} else {
							url.append("E");
						}
						url
						.append("&PRODUCT=1111&session="
								+ new RTQSessionUtil().encode(info
										.getUserID()));
					}
					info.setUrl(url.toString());
				}

			} catch (Exception ex) {
				log_debug.info(ex);
			} finally {
				DBConnPoolUtil.closeConnection(conn, pstmt, rs);

			}

		} else {
			log_debug.info("cann't get connection");
		}
		if (info.getUrl() == null || "".equals(info.getUrl().trim())) {
			info.setUrl("./quote_error.jsp");
		}
		log_debug.info("customer code: "+userID);
		return info;

	}

	public boolean futUserValidate(String loginID) {
		if (true) return true;
		boolean isValid = false;
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//conn = TomcatConnection.getConnection();
			conn = DBConnPoolUtil.getConnection();
			String sql = "SELECT * FROM FUT_RTQ_USER_INFO WHERE CHNNLGRP_LOGINID = '"
				+ loginID + "'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				isValid = true;
			}

		} catch (Exception ex) {
			log_debug.info(ex);
		} finally {
			DBConnPoolUtil.closeConnection(conn, ps, rs);

		}

		return isValid;
	}

	public String secUserValidate(String loginID) {
		//System.out.println("LOGIN:"+loginID);
		String id = "";
		try {
			id = "" + Integer.parseInt(loginID);
			for (int i = id.length(); i < 7; i++) {
				id = "0" + id;
			}
		} catch (NumberFormatException ex) {
			id = loginID;
		}
		//System.out.println("ID:" + id);
		//boolean isValid = false;        
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs =null;
		String custCode = null;
		try {
			// conn = TomcatConnection.getConnection();
			conn = DBConnPoolUtil.getConnection();
			String sql = "SELECT * FROM NW_USR_PROF WHERE CLNT_LOGIN_ID= '"
				+ id + "'";
			//System.out.println("SQL:" + sql);
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				custCode = rs.getString("CLNT_ID");
				log_debug.info(custCode);
			}
		} catch (Exception ex) {
			log_debug.info(ex);
		} finally {
			DBConnPoolUtil.closeConnection(conn, ps, rs);
		}
		//System.out.println("CUSTCODE:" + custCode);
		return custCode;
	}

//	new block for eservices,validate CHNNLGRP_LOGINID of fut_rtq_user_info
	public String eSerFutUserValidate(String loginID) {
		String custCode = null;
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs =null;
		String id = loginID;
//		if(loginID.length()<7){
//			try {
//				id = "" + Integer.parseInt(loginID);
//				for (int i = id.length(); i < 7; i++) {
//					id = "0" + id;
//				}
//			} catch (NumberFormatException ex) {
//				id = loginID;
//			}
//		}
//		else{
//			id = loginID;
//		}
		try {
			//   conn = TomcatConnection.getConnection();
			conn = DBConnPoolUtil.getConnection();
			String sql = "SELECT * FROM FUT_RTQ_USER_INFO WHERE CHNNLGRP_LOGINID = '"
				+ id + "'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				custCode = rs.getString("CUSTCODE");
			}

		} catch (Exception ex) {
			log_debug.info(ex);
		} finally {
			DBConnPoolUtil.closeConnection(conn, ps, rs);
		}

		return custCode;
	}

	private static void appendRTQURL(StringBuffer buffer, String url) {
		if (url == null)
			url = "";

		buffer.append(url);
		if (url.indexOf('?') >= 0)
			buffer.append("&broker=taifook");
		else
			buffer.append("?broker=taifook");
	}

	public static void main(String[] args) {
		ClientMain client = new ClientMain();
		//client.secUserValidate("920672");
		//System.out.println("sdfsdf");
		System.out.println(Calendar.getInstance().getTime());

	}


	/**
	 * 
	 * @param loginId
	 * @param lang
	 * @return quoteInfo[]{RTQChnlUsrID,RTQChnlUsrPwd,quoteUrl,RTQChnlName}
	 */
	public String[] getQuoteInfo(String custCode, String lang) {
		Connection conn = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String[] quoteInfo = new String[] { "", "", "", "" ,""};
		StringBuffer quoteUrl = new StringBuffer();
		String RTQChnlName = "";
		String RTQChnlUsrID = "";
		String RTQChnlUsrPwd = "";
		String RTQChnlURL = "";
		String RTQPrimalURL="";
		try {


			conn=DBConnPoolUtil.getConnection();
			String sql = "select CUSTCODE,CHNNLGRP_LOGINID,QUOTE_PROVIDER, "
				+ " QUOTE_LOGINID,QUOTE_PW,QUOTE_URL from SEC_RTQ_USER_INFO where CUSTCODE ='"
				+ custCode + "'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				RTQChnlName = rs.getString("QUOTE_PROVIDER");
				RTQChnlUsrID = rs.getString("QUOTE_LOGINID");
				RTQChnlUsrPwd = rs.getString("QUOTE_PW");
				RTQChnlURL = rs.getString("QUOTE_URL");
				RTQPrimalURL=rs.getString("QUOTE_URL");
			}

			if (!RTQChnlName.equalsIgnoreCase("")) {
				String language;
				String fnt_lan;
				if (lang.equalsIgnoreCase("cn")) {
					language = "GB";
					fnt_lan = "2";
				} else if (lang.equalsIgnoreCase("tw")) {
					language = "C";
					fnt_lan = "1";
				} else {
					language = "E";
					fnt_lan = "0";
				}

				if (RTQChnlURL == null) {
					RTQChnlURL = "";
				}

				appendRTQURL(quoteUrl, RTQChnlURL);
				if ("Finet".equalsIgnoreCase(RTQChnlName)) {
					quoteUrl = quoteUrl.append("&company=TaiFook");
					quoteUrl = quoteUrl.append("&username=" + RTQChnlUsrID);
					quoteUrl = quoteUrl.append("&password=" + RTQChnlUsrPwd);
					quoteUrl = quoteUrl.append("&UD2=" + fnt_lan);
					log_debug.info("Finet"+quoteUrl);
				}else if(RTQChnlName.equals("aastock")){
					quoteUrl=new StringBuffer(RTQChnlURL);
					log_debug.info("aastock"+quoteUrl);

				} else {
					quoteUrl = quoteUrl.append("&uid=" + RTQChnlUsrID);
					quoteUrl = quoteUrl.append("&pwd=" + RTQChnlUsrPwd);
					quoteUrl = quoteUrl.append("&lang=" + language);
					quoteUrl = quoteUrl.append("&stype=1");
					quoteUrl = quoteUrl.append("&order_lang=" + language);
					quoteUrl = quoteUrl.append("&id=" + RTQChnlUsrID);

					quoteUrl = quoteUrl.append("&language=" + language);
					// Querystring variables needed for PowerTicker (20020809)
					quoteUrl = quoteUrl.append("&tradePlatform=c");
					quoteUrl = quoteUrl.append("&group=tf");
					quoteUrl = quoteUrl.append("&connectionType=http80");
					//quoteUrl = quoteUrl.append("&version=1.2.4.1");
					quoteUrl = quoteUrl.append("&userPasswd=" + RTQChnlUsrPwd);
					quoteUrl = quoteUrl.append("&userLogin=" + RTQChnlUsrID);
					quoteUrl = quoteUrl.append("&userLang=big5");
					log_debug.info("other"+quoteUrl);
				}


			}
		} catch (Exception ex) {
			log_debug.info(ex);
		} finally {
			DBConnPoolUtil.closeConnection(conn, ps, rs);
		}
		quoteInfo[0] = RTQChnlUsrID==null ? "" : RTQChnlUsrID ;
		quoteInfo[1] = RTQChnlUsrPwd==null ? "" : RTQChnlUsrPwd;
		quoteInfo[2] = quoteUrl==null ? "" : quoteUrl.toString();
		quoteInfo[3] = RTQChnlName==null ? "" : RTQChnlName;
		quoteInfo[4] = RTQPrimalURL==null ? "" : RTQPrimalURL;
		log_debug.info("customer code : "+custCode);
		return quoteInfo;
	}
}