// Serial SMS Client for handling SMS communication with a mobile terminal
// (C) 2001 by Stefan Moscibroda

// class representing outgoing SMS

import java.util.*;

public class OutgoingSms {

    public final int SMS_MSG_ENCODING_7BIT = 0;

    private int smscAddressLength = 0x00;
    private int smscAddressType = 0x91;
    public String smscAddress;
    private String smscAddressEnc;
    private int smsSubmitCode = 0x11;
    private int tpMsgRef = 0;
    private int recipientAddressLength;
    private int recipientAddressType = 0x91;
    public String recipientAddress;
    private String recipientAddressEnc;
    public int tpPid;
    public int tpDcs;
    private int tpValidity = 0xaa;
    private int tpUdl; // message length.
    private String tpUd; // user message (as sent).
    public String msg; // user message (unencoded).

    private String pdu;

    public OutgoingSms(String recipientAddress, String smscAddress, String msg,
            int tpPid, int tpDcs) {
        this.recipientAddress = recipientAddress;
        this.smscAddress = smscAddress;
        this.msg = msg;
        this.tpPid = tpPid;
        this.tpDcs = tpDcs;
        StringBuffer sb = new StringBuffer(320);
        smscAddressLength = smscAddress.length();

        // Internal smsc Adress settings taken from mobile
        // if other add smscAddressType and smscAddressType to sb.

        if (smscAddressLength == 0) {

            sb.append("00"); // Optional, some mobile don't work if added

        }

        else {

            // add smscAddressType and smscAddressType to sb.
            sb.append(SmsPduCodec.toHexString(0x07));

            if ((smscAddressLength % 2) == 1)
                smscAddressEnc = SmsPduCodec.swapDigits(smscAddress + "F");
            else
                smscAddressEnc = SmsPduCodec.swapDigits(smscAddress);

            sb.append(SmsPduCodec.toHexString(smscAddressType));
            sb.append(smscAddressEnc);
        }

        sb.append(SmsPduCodec.toHexString(smsSubmitCode));
        sb.append(SmsPduCodec.toHexString(tpMsgRef));

        // recipientAddress
        recipientAddressLength = recipientAddress.length();
        if ((recipientAddress.length() % 2) == 1)
            recipientAddressEnc = SmsPduCodec
                    .swapDigits(recipientAddress + "F");
        else
            recipientAddressEnc = SmsPduCodec.swapDigits(recipientAddress);

        sb.append(SmsPduCodec.toHexString(recipientAddressLength));
        sb.append(SmsPduCodec.toHexString(recipientAddressType));
        sb.append(recipientAddressEnc);
        sb.append(SmsPduCodec.toHexString(tpPid));
        sb.append(SmsPduCodec.toHexString(tpDcs));
        sb.append(SmsPduCodec.toHexString(tpValidity));

        // encode message and calculate message length.
        if ((tpDcs & 4) == 0) {
            tpUd = SmsPduCodec.sevenBitEncode(msg);
            tpUdl = msg.length();
        } else {
            tpUd = msg;
            tpUdl = msg.length() / 2;
        }
        sb.append(SmsPduCodec.toHexString(tpUdl));
        sb.append(tpUd);
        pdu = sb.toString();
    }

    public String toString() {

        return pdu.toUpperCase();

    }

    public int length() {

        if ((pdu.substring(0, 2)).equals("00"))
            return (pdu.length() - 2) / 2;
        else
            return (pdu.length() - 16) / 2;
    }

    // Transforms an outgoing to an incoming pdu
    // contains no SMCS

    public void transform_to_received_SMS(Calendar time) {

        int length;
        length = pdu.length();
        // if no SMCS specified, otherwise adaption needed
        int offset1;
        String beginning;

        if ((pdu.substring(0, 2)).equals("00")) {
            // if transmit without smsc adress enter dummy some smsc address
            beginning = "07911497949900F0";
            offset1 = 6;
        } else {
            beginning = pdu.substring(0, 16);
            offset1 = 20;
        }

        String addressLenStr = pdu.substring(offset1, offset1 + 2);
        int senderAddressLength = Integer.parseInt(addressLenStr, 16);

        int offset2 = offset1 + 4 + senderAddressLength + senderAddressLength
                % 2;

        String pdu_r = beginning + "04" + pdu.substring(offset1, offset2 + 4);

        // If errors with timestamp just enter dummy time stamp
        // pdu_r=pdu_r+"00000000000000"+pdu.substring(offset2+6,length);

        pdu_r = pdu_r + SmsPduCodec.TimeStampEncode(time)
                + pdu.substring(offset2 + 6, length);

        pdu = pdu_r;
    }

}
