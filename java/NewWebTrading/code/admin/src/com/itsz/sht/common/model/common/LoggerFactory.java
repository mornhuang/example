package com.itsz.sht.common.model.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.itsz.sht.common.Consts;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoggerFactory {
	//debug
	private  Log commonDebug = LogFactory.getLog(Consts.Log.Debug.COMMON);
	private  Log mcsDebug = LogFactory.getLog(Consts.Log.Debug.MCS);
	private  Log qsDebug = LogFactory.getLog(Consts.Log.Debug.QS);
	private  Log esDebug = LogFactory.getLog(Consts.Log.Debug.ESERVICE);
	private  Log agentDebug = LogFactory.getLog(Consts.Log.Debug.AGENT);
	//info
	private  Log commonInfo = LogFactory.getLog(Consts.Log.Info.COMMON);
	private  Log mcsInfo = LogFactory.getLog(Consts.Log.Info.MCS);
	private  Log qsInfo = LogFactory.getLog(Consts.Log.Info.QS);
	private  Log esInfo = LogFactory.getLog(Consts.Log.Info.ESERVICE);
	private  Log idpInfo = LogFactory.getLog(Consts.Log.Info.IDP);
	private  Log agentInfo = LogFactory.getLog(Consts.Log.Info.AGENT);
	//error
	private  Log commonError = LogFactory.getLog(Consts.Log.Error.COMMON);
	private  Log mcsError = LogFactory.getLog(Consts.Log.Error.MCS);
	private  Log qsError = LogFactory.getLog(Consts.Log.Error.QS);
	private  Log esError = LogFactory.getLog(Consts.Log.Error.ESERVICE);
	private  Log agentError = LogFactory.getLog(Consts.Log.Info.AGENT);
	
	private static LoggerFactory logger = new LoggerFactory();
	
	private LoggerFactory(){
	}
    
    public Log getAgentDebug() {
		return agentDebug;
	}

	public Log getAgentInfo() {
		return agentInfo;
	}

	public Log getAgentError() {
		return agentError;
	}

	/**
     * 
     * @Author:Cimenon Saint
     * @Time:2007-7-3 16:24:35
     * @param beginTime
     * @param model
     */
    public void info(long beginTime , String model){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//绛変环浜巒ow.toLocaleString()
        Calendar calendar = Calendar.getInstance();
        getInstance().commonInfo.info(sf.format(calendar.getTime())+ " "+Thread.currentThread().getName()+" admin portal call:"+ model +" " + (System.currentTimeMillis() - beginTime));
    }
    
    /**
     * 
     * @Author:Cimenon Saint
     * @Time:2007-7-3 16:47:48
     * @param beginTime
     * @param model
     */
    public void qsInfo(long beginTime , String model){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//绛変环浜巒ow.toLocaleString()
        Calendar calendar = Calendar.getInstance();
        getInstance().commonInfo.info(sf.format(calendar.getTime())+ " "+Thread.currentThread().getName()+" qs call:"+model+" " + (System.currentTimeMillis() - beginTime));
    }
    
    /**
     * 
     * @Author:Cimenon Saint
     * @Time:2007-7-3 16:59:49
     * @param beginTime
     * @param model
     */
    public void esInfo(long beginTime , String model){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//绛変环浜巒ow.toLocaleString()
        Calendar calendar = Calendar.getInstance();
        getInstance().commonInfo.info(sf.format(calendar.getTime())+ " "+Thread.currentThread().getName()+" es call:"+model+" " + (System.currentTimeMillis() - beginTime));
    }
	
	public static LoggerFactory getInstance(){
		return logger;
	}
	public Log getCommonDebug() {
		return commonDebug;
	}
	public Log getCommonError() {
		return commonError;
	}
	public Log getCommonInfo() {
		return commonInfo;
	}
	public Log getEsDebug() {
		return esDebug;
	}
	public Log getEsError() {
		return esError;
	}
	public Log getEsInfo() {
		return esInfo;
	}
	public Log getMcsDebug() {
		return mcsDebug;
	}
	public Log getMcsError() {
		return mcsError;
	}
	public Log getMcsInfo() {
		return mcsInfo;
	}
	public Log getQsDebug() {
		return qsDebug;
	}
	public Log getQsError() {
		return qsError;
	}
	public Log getQsInfo() {
		return qsInfo;
	}

	public Log getIdpInfo() {
		return idpInfo;
	}

	public void setIdpInfo(Log idpInfo) {
		this.idpInfo = idpInfo;
	}
}
