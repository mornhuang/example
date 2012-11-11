/**
 *  Copyright (C) 2009 by Maryan Rachynskyy
 *  mrach@users.sourceforge.net
 *  
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.linuxorg.pcal.engine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.linuxorg.pcal.messages.Messages;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

public class EngineXMLCrypter {

	// Encryption algorithm name
	private static final String algName = "PBEWithMD5AndDES"; //$NON-NLS-1$
	// Salt
	private static final byte[] encryptionSalt = {
		(byte)0x4d, (byte)0x61, (byte)0x72, (byte)0x79,
		(byte)0x61, (byte)0x6e, (byte)0xee, (byte)0x99
	};
	// Iteration count
	private static final int encryptionCount = 20;

	/**
	 * This method encrypts the source XML document and wraps it into the 
	 * corresponding envelope 
	 * @param source the unencrypted XML document
	 * @param key a secret key to be used for encryption
	 * @return the encrypted XML document
	 * @throws IOException if any problem with the XML encryption will happen
	 */
	public static Document encryptXML(Document source, SecretKey key) throws IOException {

		Document result = null;
		try {
			result = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		} catch (ParserConfigurationException e) {
			throw new IOException(Messages.getString("EngineXMLCrypter.1")); //$NON-NLS-1$
		}

		// these are just standard XML encryption wrappers
		Element encData = result.createElement("EncryptedData"); //$NON-NLS-1$
		encData.setAttribute("xmlns","http://www.w3.org/2001/04/xmlenc#"); //$NON-NLS-1$ //$NON-NLS-2$
		encData.setAttribute("Type", "http://www.isi.edu/in-notes/iana/assignments/media-types/text/xml"); //$NON-NLS-1$ //$NON-NLS-2$
		encData.setAttribute("EncryptionMethod", algName); //$NON-NLS-1$
		Element cipherData = result.createElement("CipherData"); //$NON-NLS-1$
		encData.appendChild(cipherData);

		Element cipherValue = result.createElement("CipherValue"); //$NON-NLS-1$

		String encryptedData = encryptData(source, key);

		cipherValue.appendChild(result.createTextNode(encryptedData));

		cipherData.appendChild(cipherValue);		

		result.appendChild(encData);

		return result;
	}

	/**
	 * This method decrypts the source XML document and returns a document with the unencrypted content.
	 * The file format is lightly validated.
	 * @param source the encrypted XML document
	 * @param key a secret key to be used for decryption
	 * @return the decrypted XML document
	 * @throws IOException on any problems with the decryption
	 */
	public static Document decryptXML(Document source, SecretKey key) throws IOException {

		String encryptedString = null;

		NodeList nl = source.getElementsByTagName("EncryptedData"); //$NON-NLS-1$

		// There should be only one encrypted block
		if(nl.getLength() == 1) {
			NodeList cdl = nl.item(0).getChildNodes();
			if((cdl.getLength() == 1) && (cdl.item(0).getNodeName().equals("CipherData"))) { //$NON-NLS-1$
				NodeList cvl = cdl.item(0).getChildNodes();
				if((cvl.getLength() == 1) && (cvl.item(0).getNodeName().equals("CipherValue"))) { //$NON-NLS-1$
					NodeList cvlChild = cvl.item(0).getChildNodes(); 
					if(cvlChild.getLength() == 1) {						
						encryptedString = cvlChild.item(0).getNodeValue();
					} else {
						throw new IOException(Messages.getString("EngineXMLCrypter.7")); //$NON-NLS-1$
					}
				} else {
					throw new IOException(Messages.getString("EngineXMLCrypter.8")); //$NON-NLS-1$
				}				
			} else {
				throw new IOException(Messages.getString("EngineXMLCrypter.0")); //$NON-NLS-1$
			}
		} else {
			throw new IOException(Messages.getString("EngineXMLCrypter.10")); //$NON-NLS-1$
		}

		return decryptData(encryptedString, key);
	}

	/**
	 * Generate secret key based on the password
	 * @param password
	 * @return the secret key itself
	 * @throws IOException if any problem occurs
	 */
	public static SecretKey generateSecretKey(char[] password) throws IOException {
		SecretKey pbeKey = null;
		try {
			SecretKeyFactory keyFac = SecretKeyFactory.getInstance(algName);
			PBEKeySpec pbeKeySpec = new PBEKeySpec(password);
			pbeKey = keyFac.generateSecret(pbeKeySpec);
		} catch (NoSuchAlgorithmException e) {
			throw new IOException(Messages.getString("EngineXMLCrypter.11")); //$NON-NLS-1$
		} catch (InvalidKeySpecException e) {
			throw new IOException(Messages.getString("EngineXMLCrypter.12")); //$NON-NLS-1$
		}
		return pbeKey;
	}

	/*
	 * This method returns a base64-encoded string which contains the encrypted 
	 * source XML document.
	 */
	private static String encryptData(Document source, SecretKey key) throws IOException {

		Cipher cipher = getStreamCipher(Cipher.ENCRYPT_MODE, key);

		ByteArrayOutputStream ostream = new ByteArrayOutputStream();
		GZIPOutputStream gzf = new GZIPOutputStream(ostream);		

		OutputFormat formatter = new OutputFormat();
		formatter.setPreserveSpace(true);
		XMLSerializer serializer =
			new XMLSerializer(gzf, formatter);
		serializer.serialize(source);
		gzf.finish();
		gzf.close();

		byte[] ba;
		try {
			ba = cipher.doFinal(ostream.toByteArray());
		} catch (IllegalBlockSizeException e) {
			throw new IOException(Messages.getString("EngineXMLCrypter.13")); //$NON-NLS-1$
		} catch (BadPaddingException e) {
			throw new IOException(Messages.getString("EngineXMLCrypter.14")); //$NON-NLS-1$
		}

		return Base64.encodeBytes(ba);
	}

	/* 
	 * This method returns an XML document parsed from the encrypted data stored in the source string.
	 */
	private static Document decryptData(String source, SecretKey key) throws IOException {

		byte[] ba = Base64.decode(source);

		Cipher cipher = getStreamCipher(Cipher.DECRYPT_MODE, key);

		ByteArrayInputStream bais;
		try {
			bais = new ByteArrayInputStream(cipher.doFinal(ba));
		} catch (IllegalBlockSizeException e) {
			throw new IOException(Messages.getString("EngineXMLCrypter.13")); //$NON-NLS-1$
		} catch (BadPaddingException e) {
			throw new IOException(Messages.getString("EngineXMLCrypter.15")); //$NON-NLS-1$
		}

		GZIPInputStream f = new GZIPInputStream(bais);		

		Document doc = null;
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
		} catch (SAXException e) {
			throw new IOException(Messages.getString("EngineXMLCrypter.6")); //$NON-NLS-1$
		} catch (ParserConfigurationException e) {
			throw new IOException(Messages.getString("EngineXMLCrypter.6")); //$NON-NLS-1$
		}
		return doc;
	}

	/**
	 * Return a cipher instance to be used for loading and saving encrypted files
	 * @param opmod - one of Cipher.ENCRYPT_MODE or Cipher.DECRYPT_MODE
	 * @param pbeKey - a secret key for the cipher
	 * @return created cipher instance
	 * @throws IOException 
	 */
	private static Cipher getStreamCipher(int opmod, SecretKey pbeKey) throws IOException {
		PBEParameterSpec pbeParamSpec;
		Cipher pbeCipher = null;

		pbeParamSpec = new PBEParameterSpec(encryptionSalt, encryptionCount);

		try {			
			// Create PBE Cipher
			pbeCipher = Cipher.getInstance(algName);
			// Initialize PBE Cipher with key and parameters
			pbeCipher.init(opmod, pbeKey, pbeParamSpec);
		} catch (NoSuchAlgorithmException e) {
			throw new IOException(Messages.getString("EngineXMLCrypter.5"));			 //$NON-NLS-1$
		} catch (NoSuchPaddingException e) {
			throw new IOException(Messages.getString("EngineXMLCrypter.2")); //$NON-NLS-1$
		} catch (InvalidKeyException e) {
			throw new IOException(Messages.getString("EngineXMLCrypter.3")); //$NON-NLS-1$
		} catch (InvalidAlgorithmParameterException e) {
			throw new IOException(Messages.getString("EngineXMLCrypter.4")); //$NON-NLS-1$
		}
		return pbeCipher;
	}
}
