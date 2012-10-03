/*
 * Created on 2005-5-23
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.itsz.Contingency;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import com.itsz.util.Counters;
import com.itsz.util.ProcessStatus;
import com.itsz.util.StringUtil;
import com.itsz.util.database.DBConnPoolUtil;

/**
 * @author Danny
 *
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class DataImptAsync {
    String QUOTE_INSERT_SQL = "insert into SEC_RTQ_USER_INFO (CUSTCODE,CHNNLGRP_LOGINID,QUOTE_PROVIDER,QUOTE_LOGINID,QUOTE_PW,QUOTE_URL) values (?,?,?,?,?,?)";

    String BROKER_INSERT_SQL = "insert into USER_BROKER_INFO (CUSTCODE,AE_CODE,AE_NAME,AE_PHONE,COMPANY_CODE) values (?,?,?,?,?)";

    String FUTQUOTE_INSERT_SQL = "insert into FUT_RTQ_USER_INFO (CUSTCODE,CHNNLGRP_LOGINID,RTQ_CODE_DISPLAY,PROVIDER_USERID,PROVIDER_PASSWORD,MARKET,TFFO_RTQ_PRODUCT,UPDATE_DATE) values(?,?,?,?,?,?,?,SYSDATE())";

    String QUOTE_DELETE_SQL = "delete from SEC_RTQ_USER_INFO";
    String SEC_BROKER_DELETE_SQL = "delete from USER_BROKER_INFO WHERE COMPANY_CODE='SEC'";
    String FUT_BROKER_DELETE_SQL = "delete from USER_BROKER_INFO WHERE COMPANY_CODE='FUT'";
    String FUTQUOTE_DELETE_SQL = "delete from FUT_RTQ_USER_INFO";

    Connection conn = null;

    PreparedStatement pstmt = null;
    PreparedStatement pstmt1 = null;
    PreparedStatement pstmt2 = null;
    PreparedStatement pstmt3 = null;
    PreparedStatement pstmt4 = null;

    /**
     *
     */
    public DataImptAsync() {

    }

    public void processUpload(final Collection upFileRows,
            final Dictionary upFileFields, final String uploadType) {

        final ProcessStatus status = ProcessStatus.getStatus("ImportDataSync");
       // conn = TomcatConnection.getConnection();
        conn=DBConnPoolUtil.getConnection();

        Runnable runnable = new Runnable() {
            public void run() {
                /***************************************************************
                 *
                 **************************************************************/

                status.start();
                status.setTotalRecord(upFileRows.size());
                Counters counters = status.getCounters();
                Map errMap = status.getErrors4Append();
                String deleteSQL;
                String updateSQL;
                try {
                    if (uploadType.equals("quote")) {
                        deleteSQL = QUOTE_DELETE_SQL;

                    } else if (uploadType.equals("broker")) {
                        deleteSQL = SEC_BROKER_DELETE_SQL;

                    } else if (uploadType.equals("futquote")) {
                        deleteSQL = FUTQUOTE_DELETE_SQL;

                    } else if(uploadType.equals("futbroker")){
                        deleteSQL = FUT_BROKER_DELETE_SQL;
                    } else {
                        errMap.put("Error", "Upload type error!");
                        status.end();
                        return;
                    }
                    pstmt = conn.prepareStatement(deleteSQL);
                    pstmt.executeUpdate();

                    String line=null;
                    StringTokenizer token=null;
                    String[] arr = null;
                    //System.out.println("uploadType:" + uploadType);
                    pstmt1 = conn.prepareStatement(QUOTE_INSERT_SQL);
                    pstmt2 = conn.prepareStatement(BROKER_INSERT_SQL);
                    pstmt3 = conn.prepareStatement(FUTQUOTE_INSERT_SQL);
                    pstmt4 = conn.prepareStatement(BROKER_INSERT_SQL);
                    
                    for (Iterator itr = upFileRows.iterator(); itr.hasNext();) {

                        line = StringUtil.replaceString((String) itr.next(),
                                "|^|", "|^| ");
                        line = StringUtil.replaceString(line, "\r\n", "");
                        line = StringUtil.replaceString(line, "\n", "");
                        //System.out.println("'" + line + "'");
                        if (line.trim().equals(""))
                            continue;

                        token = new StringTokenizer(line, "|^|", false);
                        //System.out.println("SIZE:" + token.countTokens());
                        //String[] arr = new String[token.countTokens()];
                        arr = StringUtil.partitionString(line, "|^|");
                        //System.out.println("SIZE:" + arr.length);

                        
                        for (int i = 0; i < arr.length; i++) {
                            if (token.hasMoreTokens()) {
                                arr[i] = token.nextToken();
                            }
                        }

                        if (uploadType.equals("quote")) {
                            if (arr[2].trim().equalsIgnoreCase("aastock")) {
                                arr[5] = "https://secure.aastocks.com/aateletext/chi/login_broker/auto_post.asp";
                            }
                            if (arr[2].trim().equalsIgnoreCase("qpifull")) {
                                arr[5] = "http://www.quotepower.com/web/taifook/qpmain.asp";
                            }
                          
                            pstmt1.setString(1, arr[0].trim());//custcode
                            pstmt1.setString(2, arr[1].trim());//loginid
                            pstmt1.setString(3, arr[2].trim());//RTQChnlName
                            pstmt1.setString(4, arr[3].trim());//RTQChnlUsrID
                            pstmt1.setString(5, arr[4].trim());//RTQChnlUsrPwd
                            pstmt1.setString(6, arr[5].trim());//RTQChnlURL
                            pstmt1.executeUpdate();
                        } else if (uploadType.equals("broker")) {
                            
                            pstmt2.setString(1, arr[0].trim());//UsrId
                            pstmt2.setString(2, arr[1].trim());
                            pstmt2.setString(3, arr[2].trim());
                            pstmt2.setString(4, arr[3].trim());
                            pstmt2.setString(5, ClientMain.SEC_COMPANY);
                            pstmt2.executeUpdate();
                        } else if (uploadType.equals("futquote")) {
                            
                            pstmt3.setString(1, arr[0].trim());//custcode
                            pstmt3.setString(2, arr[1].trim());//loginid
                            pstmt3.setString(3, arr[2].trim());//RTQ_CODE_DISPLAY
                            pstmt3.setString(4, arr[3].trim());//PROVIDER_USERID
                            pstmt3.setString(5, arr[4].trim());//PROVIDER_PASSWARD
                            pstmt3.setString(6, arr[5].trim());//MARKET
                            pstmt3.setString(7, arr[6].trim());//TFFO_RTQ_PRODUCT
                            pstmt3.executeUpdate();
                        }else if (uploadType.equals("futbroker")) {
                            
                            pstmt4.setString(1, arr[0].trim());//UsrId
                            pstmt4.setString(2, arr[1].trim());
                            pstmt4.setString(3, arr[2].trim());
                            pstmt4.setString(4, arr[3].trim());
                            pstmt4.setString(5, ClientMain.FUT_COMPANY);
                            pstmt4.executeUpdate();
                        }

                        counters.increment("TOTAL");
                    }
                } catch (SQLException e) {
                    errMap.put("SQLException", e);
                    status.end();
                    return;
                } finally {
//
//                    try {
//                        if (conn != null && !conn.isClosed())
//                            conn.close();
//                    } catch (Exception ex) {
//                        ex.printStackTrace();
//                    }
                	DBConnPoolUtil.closeConnection(conn, pstmt);
                    DBConnPoolUtil.closeConnection(conn, pstmt1);
                    DBConnPoolUtil.closeConnection(conn, pstmt2);
                    DBConnPoolUtil.closeConnection(conn, pstmt3);
                    DBConnPoolUtil.closeConnection(conn, pstmt4);
                }

                status.end();
            }

        };

        Thread backendThread = new Thread(runnable);
        backendThread.start();

    }

}
