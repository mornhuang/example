
package com.manning.ajaxinaction.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;

/**
 * class for encrypting private-public key pairs using MD5
 * 
 * @author dave
 */
public class Encrypter {

	private String pubKey=null;
	private String privKey=null;
	private MessageDigest digest=null;
	
	/**
	 * @param pubKey
	 * @param privKey
	 * @throws NoSuchAlgorithmException
	 */
	public Encrypter(String pubKey, String privKey) throws NoSuchAlgorithmException {
		super();
		this.pubKey = pubKey;
		this.privKey = privKey;
		digest=MessageDigest.getInstance("MD5");
	}
	
	public String getEncryptedValue(){
	  byte[] data=privKey.getBytes();
	  digest.update(data);
	  byte[] raw=digest.digest(pubKey.getBytes());
	  byte[] b64=Base64.encodeBase64(raw);
	  return new String(b64);
	}

	public boolean compare(String string){
	  return string.equals(getEncryptedValue());
	}
	/**
	 * @return Returns the pubKey.
	 */
	public String getPubKey() {
		return pubKey;
	}

}
