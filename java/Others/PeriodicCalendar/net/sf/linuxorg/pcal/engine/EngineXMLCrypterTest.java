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

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.crypto.SecretKey;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class EngineXMLCrypterTest {

	private final String sampleXML = "<test>some text</test>"; //$NON-NLS-1$
	private final String password = "something very secret here!!!"; //$NON-NLS-1$

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}


	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.EngineXMLCrypter#generateSecretKey(char[])}.
	 */
	@Test
	public final void testGenerateSecretKey() {
		SecretKey secretKey = null;
		try {
			secretKey = EngineXMLCrypter.generateSecretKey(password.toCharArray());
		} catch (IOException e) {
			fail(e.getMessage());
		}
		if(!secretKey.getFormat().equals("RAW") || !password.equals(new String(secretKey.getEncoded()))) { //$NON-NLS-1$
			fail("The key was not properly generated"); //$NON-NLS-1$
		}
	}	


	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.EngineXMLCrypter#encryptXML(org.w3c.dom.Document, javax.crypto.SecretKey)}.
	 */
	@Test
	public final void testEncryptXML() {

		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(sampleXML.getBytes());

			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);

			SecretKey secretKey = EngineXMLCrypter.generateSecretKey(password.toCharArray());

			Document encDoc = EngineXMLCrypter.encryptXML(doc, secretKey);

			//validate if the new document structure is proper
			NodeList nl = encDoc.getChildNodes();
			if((nl.getLength() == 1) && (nl.item(0).getNodeName().equals("EncryptedData"))) { //$NON-NLS-1$
				NodeList cdl = nl.item(0).getChildNodes();
				if((cdl.getLength() == 1) && (cdl.item(0).getNodeName().equals("CipherData"))) { //$NON-NLS-1$
					NodeList cvl = cdl.item(0).getChildNodes();
					if((cvl.getLength() == 1) && (cvl.item(0).getNodeName().equals("CipherValue"))) { //$NON-NLS-1$
						NodeList cvlChild = cvl.item(0).getChildNodes(); 
						if(cvlChild.getLength() == 1) {
							if(!cvlChild.item(0).getNodeValue().equals("WNx/XmdpEDprp9m1IRS2fFzdiqTAOMWSc1tRmOBezysRixv59u0gaW3oCHubEUpVx6ERGnXGUxo9J70eBk1e9lcnirfS7id5kH34RgoIdLU=")) { //$NON-NLS-1$
								fail("Incorrect encryption result"); //$NON-NLS-1$
							} 
						} else {
							fail("Structure check 4 failed."); //$NON-NLS-1$
						}
					} else {
						fail("Structure check 3 failed."); //$NON-NLS-1$
					}				
				} else {
					fail("Structure check 2 failed."); //$NON-NLS-1$
				}
			} else {
				fail("Structure check 1 failed."); //$NON-NLS-1$
			}


		} catch (SAXException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}		
	}

	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.EngineXMLCrypter#decryptXML(org.w3c.dom.Document, javax.crypto.SecretKey)}.
	 */
	@Test
	public final void testDecryptXML() {

		ByteArrayInputStream bais = new ByteArrayInputStream(sampleXML.getBytes());

		try {
			Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(bais);
			SecretKey secretKey = EngineXMLCrypter.generateSecretKey(password.toCharArray());

			Document encDoc = EngineXMLCrypter.encryptXML(doc, secretKey);

			Document resDoc = EngineXMLCrypter.decryptXML(encDoc, secretKey);
			
			NodeList nl = resDoc.getChildNodes();
			
			if(!nl.item(0).getNodeName().equals("test") || !nl.item(0).getTextContent().equals("some text")) { //$NON-NLS-1$ //$NON-NLS-2$
				fail("The decryption result do not match the source."); //$NON-NLS-1$
			}
			
		} catch (SAXException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			fail(e.getMessage());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
	}

}
