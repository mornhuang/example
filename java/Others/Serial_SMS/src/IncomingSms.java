// Serial SMS Client for handling SMS communication with a mobile terminal
// (C) 2001 by Stefan Moscibroda

// class representing incoming SMS



import java.util.*;

public class IncomingSms {
    public final int SMS_MSG_ENCODING_7BIT = 0;

    private int    smscAddressLength;
    private int    smscAddressType;
    private String smscAddress;
    private int    smsDeliverCode;
    private int    senderAddressLength;
    private int    senderAddressType;
    public String senderAddress;
    private int    tpPid;      // protocol identifier.
    private int    tpDcs;      // data coding scheme.
    private String tpScts;     // time stamp.
    private int    tpUdl;      // message length.
    private int    encMsgLen;
    private String tpUd;       // user message (as sent).
    public String msg;        // user message (decoded).
    public GregorianCalendar time;
    private String sms_format;

    public String getSenderNumber()
    {
	return senderAddress;
    }

    public String getMessage() {
	return msg;
    }

    public IncomingSms(String smspdu) throws PduParseException {
	int pdulen = smspdu.length();
	String tmpstr;
	int i = 0;

	try {
	    boolean hasSmscInfo = false;
            smspdu.toUpperCase();
	    String octet1str = smspdu.substring(i, 2);
	    int octet1 = Integer.parseInt(octet1str, 16);
	    i=i+2;
	    String octet2str = smspdu.substring(i, i+2);
	    int octet2 = Integer.parseInt(octet2str, 16);
	    i=i+2;

    	    if( octet1 > 0 ) {
		// SMSC info included in the PDU.

		// get SMSC address length.
		smscAddressLength = octet1;

		// get SMSC address type.
		smscAddressType = octet2;

		// get SMSC address string (minus type len).
		int smsAddrLastIndex = i + (smscAddressLength*2)-2;
		tmpstr = smspdu.substring(i, smsAddrLastIndex);
		smscAddress = SmsPduCodec.swapDigits(tmpstr);
		if( smscAddress.indexOf('F') != -1 ) // strip trailing F.
		    smscAddress = smscAddress.substring(0,
						smscAddress.length()-1);
		if((smscAddressType & 0xf0) == 0x90) // international format.
		    smscAddress = '+' + smscAddress;
		i=i+(smscAddressLength*2)-2;

		String smsDeliverStr = smspdu.substring(i, i+2);
		smsDeliverCode = Integer.parseInt(smsDeliverStr, 16);
		i=i+2;

                if (smsDeliverCode % 2 == 0) {
                  sms_format="incoming"; }
                else  {
                  sms_format="outgoing";
		  i=i+2; // +2 because additonal TP reference field in outgoing format
                }

		// get sender address length.
		String addressLenStr = smspdu.substring(i, i+2);
		senderAddressLength = Integer.parseInt(addressLenStr, 16);
                i=i+2;

	    } else {
		// no SMSC info in the PDU.
		smsDeliverCode = octet2;

                if (smsDeliverCode % 2 == 0) {
                  sms_format="incoming"; }
                else  {
                  sms_format="outgoing";
		  i=i+2; // +2 because additonal TP reference field in outgoing format
                }
                String addressLenStr = smspdu.substring(i, i+2);
                senderAddressLength = Integer.parseInt(addressLenStr, 16);
                i=i+2;
	    }

	    // get sender address type.
	    String addressTypeStr = smspdu.substring(i, i+2);
	    senderAddressType = Integer.parseInt(addressTypeStr, 16);
	    i=i+2;

	    // get sender address.
	    int senderLastIndex = i+senderAddressLength+senderAddressLength%2;
	    tmpstr = smspdu.substring(i, senderLastIndex);
	    senderAddress = SmsPduCodec.swapDigits(tmpstr);
	    senderAddress = senderAddress.substring(0, senderAddressLength);
	    if( (senderAddressType & 0xf0) == 0x90 ) // 1001xxxx?
		senderAddress = '+' + senderAddress;
	    i=i+senderAddressLength + senderAddressLength%2;

	    // get protocol id.
	    String protocolStr = smspdu.substring(i, i+2);
	    tpPid = Integer.parseInt(protocolStr, 16);

	    i=i+2;

	    // get data encoding scheme.
	    String dataEncStr = smspdu.substring(i, i+2);
	    tpDcs = Integer.parseInt(dataEncStr, 16);
	    i=i+2;

            if (sms_format.equals("incoming")) {
               // get timestamp.
               tpScts = smspdu.substring(i, i+7*2);
               time = new GregorianCalendar();
               time = SmsPduCodec.TimeStampDecode(tpScts);
               i=i+7*2;
            }

            if (sms_format.equals("outgoing")) {
               // Check different format for VPF field
               if ((smsDeliverCode-4) % 2 == 1) {
		  i=i+7*2; // +2 because additonal Validity field
                }
               else
               if ((smsDeliverCode-8) % 2 == 1) {
                  i=i+2;
               }
            }

	    // get message length.
	    String msgLenStr = smspdu.substring(i, i+2);
	    tpUdl = Integer.parseInt(msgLenStr, 16);
	    i=i+2;

	    // calculate encoded message length.
            // tpDcs defines wheter message is 7 bit or 8 bit coded
	    if ((tpDcs & 4) == 0) {
		encMsgLen = (tpUdl*7) / 8;
		if( ((tpUdl*7) % 8) != 0 )
		    encMsgLen++;
	    } else
		encMsgLen = tpUdl;

	    // get message string.
	    tpUd = smspdu.substring(i, i + encMsgLen*2);
            // decode depending on tpDcs settings (7 bit or 8 bit)
            if ((tpDcs & 4) == 0)
	      msg = SmsPduCodec.sevenBitDecode(tpUd, tpUdl);
            else
              msg = SmsPduCodec.eightBitDecode(tpUd);

	} catch (IndexOutOfBoundsException e) {
	    throw new PduParseException("SMS PDU too short: " + e);
	} catch (NumberFormatException e) {
	    throw new PduParseException("Hexadecimal number expected: " + e);
	}

        // To extract the actual data of the SMS generated by the SIM card applet
        int sim_data_offset = msg.indexOf("Update Record   ");
        if (sim_data_offset != -1)
          msg = msg.substring(sim_data_offset + 16, msg.length());

	if( (i + tpUd.length()) < pdulen )
	    throw new PduParseException("PDU too long");

    }


    public String toString() {
	StringBuffer sb = new StringBuffer(200);
	sb.append("\n");
	sb.append("\n smscAddress      = " + smscAddress);
	sb.append("\n smsDeliverCode   = 0x" +
		  SmsPduCodec.toHexString(smsDeliverCode));
	sb.append("\n senderAddress    = " + senderAddress);
	sb.append("\n tpPid            = 0x" +
		  SmsPduCodec.toHexString(tpPid));
	sb.append("\n tpDcs            = 0x" +
		  SmsPduCodec.toHexString(tpDcs));
        if (sms_format.equals("incoming")) {
        sb.append("\n Time/Date        = " + SmsPduCodec.displayTimeDate(time));
        }
	sb.append("\n msg              = '" + msg + "'");
        sb.append("\r\n");
	return new String(sb);
    }

}
