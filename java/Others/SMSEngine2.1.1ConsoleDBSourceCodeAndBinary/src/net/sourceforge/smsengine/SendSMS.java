/*
Copyright (c) 2006 Beyhan Meyrali [C*]
All rights reserved.

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

/*
 * SendSMS.java
 *
 * Created on 25 September 2006, 20:29
 *
 */

package net.sourceforge.smsengine;

import org.smslib.*;
import org.apache.log4j.*;

import gnu.io.*;
/** 
 * This is the class which interract with smslib
 */

/**
 *
 * @author Beyhan Meyrali
 */
public class SendSMS {
    
    CService srv = null;

   /*
    * Get logger instance
    */
    private static final Logger log = Logger.getLogger(StartUp.class.getName());

   /*
    *PC connection Settings
    */
   private static String port = "COM5" ;
   private static int baudRate=9600;
   
   /*
    * GSM Device Settings
    */
   private static String pin     = "0000";
   private static String smsCNum = "";
   private static int sourcePort =0;
   private static int destinationPort=50001;
   
   /*
    *sms settings
    */
   private static String message   = "";
   private static String destinationNumber = "";
      
   /**
    * set port. Can be as COM4 or COM5 or LPT1 etc
	* you can find the port by checking from control panel / fax-modem
	* @return boolean
    */
   public boolean setPort(String portNumber){
      if(portNumber.length()>0) {
        this.port = portNumber;
        return true;
      }else{
          return false;
      }
   }

   /**
    * set BaudRate. Default 9600
	* @return boolean
    */
   public boolean setBaudRate(String baudRate){
      if(baudRate.length()>0) {
        try{  
            this.baudRate = Integer.getInteger(baudRate);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }        
      }else{
          return false;
      }
   }

   /**
    * set sourcePort. Default 0
	* @return boolean
	*/
   public boolean setSourcePort(String sourcePort){
      if(sourcePort.length()>0) {
        try{  
            this.sourcePort = Integer.getInteger(sourcePort);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }        
      }else{
          return false;
      }
   }   


   /**
    * set sourcePort. Default 50001
	* @return boolean
	*/
   public boolean setDestinationPort(String destinationPort){
      if(destinationPort.length()>0) {
        try{  
            this.destinationPort = Integer.getInteger(destinationPort);
            return true;
        }catch(Exception e){
            System.out.println(e.getMessage());
            return false;
        }        
      }else{
          return false;
      }
   }      
   
   /**
    * set Sim Pin. default "0000"
	* @return boolean
	*/
   public boolean setSimPin(String simPin){
      if(simPin.length()>0) {
        this.pin = simPin;
        return true;
      }else{
          return false;
      }
   }   
   
   /**
    * set SMS Center Number. default ""
	* @return boolean
	*/
   public boolean setSMSCenterNumber(String smsC){
      if(smsC.length()>0) {
        this.smsCNum = smsC;
        return true;
      }else{
          return false;
      }
   }   
    
   /**
    * set SMS Message text . default "Ahoij"
	* why Ahoij ;) ?
	* @return boolean
	*/
   public boolean setSMSMessage(String smsMsg){
      if(smsMsg.length()>0) {
        this.message = smsMsg;
        return true;
      }else{
          return false;
      }
   }   

   /**
    * set SMS destination number . default ""
	* @return boolean
	*/
   public boolean setDestinationNumber(String number){
      if(number.length()>0) {
        this.destinationNumber = number;
        return true;
      }else{
          return false;
      }
   }   
       
   /**
    * get port. 
	* @return String
	*/
   public String getPort(){
      return this.port;
   }

   /**
    * get BaudRate. 
	* @return String
    */
   public String getBaudRate(){
        try{  
            return String.valueOf(this.baudRate);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "UnKnown";
        }        
   }

   /**
    * get sourcePort.
	* @return String
    */
   public String getSourcePort(){
        try{  
            return String.valueOf(this.sourcePort);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "UnKnown";
        }        
   }   


   /**
    * get destinationPort. 
	* @return String
    */
   public String getDestinationPort(){
        try{  
            return String.valueOf(this.destinationPort);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return "UnKnown";
        }        
   }      
   
   /**
    * get simPin.
	* @return String
    */
   public String getSimPin(){
     return this.pin;
   }   
   
   /**
    * get SMS Center Number.
	* @return String
    */
   public String getSMSCenterNumber(){
     return  this.smsCNum;
   }   
    
   /**
    * get SMS Message text . 
	* @return String
    */
   public String getSMSMessage(){
      return  this.message;
   }   

   /**
    * get SMS destination number .
	* @return String
    */
   public String getDestinationNumber(){
      return  this.destinationNumber;
   }   
   
   /**
    * Connect to device
    * @return boolean
    */
   public boolean connectToDevice(){
      try{ 
          srv = new CService(port, baudRate, "", "");
          srv.setSimPin(pin);         
          log.debug("Connecting to Device on port :" + String.valueOf(port) +" with baudRate : " + String.valueOf(baudRate));
          System.out.println("Connecting to Device on port :" + String.valueOf(port) +" with baudRate : " + String.valueOf(baudRate));
          srv.connect();
          srv.setSmscNumber(smsCNum);                   
          log.debug("  Using " + srv._name + " v" + srv._version);       
          System.out.println("  Using " + srv._name + " v" + srv._version);       
          return srv.getConnected();
      }catch(Exception e) {
    	  log.debug("Error connectToDevice()" + e.getMessage());
    	  System.out.println("Error connectToDevice()" + e.getMessage());
    	  return false;
      }
   }
   

   /**
    *Disconnect from device
	* @return boolean
    */
   public boolean disconnectFromDevice(){
      try{ 
        srv.disconnect();
        return srv.getConnected();
      }catch(Exception e) {
        log.debug("Error disconnectFromDevice()" + e.getMessage());
        System.out.println("Error disconnectFromDevice()" + e.getMessage());
        return false;
      }
   }
      
   /**
    *Sent SMS
    * @return boolean
    */
   public boolean sendSMS(){
      try{ 
         COutgoingMessage msg = new COutgoingMessage(destinationNumber, message);
         msg.setSourcePort(sourcePort);
         msg.setDestinationPort(destinationPort);
         srv.sendMessage(msg);
         log.debug("SMS sent to " + destinationNumber);
         System.out.println("SMS sent to " + destinationNumber);
        return true;
      }catch(Exception e) {
    	  log.debug("Error connectToDevice()" + e.getMessage());
    	  System.out.println("Error connectToDevice()" + e.getMessage());
    	  return false;
      }
   }
   
   /**
    * Disconnet from device and connect again
	* @return boolean
    */
   public boolean resetConnection(){
       try{
           srv.disconnect();
           srv.connect();
           return srv.getConnected();
       }catch(Exception e){
           log.debug("Error connectToDevice()" + e.getMessage());
           System.out.println("Error connectToDevice()" + e.getMessage());
           return false;
       }    
   }

   /**
    * Constructor
	* Set logger properties
	*/
   public SendSMS() {
        //PropertyConfigurator.configure("c:\\logs\\log4j.properties");//Will be deleted, after adding the property file to classpath
        log.debug("SendSMS instance created ");
   }
   
   public static void main(String[] args) {
      /*
      CService srv = new CService(port, baudRate, "", "");
      System.out.println("SendMessage(): running.");
      System.out.println("  Using " + srv._name + " v" + srv._version);              
      try {
         srv.setSimPin(pin);         
         srv.connect();
         srv.setSmscNumber(smsCNum);
         COutgoingMessage msg = new COutgoingMessage(destinationNumber, message);
         msg.setSourcePort(sourcePort);
         msg.setDestinationPort(destinationPort);
         srv.sendMessage(msg);
         srv.disconnect();
      }
      catch (Exception e) {
         e.printStackTrace();
      }
      System.exit(0);
      */
   }
    
}
