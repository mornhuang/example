/*
Copyright (c) 2006 Beyhan Meyrali [C*]
All rights reserved.
/**
Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:
1. Redistributions of source code must retain the above copyright
   notice, this list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright
   notice, this list of conditions and the following disclaimer in the
   documentation and/or other materials provided with the distribution.
3. The name of the author may not be used to endorse or promote products
   derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package net.sourceforge.smsengine;

import java.util.ArrayList;

import org.apache.log4j.Logger;

/**
 * @author Beyhan Meyrali
 * This class can be used for text mode usage
 * or running batch jobs with an other application.
 *
 */
public class SMSConsole {
    
	/** Set Logger */
    private static final Logger log = Logger.getLogger(StartUp.class.getName());
	
	/** Create an instance of sendSMS */
    private SendSMS sendSMS = null;
    
    /** Recievers List */
    private java.util.ArrayList recieversList = null;
    
    /** Message */
    private String message = "";
    
    /** Settings for Database Connection to Load messages*/ 
    private String mDatabaseType ="";
    private String mDatabaseServerName ="";
    private String mDBPort ="";
    private String mUserName ="";
    private String mPassword ="";
    private String mTnsNameOrDBName ="";
    private String mTableName ="";
    private String mColumnName ="";
    private String mQuery ="";
    
    /** Settings for Database Connection to Load receiver(s)*/
    private String rDatabaseType ="";
    private String rDatabaseServerName ="";
    private String rDBPort ="";
    private String rUserName ="";
    private String rPassword ="";
    private String rTnsNameOrDBName ="";
    private String rTableName ="";
    private String rColumnName ="";
    private String rQuery ="";    

    /**
	 * Load recievers from a file
	 * @param fileName takes the file name with path
	 * @return boolean
	 */
	public boolean loadRecieversFromAFile(String fileName){
		
		String num = "";
		
		try{
            if((fileName != null)&& (fileName.length()>0)){
                java.io.File file = new java.io.File(fileName);                         
                java.io.FileReader reader = new java.io.FileReader(file);					
                java.io.BufferedReader br = new java.io.BufferedReader(reader);   
                
                do{
                	num = br.readLine();
                	
                    num = num.replace(" ","");                    
                    num = num.replace("-","");                    
                    num = num.replace(")","");                    
                    num = num.replace("(","");
                
                    if(num.length()>0)
                		recieversList.add(num);
                
                }while(br.readLine()!=null);                
                br.close();					                
                if(recieversList.size()>0){                
                	log.debug("Imported the recievers from parameter ");
                	System.out.println("Reciever list is ready ...");
                	return true;
                }else{
                	log.debug("There is no reciever ! ");
                	System.out.println("There is no reciever ! ");                	
                	return false;
                }
            }else{
	            log.debug("Failed to import the recievers from a file ");
                System.out.println("Failed to import the recievers from a file ");
                return false;
            }
        }catch(Exception e){
	        log.debug("Failed to import the recievers from a file "+e.getMessage());
            System.out.println("Could not recievers read the file !");
            return false;
        }		
	}

	/**
	 * Load recievers from command line 
	 * Each number needs to be seperated with ","
	 * Example : 012345,647895
	 * @param numbers
	 * @return boolean
	 */
	public boolean loadRecieversFromCMDLine(String numbers){
		
		String num = "";
		
		try{
            if((numbers != null)&& (numbers.length()>0)){
            	log.debug("Numbers to send sms which are entered as parameter");
            	java.util.StringTokenizer st = new java.util.StringTokenizer(numbers,",");
                while (st.hasMoreTokens()) {

                	num = st.nextToken();
                    num = num.replace(" ","");                    
                    num = num.replace("-","");                    
                    num = num.replace(")","");                    
                    num = num.replace("(","");

                    if(num.length()>0)
                		recieversList.add(num);
                    System.out.println("Reciever number :" +num);
                    log.debug("Reciever number :" +num);
                }
                
                if(recieversList.size()>0){                
                	log.debug("Imported the recievers from parameter ");
                	System.out.println("Reciever list is ready ...");
                	return true;
                }else{
                	log.debug("There is no reciever ! ");
                	System.out.println("There is no reciever ! ");                	
                	return false;
                }
            }else{
	            log.debug("Failed to import the recievers from parameter ");
                System.out.println("Failed to import the recievers from parameter ");
                return false;
            }
        }catch(Exception e){
	        log.debug("Failed to import the recievers from a file "+e.getMessage());
            System.out.println("Could not read the message file !");
            return false;
        }		
	}
	
	
	/**
	 * Load message from a file
	 * @param fileName takes the file name with path
	 * @return boolean
	 */
	public boolean loadMessagesFromAFile(String fileName){
		try{
            if((fileName != null)&& (fileName.length()>0)){
                java.io.File file = new java.io.File(fileName);                         
                java.io.FileReader reader = new java.io.FileReader(file);					
                java.io.BufferedReader br = new java.io.BufferedReader(reader);   
                
                do{
                	message = message + br.readLine();
                }while(br.readLine()!=null);                
                br.close();					
                if(setMessage(message)){
                	log.debug("Imported the message from a file ");
                	return true;
                }else{
                	return false;                	
                }
            }else{
	            log.debug("Failed to import the message from a file ");
                System.out.println("Failed to import the message from a file ");
                return false;
            }
        }catch(Exception e){
	        log.debug("Failed to import the message from a file "+e.getMessage());
            System.out.println("Failed to import the message from a file "+e.getMessage());
            return false;
        }		
	}

	/**
	 * Load message from a Database table or view
	 * @param fileName takes the file name with path
	 * @return boolean
	 */
	public boolean loadMessagesFromDatabase(){
		try{	
			if((loadDatabaseSettingsForMessages())&&(mDatabaseType != null)&& (mDatabaseType.length()>0)
            		&&(mDatabaseServerName != null)&& (mDatabaseServerName.length()>0)
            		&&(mUserName != null)&& (mUserName.length()>0)
            		&&(mPassword != null)&& (mPassword.length()>0)
            		&&(mTnsNameOrDBName != null)&& (mTnsNameOrDBName.length()>0)
            		&&((mQuery != null)&& (mQuery.length()>0)||((mTableName != null)&& (mTableName.length()>0)&&(mColumnName != null)&& (mColumnName.length()>0)))){
				
				java.util.ArrayList dbData = new java.util.ArrayList();
            	
				if (mDatabaseType.equalsIgnoreCase("oracle")){
                	DBConnectionToOracle dbCon = new DBConnectionToOracle();
                	
                	dbCon.setDBNameOrIP(mDatabaseServerName);
                	dbCon.setDBPort(mDBPort);
                	dbCon.setDBUserName(mUserName);
                	dbCon.setDBPassword(mPassword);
                	dbCon.setDBTNSName(mTnsNameOrDBName);
                	dbCon.setExternalSQLStatement(mQuery);
                	dbCon.setTableName(mTableName);
                	dbCon.setColumnName(mColumnName);
                	
                	dbData = dbCon.getData();
                }else if (mDatabaseType.equalsIgnoreCase("mysql")){                	
                	DBConnectionToMySQL dbCon = new DBConnectionToMySQL();

                	dbCon.setDBNameOrIP(mDatabaseServerName);
                	dbCon.setDBPort(mDBPort);
                	dbCon.setDBUserName(mUserName);
                	dbCon.setDBPassword(mPassword);
                	dbCon.setDBName(mTnsNameOrDBName);
                	dbCon.setExternalSQLStatement(mQuery);
                	dbCon.setTableName(mTableName);
                	dbCon.setColumnName(mColumnName);
                	
                	dbData = dbCon.getData();                	
                }else{
                	log.debug("Unknown Database Type (You can connect to Oracle or MySQL DBs.");
                	System.out.println("Unknown Database Type (You can connect to Oracle or MySQL DBs.");
                	return false;
                }
                
                for(int i = 0;i<dbData.size();i++)
                	message = message + ((String)dbData.get(i));               
                					
                if(setMessage(message)){
                	log.debug("Imported the message from database ");
                	System.out.println("Imported the message from database ");
                	return true;
                }else{
                	return false;                	
                }
            }else{
	            log.debug("insufficient parameters, please check the parameters ");
                System.out.println("insufficient parameters, please check the parameters ");
                return false;
            }
			
        }catch(Exception e){
	        log.debug("Failed to import the message from database "+e.getMessage());
            System.out.println("Failed to import the message from database "+e.getMessage());
            return false;
        }		
	}

	/**
	 * Load receivers from a Database table or view
	 * @param fileName takes the file name with path
	 * @return boolean
	 */
	public boolean loadReceiversFromDatabase(){
		try{	
			if((loadDatabaseSettingsForReceivers())&&(rDatabaseType != null)&& (rDatabaseType.length()>0)
            		&&(rDatabaseServerName != null)&& (rDatabaseServerName.length()>0)
            		&&(rUserName != null)&& (rUserName.length()>0)
            		&&(rPassword != null)&& (rPassword.length()>0)
            		&&(rTnsNameOrDBName != null)&& (rTnsNameOrDBName.length()>0)
            		&&((rQuery != null)&& (rQuery.length()>0)||((rTableName != null)&& (rTableName.length()>0)&&(rColumnName != null)&& (rColumnName.length()>0)))){
				
				java.util.ArrayList dbData = new java.util.ArrayList();
            	
				if (rDatabaseType.equalsIgnoreCase("oracle")){
                	DBConnectionToOracle dbCon = new DBConnectionToOracle();
                	
                	dbCon.setDBNameOrIP(rDatabaseServerName);
                	dbCon.setDBPort(rDBPort);
                	dbCon.setDBUserName(rUserName);
                	dbCon.setDBPassword(rPassword);
                	dbCon.setDBTNSName(rTnsNameOrDBName);
                	dbCon.setExternalSQLStatement(rQuery);
                	dbCon.setTableName(rTableName);
                	dbCon.setColumnName(rColumnName);
                	
                	dbData = dbCon.getData();
                }else if (rDatabaseType.equalsIgnoreCase("mysql")){                	
                	DBConnectionToMySQL dbCon = new DBConnectionToMySQL();

                	dbCon.setDBNameOrIP(rDatabaseServerName);
                	dbCon.setDBPort(rDBPort);
                	dbCon.setDBUserName(rUserName);
                	dbCon.setDBPassword(rPassword);
                	dbCon.setDBName(rTnsNameOrDBName);
                	dbCon.setExternalSQLStatement(rQuery);
                	dbCon.setTableName(rTableName);
                	dbCon.setColumnName(rColumnName);
                	
                	dbData = dbCon.getData();                	
                }else{
                	log.debug("Unknown Database Type (You can connect to Oracle or MySQL DBs.");
                	System.out.println("Unknown Database Type (You can connect to Oracle or MySQL DBs.");
                	return false;
                }
                
                for(int i = 0;i<dbData.size();i++)
                    if(((String)dbData.get(i)).length()>0)
                		recieversList.add(dbData.get(i));                
                
                if(recieversList.size()>0){                
                	log.debug("Imported the recievers from parameter ");
                	System.out.println("Reciever list is ready ...");
                	return true;
                }else{
                	log.debug("There is no reciever ! ");
                	System.out.println("There is no reciever ! ");                	
                	return false;
                }
                					
            }else{
	            log.debug("insufficient parameters, please check the parameters ");
                System.out.println("insufficient parameters, please check the parameters ");
                return false;
            }
			
        }catch(Exception e){
	        log.debug("Failed to import the message from database "+e.getMessage());
            System.out.println("Failed to import the message from database "+e.getMessage());
            return false;
        }		
	}
		
    /* 
     * Load GSM Device Connection Settings from a property file.
     * @return boolean
     */
    public boolean loadGSMDeviceConnectionSettings(){     	 
        String gsmDeviceConnectionSettings = "/gsmDeviceConnectionSettings.properties";
        java.io.InputStream sis = this.getClass().getResourceAsStream(gsmDeviceConnectionSettings); 

        if ( sis == null ) { 
            log.debug(gsmDeviceConnectionSettings+" file does not exist"); 
            System.out.println(gsmDeviceConnectionSettings+" file does not exist");
            // Doesn't exist 
            return false; 
        }

        // Load props 
        java.util.Properties sourceProp = new java.util.Properties(); 
                
        try{
        	sourceProp.load(sis);
        	if ( sourceProp != null ){
            	
                log.debug("Setting default gsm device connection settings  ");
                sendSMS.setPort(sourceProp.getProperty("port").trim());
                sendSMS.setBaudRate(sourceProp.getProperty("baudRate").trim());
                sendSMS.setSourcePort(sourceProp.getProperty("sourcePort").trim());
                sendSMS.setDestinationPort(sourceProp.getProperty("destinationPort").trim());
                sendSMS.setSimPin(sourceProp.getProperty("pin").trim());
                sendSMS.setSMSCenterNumber(sourceProp.getProperty("smsCNum").trim());	                                 

                log.debug(" Port :" + sourceProp.getProperty("port").trim()); 
                log.debug(" BaudRate :" + sourceProp.getProperty("baudRate").trim()); 
                log.debug(" Sim Pin :" + sourceProp.getProperty("pin").trim()); 
                log.debug(" Sms Service Center Number :" + sourceProp.getProperty("smsCNum").trim()); 
                log.debug(" Source Port :" + sourceProp.getProperty("sourcePort").trim()); 
                log.debug(" Destination Port :" + sourceProp.getProperty("destinationPort").trim()); 
                return true;
        	}else{
                log.debug(gsmDeviceConnectionSettings+" file does not exist");
                System.out.println(gsmDeviceConnectionSettings+" file does not exist");
        		return false;
        	}
        }catch(Exception e){ 
            log.debug(gsmDeviceConnectionSettings+" file does not exist or missing parameters in file");
            System.out.println(gsmDeviceConnectionSettings+" file does not exist or missing parameters in file");
            return false; 
        }         
    }

    /* 
     * Load Database Connection Settings from a property file for messages.
     * @return boolean
     */
    public boolean loadDatabaseSettingsForMessages(){ 
        String databaseConnectionSettings = "/databaseConnectionSettings.properties"; 
        java.io.InputStream sis = this.getClass().getResourceAsStream(databaseConnectionSettings); 

        if ( sis == null ) { 
            log.debug(databaseConnectionSettings+" file does not exist"); 
            System.out.println(databaseConnectionSettings+" file does not exist");
            // Doesn't exist 
            return false; 
        }

        // Load props 
        java.util.Properties sourceProp = new java.util.Properties(); 
        
        try{ 
            sourceProp.load(sis);
            if ( sourceProp != null ){
                log.debug("Setting default database connection settings for messages from property file");
                mDatabaseType =sourceProp.getProperty("mDatabaseType").trim();
                mDatabaseServerName =sourceProp.getProperty("mDatabaseServerName").trim();
                mDBPort =sourceProp.getProperty("mDBPort").trim();
                mUserName =sourceProp.getProperty("mUserName").trim();
                mPassword =sourceProp.getProperty("mPassword").trim();
                mTnsNameOrDBName =sourceProp.getProperty("mTnsNameOrDBName").trim();
                mTableName =sourceProp.getProperty("mTableName").trim();
                mColumnName =sourceProp.getProperty("mColumnName").trim();
                mQuery =sourceProp.getProperty("mQuery").trim();

                log.debug("Database Type : " + mDatabaseType); 
                log.debug("Database Server Name / IP : " + mDatabaseServerName);
                log.debug("Database Server Connection Port : " +mDBPort);
                log.debug("Database User Name : "+mUserName);
                log.debug("Database Password : " + mPassword);
                log.debug("Database Name / TNS Name : " +mTnsNameOrDBName);
                log.debug("Table Name : " +mTableName);
                log.debug("Column Name : " + mColumnName);
                log.debug("SQL Query to run : "+ mQuery);
                
                return true;
    	}else{
            log.debug(databaseConnectionSettings+" file does not exist");
            System.out.println(databaseConnectionSettings+" file does not exist");
    		return false;
    	}
            
        }catch(Exception e){ 
            log.debug(databaseConnectionSettings+" file does not exist or missing parameters in file");
            System.out.println(databaseConnectionSettings+" file does not exist or missing parameters in file");
            return false; 
        } 
    }

    /* 
     * Load Database Connection Settings from a property file for receivers.
     * @return boolean
     */
    public boolean loadDatabaseSettingsForReceivers(){ 
        String databaseConnectionSettings = "/databaseConnectionSettings.properties"; 
        java.io.InputStream sis = this.getClass().getResourceAsStream(databaseConnectionSettings); 

        if ( sis == null ) { 
            log.debug(databaseConnectionSettings+" file does not exist"); 
            System.out.println(databaseConnectionSettings+" file does not exist");
            // Doesn't exist 
            return false; 
        }

        // Load props 
        java.util.Properties sourceProp = new java.util.Properties(); 

        try{ 
            sourceProp.load(sis);
            if ( sourceProp != null ){
                log.debug("Setting default database connection settings for receivers from property file ");
                rDatabaseType =sourceProp.getProperty("rDatabaseType").trim();
                rDatabaseServerName =sourceProp.getProperty("rDatabaseServerName").trim();
                rDBPort =sourceProp.getProperty("rDBPort").trim();
                rUserName =sourceProp.getProperty("rUserName").trim();
                rPassword =sourceProp.getProperty("rPassword").trim();
                rTnsNameOrDBName =sourceProp.getProperty("rTnsNameOrDBName").trim();
                rTableName =sourceProp.getProperty("rTableName").trim();
                rColumnName =sourceProp.getProperty("rColumnName").trim();
                rQuery =sourceProp.getProperty("rQuery").trim();

                log.debug("Database Type : " + rDatabaseType); 
                log.debug("Database Server Name / IP : " + rDatabaseServerName);
                log.debug("Database Server Connection Port : " +rDBPort);
                log.debug("Database User Name : "+rUserName);
                log.debug("Database Password : " + rPassword);
                log.debug("Database Name / TNS Name : " +rTnsNameOrDBName);
                log.debug("Table Name : " +rTableName);
                log.debug("Column Name : " + rColumnName);
                log.debug("SQL Query to run : "+ rQuery);
                
                return true;
        	}else{
                log.debug(databaseConnectionSettings+" file does not exist");
                System.out.println(databaseConnectionSettings+" file does not exist");
        		return false;
        	}
        }catch(Exception e){ 
            log.debug(databaseConnectionSettings+" file does not exist or missing parameters in file");
            System.out.println(databaseConnectionSettings+" file does not exist or missing parameters in file");
            return false; 
        }    
    }
        
    /*
     * setMessage
     * @param message
     * @return boolean
     */
    public boolean setMessage(String message){
    	if(message.trim().length()>0){
    		this.message=message;
    		System.out.println("Message is ready ...");
    		log.debug("Your message is set to :"+ message);
    		return true;
    	}else{
    		log.debug("There is no text to send !"+ message);
    		System.out.println("There is no text to send !"+ message);
    		return false;
    	}
    }
    
    
    /*
     * Create an instance of SendSMS
     * Load Gsm device connection settings
     */
    public SMSConsole(){
    	log.debug("SendSMS Console Application is starting ...");
    	System.out.println("Copyright (c) 2006 Beyhan Meyrali [C*]");
    	System.out.println("SendSMS Console Application is starting ...");    	
    	sendSMS = new SendSMS();
    	this.loadGSMDeviceConnectionSettings();
    	recieversList = new java.util.ArrayList();
    }

    /**
     * send the sms messages
     * @return boolean
     */
    public boolean send(){
    	//Check the reciever list size
    	if(recieversList.size()<1){
    		System.out.println("There is no reciever !");
    		log.debug("There is no reciever !");
    		return false;
    	}
    	
    	//Check the message length 
    	if(message.trim().length()<1){
    		System.out.println("There is no text to send !");
    		log.debug("There is no text to send !");
    		return false;
    	}

    	if(sendSMS.connectToDevice()){
    		
    		String num = "";
    		sendSMS.setSMSMessage(message);
    		
    		int i=0;
    		
    	    log.debug("Connected to device :)");
            for(i=0;i<recieversList.size();i++) {
            	
            	num = (String) recieversList.get(i);
                
            	sendSMS.setDestinationNumber(num);
                
            	log.debug("Setting reciever number " + num);
                
            	if(!sendSMS.sendSMS()){
                    log.debug("Could not send sms to " + num);
                    System.out.println("Could not send sms to " + num);
                    log.debug("Reseting the phone connection ");
                    System.out.println("Reseting the phone connection ");
                    sendSMS.resetConnection();
                }else{
                	log.debug("SMS sent to : " + num);
                	System.out.println("SMS sent to : " + num);
                }
            }    
		    
            log.debug("Sending SMS completed (Total :"+ Integer.toString(i)+")");
            System.out.println("Sending SMS completed (Total :"+ Integer.toString(i)+")");
		    log.debug("Trying to disconnect from device ");
            System.out.println("Trying to disconnect from device ");
		    
		    sendSMS.disconnectFromDevice();
            return true;

    	}else{
		    log.debug("Could not connect to device ");
		    System.out.println("Could not connect to device ");
		    return false;
    	}
    }
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//Here we go....
		ArrayList arguments = new ArrayList();			
		
        for (String s: args) {
        	arguments.add((String)s);
        }

        if(arguments.size()>0){
        	SMSConsole smsConsole = new SMSConsole();
        	boolean checkRecFlag = false;
        	boolean checkMesFlag = false;
        	for(int i=0;i<arguments.size();i++){
        		
        		String arg = (String)arguments.get(i);
        		
            	if((arg.equalsIgnoreCase("-help"))||(arg.equalsIgnoreCase("-h"))){
                	System.out.println("SMSEngine Console Argument List :");
                	System.out.println(" ");
                	System.out.println("-m  : message   , usage: -m 'this is my message'");
                	System.out.println("-r  : receivers , usage: -r '004477728633XX' or -r '004477728633XX','077728633XX','053634565XX'");
                	System.out.println("-mf : message from file , usage: -mf 'c:\\SMSFolder\\message01.txt'");
                	System.out.println("-mdb : message from database , usage: -mdb , application will try to read the parameters from property file.");
                	System.out.println("-rf : receivers from file , usage: -rf 'c:\\SMSFolder\\recievers01.txt'");
                	System.out.println("-rdb : receivers from database , usage: -rdb , application will try to read the parameters from property file.");                	
                	System.out.println("");
                	System.out.println("-h or -help : shows the argument list");
                	System.out.println("");
                	System.out.println("Note : If you are a Windows user please use \\\\ instead \\");
                	System.out.println("");
                	System.out.println("Enjoy :)");
                	System.out.println("");
                }
            	//Set the message from command line as string
                if((arg.equalsIgnoreCase("-m"))||(arg.equalsIgnoreCase("-message"))){
                	if(!smsConsole.setMessage((String)arguments.get(i+1))){
                		System.out.println("There is no message to send , please see the -m parameter usage");
                		System.out.println("-m  : message   , usage: -m 'this is my message'");
                		checkMesFlag = false;
                	}else{
                		checkMesFlag = true;
                		i=i+1;
                		System.out.println("Set message completed :) ");
                	}
                }
                //Set the recievers from command line comma seperated
                if(arg.equalsIgnoreCase("-r")){
                	if(!smsConsole.loadRecieversFromCMDLine(((String)arguments.get(i+1)))){
                		System.out.println("There is no reciever to send , please see the -r parameter usage");
                    	System.out.println("-r  : recievers , usage: -r '004477728633XX' or -r '004477728633XX','077728633XX','053634565XX'");
                    	checkRecFlag = false;
                	}else{
                		checkRecFlag = true;
                		i=i+1;
                		System.out.println("Set reciever(s) completed :) ");
                	}
                }
                //Load the message from a file
                if(arg.equalsIgnoreCase("-mf")){
                	if(!smsConsole.loadMessagesFromAFile(((String)arguments.get(i+1)))){
                		System.out.println("Could not load the message from file , please see the -mf parameter usage");
                    	System.out.println("-mf : message from file , usage: -mf 'c:\\SMSFolder\\message01.txt'");
                    	checkMesFlag = false;                		
                	}else{
                		checkMesFlag = true;
                		i=i+1;
                		System.out.println("Message load from a file completed :) ");
                	}
                }
                
                //Load the message from database
                if(arg.equalsIgnoreCase("-mdb")){
                	if(!smsConsole.loadMessagesFromDatabase()){
                		System.out.println("Could not load the message from database , please see the -mdb parameter usage");
                    	System.out.println("-mdb : message from database , usage: -mdb , application will try to read the parameters from property file.");
                    	checkMesFlag = false;                		
                	}else{
                		checkMesFlag = true;
                		System.out.println("Message load from a database completed :) ");
                	}
                }                
                
                //Load the recievers from a file
                if(arg.equalsIgnoreCase("-rf")){
                	if(!smsConsole.loadRecieversFromAFile(((String)arguments.get(i+1)))){
                		System.out.println("Could not load reciever(s) from file , please see the -rf parameter usage");
                    	System.out.println("-rf : recievers from file , usage: -rf 'c:\\SMSFolder\\recievers01.txt'");
                    	checkRecFlag = false;                		
                	}else{
                		checkRecFlag = true;
                		i=i+1;
                		System.out.println("Recievers load from a file completed :) ");
                	}
                }
                //Load the receiver(s) from database
                if(arg.equalsIgnoreCase("-rdb")){
                	if(!smsConsole.loadReceiversFromDatabase()){
                		System.out.println("Could not load the receiver(s) from database , please see the -rdb parameter usage");
                    	System.out.println("-mdb : receiver(s) from database , usage: -rdb , application will try to read the parameters from property file.");
                    	checkRecFlag = false;                		
                	}else{
                		checkRecFlag = true;
                		System.out.println("receiver(s) load from a database completed :) ");
                	}
                }                
                
        	} 

        	//if everything is ok then send the sms
        	if((checkRecFlag)&&(checkMesFlag)){
        		System.out.println("Trying to send sms ...");
        		smsConsole.send();
        	}else{
        		System.out.println("Please set message/reciever(s) parameters ");
        		System.exit(0);
        	}
        }else{
        	System.out.println("No Parameter ! Program will exit");
        	System.exit(0);
        }
	}			
		
}
