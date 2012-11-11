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

public class ComputeSmsData {
    
    private String telNum;
    private String telNumSMSC;
    private String telNumLen;
    private String telNumSMSCLen;
    private String pduTxt;
    private String pduTxtLen;

    private String rcvdPdu;
    private String rcvdSMSC;
    private String rcvdSenderNum;
    private String rcvdPduTxt;

    // *******************************************************************************************************
    //                              encode text message in 7bit GSM standard
    // *******************************************************************************************************
    private void txtToSmsPdu(String testo) {
        // first of all set the msg lenght
        pduTxtLen = Integer.toHexString(testo.length());
        
        if (pduTxtLen.length() < 2) {
            pduTxtLen = "0" + pduTxtLen;
        }
        pduTxtLen = pduTxtLen.toUpperCase();
        
//        System.out.println("testo da convertire <" + testo + ">");

        byte[] ccc = testo.getBytes(); // get ASCII(?) bytes value

        BitSet bs = new BitSet(testo.length() * 8);
        int l = 0;

        for (int i = 0; i < testo.length(); i++) {
            for (int subI = 0; subI < 7; subI++) {
                l = i * 7 + subI;
                if ((ccc[i] & (1 << subI)) != 0) {
                    bs.set(l);
                }
            }
        }
        l++;
//        System.out.println("Elle = " + l);

        int ll;
        
        // check if size fit a byte
        if (((l / 56) * 56) != l) { // 56 = 7*8 = M.C.M.
            ll = (l / 8) + 1;
        }
        else {
            ll = l / 8;
        }
		if (ll == 0) {
			ll++;
		}
  
//        System.out.println("Elle2 = " + ll);
  
        byte[] b = new byte[ll];
        for (int i = 0; i < ll; i++) {
            for (int subI = 0; subI < 8; subI++) {
                if ((l + 1) > (i * 8 + subI)) {  // should be less then last 1 in bs
                    if (bs.get(i * 8 + subI)) {
                        b[i] |= (byte)(1 << subI);
                    }
                }
            }
        }

        pduTxt = new String("");
       	for (int i = 0; i < ll; i++)
        {
            String str1 = Integer.toHexString((int)b[i]);   // convert # in string
            if (str1.length() < 2) {
                str1 = '0' + str1;
            }
            str1 = (str1.substring(str1.length()-2,str1.length()));
            pduTxt += str1.toUpperCase();
        }
        
        System.out.println("Stringa convertita   <" + pduTxt + ">");
        System.out.println("Stringa riconvertita <" + smsPduToTxt(pduTxt) + ">");
        
    }


    // ******************************
    // set text message (from extern)
    // ******************************
    public void setAsciiTxt(String s) {
        txtToSmsPdu(s);
    }


    // *********************************************************
    // Encode destination telephone number in GSM stadard format
    // *********************************************************
    // str MUST be in international format
    private String encodeTelNum(String str) {

        // remove trailing '+' if any
        if (str.charAt(0) == '+') {
            str = str.substring(1, str.length());
        }                          
        
        // check if num length is even or odd
        int l = str.length();
        if (((l / 2) * 2) != l) {
            str += 'F';
            l++;
        }
        
        String tmpStr = new String();
        // swap chars
        for (int cnt = 0; cnt < (l / 2); cnt++) {
            tmpStr += str.charAt(cnt * 2 + 1);
            tmpStr += str.charAt(cnt * 2);
        }

        return tmpStr;
    }

    // *********************************************************
    // Encode destination telephone number in GSM stadard format
    // *********************************************************
    // str MUST be in international format
    private String encodeTelNumLen(String str) {
        String numLen = new String();

        // remove trailing '+' if any
        if (str.charAt(0) == '+') {
            str = str.substring(1, str.length());
        }                          
        
        // check if num length is even or odd
        int l = str.length();
        numLen = new String(Integer.toHexString(l));
        if (numLen.length() < 2) {
            numLen = "0" + numLen.toUpperCase();
        }                               
        return numLen;
    }

    // **********************************
    // set telephone number (from extern)
    // **********************************
    // WARNING: telephone number must be in international format
    // with or without leading '+'
    public void setTelNum(String s) {
        telNum    = encodeTelNum(s);
        telNumLen = encodeTelNumLen(s);
    }

    public void setSMSCTelNum(String s) {
        telNumSMSC = encodeTelNum(s);
        telNumSMSCLen = encodeTelNumLen(s);
    }

    public String getCompletePduData () {
        String pduData = new String();
        pduData = "11";                 // first octet of SMS Submit
        pduData += "00";                // let telephone set msg reference
        pduData += telNumLen;           // tel # length
        pduData += "91";                // tel # is int'l format
        pduData += telNum;              // tel Num in GSM format
        pduData += "00";                // protocol identifier TP-PID
        pduData += "00";                // pdu data is encoded 7bit data
        pduData += "AA";                // TP validity period
        pduData += pduTxtLen;           // length of text data
        pduData += pduTxt;              // message encoded data
                         
        return pduData;                         
    }

    public String getSMSCPduData () {
        String pduData = new String();
        pduData = telNumSMSCLen;           // tel # length
        pduData += "91";                // tel # is int'l format
        pduData += telNumSMSC;              // tel Num in GSM format
                         
        return pduData;                         
    }


    // *********************************************************************************************************
    //                                         decode recv'd message
    // *********************************************************************************************************

    // ************************
    // set received Pdu from ME
    // ************************
    public void setRcvdPdu(String s) {                                                       
        rcvdPdu = s;
        computeRcvdPdu(rcvdPdu);
    }

    // **************************************
    // get received number of services center
    // **************************************
    public String getRcvdPduSMSC() {
        return rcvdSMSC;
    }

    // *****************
    // get sender number
    // *****************
    public String getRcvdSenderNumber() {
        return rcvdSenderNum;
    }

    // ********************
    // get received message
    // ********************
    public String getRcvdPduTxt() {
        return rcvdPduTxt;
    }

    // ***********************
    // decode rcvd Pdu message
    // ***********************
    private void computeRcvdPdu(String s) {               
        
        Integer lenOfSMSC = new Integer(Integer.parseInt(s.substring(0,2),16));

//        System.out.println("Len SMSC = " + lenOfSMSC.intValue());
//        System.out.println("SMSC info = " + s.substring(2,2+(lenOfSMSC.intValue() * 2)));
        
        // compute SMSC infos
        // ------------------
        rcvdSMSC = new String();
        rcvdSMSC = s.substring(2,4);    // add type of address (future evaluation?)
        rcvdSMSC += '.';                // add a separator
        // decode SMSC number and add to string
        rcvdSMSC += decodeTelNum(s.substring(4,4+((lenOfSMSC.intValue() - 1) * 2)));
//        System.out.println("dec'd SMSC info = " + rcvdSMSC);

        // remove part of message just evaluated                                                                                                 
        int lenOfRemovedTxt = 2 + (lenOfSMSC.intValue() * 2);
        s = s.substring(lenOfRemovedTxt , s.length());
        
        // remove first octet of SMS-DELIVER msg (future evaluation!)
        lenOfRemovedTxt = 2;
        s = s.substring(lenOfRemovedTxt , s.length());
//        System.out.println("Rimane da elaborare " + s);

        // evaluate sender address length
        Integer lenOfSenderNum = new Integer(Integer.parseInt(s.substring(0,2),16));

        rcvdSenderNum = new String("");
/*
		remove comments to this part if you need to know type of address
        rcvdSenderNum = s.substring(2,4);    // add type of address (future evaluation?)
        rcvdSenderNum += '.';                // add a separator
*/
        // decode SMSC number and add to string
//        System.out.println(lenOfSenderNum.intValue());
        int tmpLenOfSender = lenOfSenderNum.intValue();
        if ((tmpLenOfSender / 2 * 2) != tmpLenOfSender) {
            tmpLenOfSender++;
        }
        rcvdSenderNum += decodeTelNum(s.substring(4,4+tmpLenOfSender));
//        System.out.println("dec'd Sender info = " + rcvdSenderNum);

        // remove computed text        
        tmpLenOfSender += 4;
        s = s.substring(tmpLenOfSender , s.length());
        
        // remove TP-ID (2 octet) (future evaluation?)
        lenOfRemovedTxt = 2;
        s = s.substring(lenOfRemovedTxt , s.length());
        // remove TP-DCS (2 octet) (future evaluation?)        
        lenOfRemovedTxt = 2;
        s = s.substring(lenOfRemovedTxt , s.length());
        // remove Time stamp (14 octet) (future evaluation?)        
        lenOfRemovedTxt = 14;                                     
        s = s.substring(lenOfRemovedTxt , s.length());
        // remove length of pdu data
        lenOfRemovedTxt = 2;                                     
        s = s.substring(lenOfRemovedTxt , s.length());

        rcvdPduTxt = smsPduToTxt(s);
    }



    // *****************
    // decode tel number
    // *****************
    private String decodeTelNum(String s) {
        String decodedStr = new String();
                                        
        for (int i = 0; i < (s.length() / 2); i++) {
            decodedStr += s.charAt(i * 2 + 1);
            decodedStr += s.charAt(i * 2);    
        }                                
        
        if (decodedStr.charAt(decodedStr.length() - 1) == 'F') {
            decodedStr = decodedStr.substring(0,decodedStr.length()-1);
        }

        return decodedStr;
    }                                                                                                    
                                                                                                    
    // ********************************
    // decode pdu text from 7bit format
    // ********************************
    private String smsPduToTxt(String s) {
        byte[] bt = new byte[s.length() / 2];
        for (int i = 0; i < (s.length() / 2); i++) {
            bt[i] = (byte)(Integer.parseInt(s.substring(i*2,i*2+1),16) * 16);
            bt[i] += (byte)Integer.parseInt(s.substring(i*2+1,i*2+2),16);
        }

        BitSet bs = new BitSet(s.length() / 2 * 8);
        int l = 0;

        for (int i = 0; i < (s.length() / 2); i++) {
            for (int subI = 0; subI < 8; subI++) {
                l = i * 8 + subI;
                if ((bt[i] & (1 << subI)) != 0) {
                    bs.set(l);
                }
            }
        }
        l++;
        
//        System.out.println("bit totali (8) " + l);

        int ll;
        // now we have to find the real septets
		// this will miss 7 and 8 multiples (fixed at the
		// end of this method looking for byte == 0)
		// this is part A
		// look at part B (at the end of the method)
	   	ll = (l / 7);
		if (ll == 0) {
			ll++;
		}

//        System.out.println("bit totali (7) " + ll);
        
        byte[] b = new byte[ll];
        for (int i = 0; i < ll; i++) {
            for (int subI = 0; subI < 7; subI++) {
                if ((l + 1) > (i * 7 + subI)) {  // should be less then last 1 in bs
                    if (bs.get(i * 7 + subI)) {
                        b[i] |= (byte)(1 << subI);    
                    }
                }
            }
        }

		// this is part B
		// look at part A (at the middle of the method)
		if (b[ll-1] == 0) {
	        return (new String(b,0,ll-1));	// if last byte == 0 skip it
		}
		else {
        	return (new String(b));
        }
    }
}
