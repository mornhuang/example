package com.itsz.sht.common;




import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.commons.lang.NumberUtils;

import com.taifook.framework.application.i18n.I18NHandler;
import com.taifook.framework.foundation.configuration.ConfException;
import com.taifook.framework.foundation.configuration.ConfRepository;
import com.taifook.framework.foundation.configuration.Configurable;
import com.taifook.framework.foundation.logging.Logger;
import com.taifook.framework.foundation.logging.LoggerFactory;
import com.taifook.mtss.ccis.webservice.impl.EServiceClientInfoModel2;

public class UserProfileUtilizer {
    public static final int MOBILE_LENGTH_HK = 8;
    public static final int MOBILE_LENGTH_PRC = 11;

    public static  boolean userIDAvailable(String userID){
        if ((userID.length()>=UserProfileConstants.TF_AC_LENGTH_MIN)&&(userID.length()<=UserProfileConstants.TF_AC_LENGTH_MAX)){
            boolean available =false;
            for (int i=0; i < userID.length(); i++ ){
                if ((userID.charAt(i)>'9') || (userID.charAt(i) > '9')){
                    available=true;
                }
            }
            return available;
        }
        return true;
    }

    public static String eserviceToMisDocType(String idType){
        String rtnIDType = null;

        //Check idType is no null to prevent null pointer exception
        if(idType!=null){
            if (idType.equals(UserProfileConstants.IDType.ID_CARD)){
                rtnIDType = UserProfileConstants.Mis.DocType.ID_CARD;
            }else if (idType.equals(UserProfileConstants.IDType.PASSPORT)){
                rtnIDType = UserProfileConstants.Mis.DocType.PASSPORT;
            }else if (idType.equals(UserProfileConstants.IDType.BUS_REG)){
                rtnIDType = UserProfileConstants.Mis.DocType.BUS_REG;
            }else if (idType.equals(UserProfileConstants.IDType.CERT_INCORP)){
                rtnIDType = UserProfileConstants.Mis.DocType.CERT_INCORP;
            }
        }

        return rtnIDType;
    }


    public static String getValue(String attribute) {
        Logger log = LoggerFactory.instance().getLogger();
        String value = null;

        try {
            if (log.isInfoEnabled())
                log.info("UserProfile.getValue(): start");

            ConfRepository confRepository = new ConfRepository(UserProfileConstants.Config.USER_PROFILE_CONFIG_FILE);
            Configurable conf = confRepository.getConfiguration();

            value = conf.getValue("//user-profile/"+ attribute + "/text()");

            if (log.isInfoEnabled())
                log.info("UserProfile.getValue(): end");
        } catch (ConfException e) {
            if (log.isErrorEnabled())
                log.error("UserProfile.getValue() : cannot read configuration file.",e);
            value = "100" ;
        }

        return value;
    }


    public static String custCodeToLoginID(String custCode) {

        for (int index=UserProfileConstants.TF_AC_LENGTH_MIN; index < UserProfileConstants.TF_AC_LENGTH_MAX; index++){
            if (custCode.charAt(0)=='0'){
                custCode=custCode.substring(1,custCode.length());
            }
        }
        return custCode;
    }



    public static boolean isETradeAC(String account) {
        Logger log = LoggerFactory.instance().getLogger();

        account=account.trim();

        log.debug("Account : " + account);

        if ((account==null)||(account.length()<3)){
            return false;
        }

        if (!account.substring(0, 3).equals(UserProfileConstants.SecuritiesACType.SECURITIES)){
            return false;
        }

        if ((account.substring(account.length()- 3, account.length()).equals(UserProfileConstants.SecuritiesACType.ONLINE_CASH)) ||
            (account.substring(account.length()- 3, account.length()).equals(UserProfileConstants.SecuritiesACType.ONLINE_MARGIN))){
            log.debug("Account Suffix : " + account.substring(account.length()- 3, account.length()));
            return true;
        }else{
            log.debug("Account Suffix : " + account.substring(account.length()- 3, account.length()));
            return false;
        }
    }

//    public static boolean validateMobilePhone(UserProfileDTO userProfile) {
//        String mobile = userProfile.getMobile();
//        boolean result = NumberUtils.isDigits(mobile);
//        if (UserProfileConstants.SMSRegionType.HONGKONG.equalsIgnoreCase(userProfile.getSmsRegion())){
//            return result && (mobile.length() == MOBILE_LENGTH_HK);
//        }
//        if (UserProfileConstants.SMSRegionType.MAINLANDCHINA.equalsIgnoreCase(userProfile.getSmsRegion())){
//            return result && (mobile.length() == MOBILE_LENGTH_PRC);
//        }
//        return result;
//    }
    public static String checkACType(String ac) {
            if (ac.indexOf(UserProfileConstants.SecuritiesACType.FUTURES)==0){
                return UserProfileConstants.ACType.FUTURES;
            }

            if (((ac.indexOf(UserProfileConstants.SecuritiesACType.SECURITIES)==0))&&((ac.indexOf(UserProfileConstants.SecuritiesACType.ONLINE_CASH) == (ac.length() - UserProfileConstants.SecuritiesACType.ONLINE_CASH.length()))||
            (ac.lastIndexOf(UserProfileConstants.SecuritiesACType.ONLINE_MARGIN) == (ac.length() - UserProfileConstants.SecuritiesACType.ONLINE_MARGIN.length())))){
                return UserProfileConstants.ACType.SECURITIES_ONLINE;
            }

            if (((ac.indexOf(UserProfileConstants.SecuritiesACType.SECURITIES)==0))&&((ac.lastIndexOf(UserProfileConstants.SecuritiesACType.OFFLINE_CASH) == (ac.length() - UserProfileConstants.SecuritiesACType.OFFLINE_CASH.length()))||
            (ac.lastIndexOf(UserProfileConstants.SecuritiesACType.OFFLINE_MARGIN) == (ac.length() - UserProfileConstants.SecuritiesACType.OFFLINE_MARGIN.length())))){
                return UserProfileConstants.ACType.SECURITIES_OFFLINE;
            }

            return UserProfileConstants.ACType.SECURITIES_ONLINE;

    }

    public static String getLocalizedACString(String ac, I18NHandler i18n) {
        String localizedACType = getLocalizedACType(ac, i18n);

        if ("".equals(localizedACType))
            return localizedACType;

        else
            return localizedACType + " " + ac;
    }

    public static String getLocalizedACType(String ac, I18NHandler i18n){
        if (ac == null)
            return "";/////////

        String accountType = UserProfileUtilizer.checkACType(ac);

        String accountTypeDisplay = "";

        if (accountType.equals(UserProfileConstants.ACType.FUTURES)) {
            accountTypeDisplay = "personal_info_fut_ac";
        }
        else if (accountType.equals(UserProfileConstants.ACType.
                                    SECURITIES_ONLINE)) {
            accountTypeDisplay = "personal_info_sec_online_ac";
        }
        else if (accountType.equals(UserProfileConstants.ACType.
                                    SECURITIES_OFFLINE)) {
            accountTypeDisplay = "personal_info_sec_offline_ac";
        }
        else {
            return "";//////////
        }

        return i18n.getLocalizedString(accountTypeDisplay, "");
    }

    public static ArrayList getSecAcList(ArrayList acList){
        ArrayList acLst = new ArrayList();
        for(int i=0; i<acList.size(); i++){
            String ac = (String) acList.get(i);
            if(ac.indexOf(UserProfileConstants.SecuritiesACType.SECURITIES) == 0){
                acLst.add(ac);
            }
        }
        return acLst;
    }

    public static ArrayList getFutAcList(ArrayList acList){
        ArrayList acLst = new ArrayList();
        for(int i=0; i<acList.size(); i++){
            String ac = (String) acList.get(i);
            if(ac.indexOf(UserProfileConstants.SecuritiesACType.FUTURES) == 0){
                acLst.add(ac);
            }
        }
        return acLst;
    }
    
//    public static void updateByCCIS(UserProfileDTO dto,EServiceClientInfoModel2 ccisInfo){
//    	
//    	dto.setUserName(ccisInfo.getCustName()==null?"":ccisInfo.getCustName().trim());
//    	dto.setNationality(ccisInfo.getNationality()==null?"":ccisInfo.getNationality().trim());
//        dto.setIdNumber(ccisInfo.getDocId()==null?"":ccisInfo.getDocId().trim());
//
//        dto.setCountry(ccisInfo.getCountryCode()==null?"":ccisInfo.getCountryCode().trim());
//        
//        dto.setHomePhone(ccisInfo.getPhoneNum1()==null?"":ccisInfo.getPhoneNum1().trim());
//        dto.setHomePhone2(ccisInfo.getPhoneNum2()==null?"":ccisInfo.getPhoneNum2().trim());
//
//        dto.setAddr1(ccisInfo.getAddressLine1()==null?"":ccisInfo.getAddressLine1().trim());
//        dto.setAddr2(ccisInfo.getAddressLine2()==null?"":ccisInfo.getAddressLine2().trim());
//        dto.setAddr3(ccisInfo.getAddressLine3()==null?"":ccisInfo.getAddressLine3().trim());
//        dto.setDistrict(ccisInfo.getDistrict()==null?"":ccisInfo.getDistrict().trim());
//        dto.setOccup(ccisInfo.getOccupation()==null?"":ccisInfo.getOccupation().trim());
//
//        if(ccisInfo.getBirthday() !=null){
//            dto.setBirthday(new Timestamp(((Calendar)ccisInfo.getBirthday()).getTimeInMillis()));
//        }
//
//        dto.setIdType(dto.convertIDType(ccisInfo.getDocType())); 
//        dto.setStaff(dto.convertStaff(ccisInfo.getIndStaff()));
//        dto.setSex(dto.convertSex(ccisInfo.getSex()));
//        dto.setMarital(ccisInfo.getMartial()==null?"":ccisInfo.getMartial().trim());
//        dto.setSalutation(ccisInfo.getTitleCode()==null?"":ccisInfo.getTitleCode().trim());
//            
//        
//    }

}



