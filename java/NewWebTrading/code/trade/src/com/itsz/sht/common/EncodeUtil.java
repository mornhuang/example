package com.itsz.sht.common;

import java.net.URLEncoder;

/**
 * 
 * $Id: EncodeUtil.java,v 1.1 2010/11/09 03:57:25 kyzou Exp $
 * @Project:portal-head
 * @File:BaseUtil.java
 * @Description:
 * @Author:Cimenon Saint
 * @Date:2007-11-26
 */
public class EncodeUtil {

    private char S_BASE64CHAR[] = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
        'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 
        'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
        'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', 
        '8', '9', '+', '/'
    };
    
    private char S_BASE64PAD;
    private byte S_DECODETABLE[];

    public EncodeUtil() {
        S_BASE64PAD = '=';
        S_DECODETABLE = new byte[128];
        for(int i = 0; i < S_DECODETABLE.length; i++)
            S_DECODETABLE[i] = 127;

        for(int i = 0; i < S_BASE64CHAR.length; i++)
            S_DECODETABLE[S_BASE64CHAR[i]] = (byte)i;

    }

    public String encode(String account) {
        byte data[] = (account + '_' + String.valueOf(account != null ? System.currentTimeMillis() : 0L)).getBytes();
        String base64Encoded = encode(data, 0, data.length);
        return URLEncoder.encode(base64Encoded);
    }

    public String encode(byte data[], int off, int len) {
        if(len <= 0)
            return "";
        char out[] = new char[(len / 3) * 4 + 4];
        int rindex = off;
        int windex = 0;
        int rest;
        for(rest = len - off; rest >= 3; rest -= 3) {
            int i = ((data[rindex] & 0xff) << 16) + ((data[rindex + 1] & 0xff) << 8) + (data[rindex + 2] & 0xff);
            out[windex++] = S_BASE64CHAR[i >> 18];
            out[windex++] = S_BASE64CHAR[i >> 12 & 0x3f];
            out[windex++] = S_BASE64CHAR[i >> 6 & 0x3f];
            out[windex++] = S_BASE64CHAR[i & 0x3f];
            rindex += 3;
        }

        if(rest == 1) {
            int i = data[rindex] & 0xff;
            out[windex++] = S_BASE64CHAR[i >> 2];
            out[windex++] = S_BASE64CHAR[i << 4 & 0x3f];
            out[windex++] = S_BASE64PAD;
            out[windex++] = S_BASE64PAD;
        } else if(rest == 2) {
            int i = ((data[rindex] & 0xff) << 8) + (data[rindex + 1] & 0xff);
            out[windex++] = S_BASE64CHAR[i >> 10];
            out[windex++] = S_BASE64CHAR[i >> 4 & 0x3f];
            out[windex++] = S_BASE64CHAR[i << 2 & 0x3f];
            out[windex++] = S_BASE64PAD;
        }
        return new String(out, 0, windex);
    }
}
