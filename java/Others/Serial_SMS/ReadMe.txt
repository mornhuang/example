
Simple SMS Client in Java for serial connection
***********************************************

(C) 2001 by Stefan Moscibroda




Purpose 
-------

This Java SMS Client aims to support the communication between a computer and a
mobile device via a serial connection. This serial connection can either be a
serial cable or a infrared connection (IRDA with virtual COM port). 

The tool allows sending, reading and writing of SMS in both transmit and
received format, as well as sending ordinary AT commands to the mobile. 



Installation (Windows)
----------------------

1.) If the communication has to take place via infrared ensure that there is
    "Virtual COM Port" support for the IRDA link. In Windows 2000 an additional
    driver is needed: "IrCOMM2k-1.2.0-eng.zip" 
    It can be downloaded at: http://www.ircomm2k.de/
 
2.) Copy Serial driver "win32com.dll" into "bin" folder of the JDK to be used.

3.) Set the java source path so that Serial Java API "comm.jar" can be found
    For more information refer to http://java.sun.com/products/javacomm/

4.) File "javax.comm.properties" File must be in same directory as "comm.jar"
    Class path has to be set accordingly

5.) Compile files *.java in package sms (javac)

6.) After starting the demo (java Sms_GUI), first set serial settings and chose
    the correct COM port (e.g. Virtual COM port on which IRDA link is mapped)


PS: The program should work also under Linux. However the entry in the 
    "javax.comm.properties" has to be changed as well as an other serial driver
    to be dowloaded. For more information refer to 

    http://www.interstice.com/~kevinh/linuxcomm.html



Functionality of the SMS tool / classes
---------------------------------------

- Sending and receiving SMS (also time information icluded)

- Writing and Reading SMS from Mobile/SIM storage 
  (support of both receiving and transmitting format)

- Direct support of AT command: 
  Can be used to e.g. read out Phone book entries, IMEI of the mobile, etc.

For the SMS functionality make sure that the mobile is set to SMS PDU mode.
To set the mobile to PDU mode, type the AT commad "AT+CMGF=0"



Usage of the SMS Tool
---------------------

1.) Set serial settings (menu)

2.) Connect Mobile via the corresponding serial connection
    (e.g. Serial cable, IRDA link)

3.) Click "Open Port" Button, click into Upper (Outgoing) Window

4.) Enter SMS command (strictly follow the syntax: sorry about the parser!)

	- SendSms <MSISDN>, <SmsContent>		: Send SMS to MSISDN number
    	- WriteTxSms <MSISDN>, <SmsContent>		: Write to Outgoing Folder
    	- WriteRxSms <MSISDN>, <SmsContent>		: Write to Incoming Folder
    	- ReadSms <index>                      	        : Read SMS from Storage Index
     	- DeleteSms <index>                      	: Delete SMS having Storage Index


    or enter any other AT-command (specified in 3GPP 07.07)

    	- eg. AT+CPBR=<index>   			: Read Phonebook entry (index)
          	AT+CMNI?           			: Show current SMS settings

    Refer to the attached public AT-command list of NOKIA for more information.
    ( File nokia_at.hlp )



Stefan Moscibroda, 2001



  	   