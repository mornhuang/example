Java Sms Api

Is a suite of Api designed to aid java developer
send and receive GSM phone SMS messages from their
Java Programs

Files:
readme.txt              : This one
gpl.txt                 : GPL license
ComputeSmsData.java     : java code
SerialToGsm.java        : java code

NOTE:
You MUST have installed Java COMM api in order to
made SMS api to work, check SUN microsystems
web site for download and installation

Actually I released not a lot of documentation, but
to Java Developers will result easy to use this
code

How to use JavaSmsApi

In your java code add this:
       
        //declare new objects and variables
       
        ComputeSmsData sms = new ComputeSmsData();
        SerialToGsm stg = new SerialToGsm("serial0");

        String retStr = new String("");
        String sss = new String();
        String alarmNumber = new String("+393351234567");   // a real phone number here

        ....


        // running code
       
        // check for messages
        retStr = stg.checkSms();
        if (retStr.indexOf("ERROR") == -1) {
            System.out.println("Phone # of sender: " + stg.readSmsSender());
            System.out.println("Recv'd SMS message: " + stg.readSms());
        }
       
        // send a message
        sss = stg.sendSms(alarmNumber,"Hello GSM World");
              

Copyright 2001 Marco Tozzini Java-System
E-Mail: marco.tozzini@java-system.com
Web:    www.java-system.com
