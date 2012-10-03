/*
 Java Sms Api
 Is a suite of Api designed to aid developer send and receive
 SMS messages from GSM wireless phone

 Copyright 2001 Marco Tozzini Java-System

 E-Mail: marco.tozzini@Java-System.com
 Web:	www.Java-System.com


 This file is part of JavaSmsApi.

 JavaSmsApi is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 JavaSmsApi is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with JavaSmsApi; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

import java.io.*;
import java.util.BitSet;
import javax.comm.*;
import java.lang.*;

public class SerialToGsm {

    InputStream in;
    OutputStream out;
    String lastIndexRead;
    String senderNum;
    String smsMsg;

    SerialToGsm(String porta) {
        try {
            CommPortIdentifier portId = CommPortIdentifier
                    .getPortIdentifier(porta);
            SerialPort sp = (SerialPort) portId.open("Sms_GSM", 0);
            sp.setSerialPortParams(9600, SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            sp.setFlowControlMode(sp.FLOWCONTROL_NONE);

            in = sp.getInputStream();
            out = sp.getOutputStream();

            /* modem reset */
            sendAndRecv("+++AT", 30);       // delay for 20 sec/10
            sendAndRecv("AT&F", 30);
            sendAndRecv("ATE0", 30);        // echo off
            sendAndRecv("AT +CMEE=1", 30);  // verbose error messages
            sendAndRecv("AT+CMGF=0", 70);   // set pdu mode
            // sendAndRecv("AT V1E0S0=0&D2&C1", 1000000);

        } catch (Exception e) {
            System.out.println("Exception " + e);
            System.exit(1);
        }
    }

    private String sendAndRecv(String s, int timeout) {
        try {
            /* clean serial port input buffer */
            in.skip(in.available());
            System.out.println("=> " + s);
            s = s + "\r"; // add CR
            out.write(s.getBytes());
            out.flush();

            String strIn = new String();
            for (int i = 0; i < timeout; i++) {
                int numChars = in.available();
                if (numChars > 0) {
                    byte[] bb = new byte[numChars];
                    in.read(bb, 0, numChars);
                    strIn += new String(bb);
                }
                // start exit conditions
                // ---------------------
                if (strIn.indexOf(">\r\n") != -1) {
                    break;
                }

                if (strIn.indexOf("OK\r\n") != -1) {
                    break;
                }

                if (strIn.indexOf("ERROR") != -1) { // if find 'error' wait for
                    // CR+LF
                    if (strIn.indexOf("\r\n", strIn.indexOf("ERROR") + 1) != -1) {
                        break;
                    }
                }

                Thread.sleep(100); // delay 1/10 sec
            }

            System.out.println("<= " + strIn);

            if (strIn.length() == 0) {
                return "ERROR: len 0";
            }

            return strIn;
        } catch (Exception e) {
            System.out.println("send e recv Exception " + e);
            return "ERROR: send e recv Exception";
        }
    }

    public String sendSms(String numToSend, String whatToSend) {
        ComputeSmsData sms = new ComputeSmsData();
        sms.setAsciiTxt(whatToSend);
        sms.setTelNum(numToSend);
        // sms.setSMSCTelNum("+393359609600"); // SC fixed
        String s = new String();
        s = sendAndRecv("AT+CMGS=" + (sms.getCompletePduData().length() / 2)
                + "\r", 30);
        // System.out.println("==> AT+CMGS=" +
        // (sms.getCompletePduData().length() / 2));
        // System.out.println("<== " + s);
        if (s.indexOf(">") != -1) {
            // s = sendAndRecv(sms.getSMSCPduData() + sms.getCompletePduData() +
            // "\u001A"); // usefull one day?
            // System.out.println("Inviero questo >>>> " +
            // sms.getCompletePduData());

            // if this sintax won't work try remove 00 prefix
            s = sendAndRecv("00" + sms.getCompletePduData() + "\u001A", 150);

            // System.out.println("<== " + s);
            return s;
        } else {
            return "ERROR";
        }
    }

    // used to reset message data
    private void resetGsmObj() {
        lastIndexRead = null;
        senderNum = null;
        smsMsg = null;
    }

    public String checkSms() {
        String str = new String();
        String strGsm = new String();
        strGsm = sendAndRecv("AT+CMGL=0", 30); // list unread msg and sign them
        // as read
        // if answer contain ERROR then ERROR
        if (strGsm.indexOf("ERROR") != -1) {
            resetGsmObj();
            return strGsm; // error
        }

        strGsm = sendAndRecv("AT+CMGL=1", 30); // list read msg
        // if answer contain ERROR then ERROR
        if (strGsm.indexOf("ERROR") != -1) {
            resetGsmObj();
            return strGsm; // error
        }

        // evaluate message index
        if (strGsm.indexOf(':') <= 0) {
            resetGsmObj();
            return ("ERROR unexpected answer");
        }

        str = strGsm.substring(strGsm.indexOf(':') + 1, strGsm.indexOf(','));
        str = str.trim(); // remove white spaces
        // System.out.println("Index: " + str);
        lastIndexRead = str;

        // find message string
        // -------------------
        // look for start point (search \r, then skip \n, add and one more for
        // right char
        int startPoint = strGsm.indexOf("\r", (strGsm.indexOf(":") + 1)) + 2;
        int endPoint = strGsm.indexOf("\r", startPoint + 1);
        if (endPoint == -1) {
            // only one message
            endPoint = strGsm.length();
        }

        // extract string
        str = strGsm.substring(startPoint, endPoint);
        System.out.println("String to be decoded :" + str);

        ComputeSmsData sms = new ComputeSmsData();
        sms.setRcvdPdu(str);
        // SMSCNum = new String(sms.getRcvdPduSMSC());
        senderNum = new String(sms.getRcvdSenderNumber());
        smsMsg = new String(sms.getRcvdPduTxt());

        System.out.println("SMSC number:   " + sms.getRcvdPduSMSC());
        System.out.println("Sender number: " + sms.getRcvdSenderNumber());
        System.out.println("Message: " + sms.getRcvdPduTxt());

        return "OK";
    }

    public String readSmsSender() {
        return senderNum;
    }

    public String readSms() {
        return smsMsg;
    }

    public String delSms() {
        if (lastIndexRead != "") {
            return sendAndRecv("AT+CMGD=" + lastIndexRead, 30);
        }
        return ("ERROR");
    }
}
