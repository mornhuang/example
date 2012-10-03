/**
 *  Copyright (C) 2009 by Maryan Rachynsky
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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.Vector;
import java.util.prefs.Preferences;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.crypto.SecretKey;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import net.sf.linuxorg.pcal.PCalDayInfo;
import net.sf.linuxorg.pcal.PCalPeriodInfo;
import net.sf.linuxorg.pcal.PCalendar;
import net.sf.linuxorg.pcal.messages.Messages;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

/**
 * @author Maryan Rachynskyy
 *
 */

public class Engine {

	/**
	 * Engine Preferences is just a container for the Engine-related preferences
	 *
	 */
	public class EnginePreferences {
		public int bufferDays;
		public int badFeelDaysBefore;
		public int badFeelDaysAfter;
		public int regularMinLength;
		public int regularMaxLength;
	}

	public class PasswordRequiredException extends Exception {
		private static final long serialVersionUID = 1L;
	};

	private EnginePreferences enginePreferences = new EnginePreferences();

	private static final String FILE_VERSION = "2.0"; //$NON-NLS-1$
	private static final int FILE_TYPE_PLAIN = 0;
	private static final int FILE_TYPE_ENCRYPTED = 1;
	private static final int FILE_TYPE_UNKNOWN = -1;
	private static final SimpleDateFormat date_format = new SimpleDateFormat("yyyy/MM/dd"); //$NON-NLS-1$

	private static final Integer PCAL_TYPE_MENS = 1;
	private static final Integer PCAL_TYPE_PREGNANCY = 2;
	private static final Integer PCAL_TYPE_BIRTH = 3;
	private static final Integer PCAL_TYPE_PREGNANCY_INT = 4;

	private static final int DEFAULT_BUFFER_DAYS = 0;
	private static final int DEFAULT_BAD_FEEL_DAYS_BEFORE = 0;
	private static final int DEFAULT_BAD_FEEL_DAYS_AFTER = 0;
	private static final int DEFAULT_REGULAR_MIN_LENGTH = 24;
	private static final int DEFAULT_REGULAR_MAX_LENGTH = 35;
	private static final int REGULAR_PREGNANCY_DAYS = 280; /* 40 weeks * 7 */
	private static final int MAX_PREGNANCY_DAYS = 308; /* 44 weeks * 7 */

	private static final int RELIABLE_LENGTH_DIFF = 2;
	private static final int RELIABLE_MAX_AVG_LENGTH = 33;
	private static final int RELIABLE_MIN_AVG_LENGTH = 25;
	private static final int RELIABLE_PERIODS_COUNT = 5;
	private static final int UNRELIABLE_MAX_AVG_LENGTH = 35;
	private static final int UNRELIABLE_MIN_AVG_LENGTH = 24;
	private static final int UNRELIABLE_LENGTH_DIFF = 3;


	private TreeSet<Date> startDates = new TreeSet<Date>(); /// the calendar events index
	private HashMap<Date, Integer> dateTypes = new HashMap<Date, Integer>(); /// the types of the calendar events listed in start_dates
	private HashMap<Date, String> notesContainer = new HashMap<Date, String>(); /// the notes container

	private boolean modified = false; /// true if any changes were done since last save or load the statistics data
	private int periodsCount = 0; /// regular periods count
	private int avgLength = 0;
	private int minLength = 0;
	private int maxLength = 0;
	private int calMethodAccuracy = 3; /// 0 - high, 1 - moderate, 2 - low, 3 - no data 

	private File workFile = null;
	private SecretKey workFileKey = null;

	public Engine() {
		Preferences enginePrefsNode = PCalendar.settings.node("Engine"); //$NON-NLS-1$
		enginePreferences.bufferDays = enginePrefsNode.getInt("Buffer Days", DEFAULT_BUFFER_DAYS); //$NON-NLS-1$
		enginePreferences.badFeelDaysBefore = enginePrefsNode.getInt("Bad Feels Before", DEFAULT_BAD_FEEL_DAYS_BEFORE); //$NON-NLS-1$
		enginePreferences.badFeelDaysAfter = enginePrefsNode.getInt("Bad Feels After", DEFAULT_BAD_FEEL_DAYS_AFTER); //$NON-NLS-1$
		enginePreferences.regularMinLength = enginePrefsNode.getInt("Reg. Min Length", DEFAULT_REGULAR_MIN_LENGTH); //$NON-NLS-1$
		enginePreferences.regularMaxLength = enginePrefsNode.getInt("Reg. Max Length", DEFAULT_REGULAR_MAX_LENGTH); //$NON-NLS-1$
		recalculate();
	}

	/**
	 * This is the main method of the entire application. The calendar day color and properties are determined here
	 * @param date - the date being analyzed
	 * @return date information or NULL if no data available
	 */
	public PCalDayInfo getDayInfo(final Date date)
	{
		if(startDates.isEmpty()) {
			// no data at all
			return null;
		} else { 
			PCalDayInfo info = null;

			Integer rectype = dateTypes.get(date);
			if(rectype != null) {
				// this date is really in the database as a day 1
				info = getDay1Info(rectype);
			}
			else
				if(date.before(startDates.first()))	{
					// the requested date is before the recorded periods
					return null;
				} else {
					Date last_date = startDates.last();
					// is the date in the recorded period?
					if(date.before(last_date)) {
						info = getDayInfoRecorded(date); 
					} else {
						info = getDayInfoEstimated(date, last_date); 
					}
				}
			return info;
		}
	}

	/**
	 * This inner method evaluated the date information for the dates beyond the recorded periods 
	 * @param date - the date being analyzed
	 * @param last_date - the last recorded date
	 * @return the day info record or NULL if no data available
	 */
	private PCalDayInfo getDayInfoEstimated(final Date date, Date last_date) {
		PCalDayInfo info = new PCalDayInfo();
		// the day parameters should be predicted
		info.day_num = dateDiff(last_date, date);
		int last_date_type = dateTypes.get(last_date);
		if(last_date_type == PCAL_TYPE_MENS) {

			if(periodsCount == 0) {
				// There is nothing we can say about this day
				return null;
			}

			// the cycle number is calculated by the average cycle length but
			// the fertility will be 
			if(avgLength!=0)
				info.day_num %= avgLength;

			if(info.day_num == 0) {
				// the most probably this will be a day 1
				info.fertile = false;
				info.badFeel = true;
			} else {
				// this is a day after the last recorded period start
				info.fertile = (info.day_num > (minLength - 18 - enginePreferences.bufferDays)) 
				&& (info.day_num < (maxLength - 11 + enginePreferences.bufferDays));
				info.badFeel = (info.day_num<=enginePreferences.badFeelDaysAfter)||
				((avgLength-info.day_num)<=enginePreferences.badFeelDaysBefore);
			}
			info.pregnancy = false;
			info.birth = false;			
		} else 
			if(last_date_type == PCAL_TYPE_PREGNANCY) {
				if(info.day_num < REGULAR_PREGNANCY_DAYS) {
					info.fertile = false;
					info.pregnancy = true;
					info.birth = false;
					info.badFeel = true;
				} else
					if(info.day_num == REGULAR_PREGNANCY_DAYS) {
						info.fertile = false;
						info.pregnancy = false;
						info.birth = true;
						info.badFeel = true;
					} else {
						// this is a day after the normal pregnancy period
						// know nothing
						info.day_num = -1;
					}
			} else {
				// know nothing
				info.day_num = -1;
			}
		info.estimate = true;
		info.pregnancy_interruption = false; // this is unpredictable by the computer
		return info;
	}


	/**
	 * This inner method evaluated the date information for the dates within recorded periods
	 * @param date - the date being analyzed
	 * @return the day info record or NULL if no data available
	 */
	private PCalDayInfo getDayInfoRecorded(final Date date) {
		PCalDayInfo info = new PCalDayInfo();
		int days_between;

		// find the starting date of the date's period
		Date p = startDates.floor(date);							
		if(p!=null) {                
			// this is a date in the complete period
			// this is not a day 1 - we have already checked this
			int date_type = dateTypes.get(p);

			if((date_type == PCAL_TYPE_MENS) && (periodsCount == 0)) {
				// There is nothing we can say about this day
				return null;
			}

			info.day_num = dateDiff(date, p);
			int period_length = dateDiff(p, startDates.higher(p));
			if(date_type == PCAL_TYPE_MENS) {
				// check if this is a regular period
				if((period_length < enginePreferences.regularMinLength) ||
						(period_length > enginePreferences.regularMaxLength)) {
					info.day_num = -1;
				} else {
					days_between = period_length - info.day_num;  
					info.fertile = (days_between < 18) && (days_between > 11);
					info.badFeel = (info.day_num<=enginePreferences.badFeelDaysAfter)||(days_between<=enginePreferences.badFeelDaysBefore);
				}
				info.pregnancy = false;
				info.birth = false;				
			} else 
				if(date_type == PCAL_TYPE_PREGNANCY) {
					Date nextDate = startDates.higher(p);
					if(((dateTypes.get(nextDate) == PCAL_TYPE_BIRTH) || 
							(dateTypes.get(nextDate) == PCAL_TYPE_PREGNANCY_INT))
							&& (info.day_num <= MAX_PREGNANCY_DAYS)) {
						// the pregnancy maybe took longer but still completion was recorded
						info.fertile = false;
						info.pregnancy = true;
						info.birth = false;
						info.badFeel = true;
					} else
						if(info.day_num <= REGULAR_PREGNANCY_DAYS) {
							//pregnancy completion was not recorded - actually this is the prediction
							info.fertile = false;
							info.pregnancy = true;
							info.birth = false;
							info.badFeel = true;
						} else {
							// this is a day after the normal pregnancy period
							// know nothing
							info.day_num = -1;
						}
				} else {
					// know nothing
					info.day_num = -1;
				}
		} else {
			// something wrong - this should normally never happen
			info.day_num = -1;
		}
		info.pregnancy_interruption = false;
		info.estimate = false;
		return info;
	}

	/**
	 * Get the day info record for the recorded day 1 of either cycle or pregnancy
	 * @param rectype - the recorded day type
	 * @return the day information record
	 */
	private static PCalDayInfo getDay1Info(Integer rectype) {
		PCalDayInfo info = new PCalDayInfo();
		info.day_num = 0;
		info.fertile = false;
		info.pregnancy = (rectype == PCAL_TYPE_PREGNANCY);
		info.birth = (rectype == PCAL_TYPE_BIRTH);
		info.pregnancy_interruption = (rectype == PCAL_TYPE_PREGNANCY_INT);
		info.badFeel = true;
		info.estimate = false;
		return info;
	}

	/**
	 * Load the data from file. If the file was opened with a password, Engine will store
	 * the password internally so transparent "Save" operation will be performed without
	 * re-requesting the password entry.
	 * @param file - a file to be loaded
	 * @param password - a password to be used for the file. 
	 * If the file appears to be unencrypted, this parameter is ignored. 
	 * @throws SAXException - file content format is unparsable
	 * @throws IOException - can not read a file specified
	 * @throws ParserConfigurationException - can not set up the file parser - internal error
	 * @throws PasswordRequiredException is thrown if password parameter is empty but the file is encrypted 
	 */
	public void loadFromFile(final File file, char[] password) 
	throws SAXException, IOException, ParserConfigurationException, PasswordRequiredException
	{
		FileInputStream fileStream = new FileInputStream(file);
		GZIPInputStream f;
		SecretKey secretKey = null;

		fileStream = new FileInputStream(file);
		f = new GZIPInputStream(fileStream);

		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);

		int docType = getDocumentType(doc);

		switch(docType) {
		case FILE_TYPE_UNKNOWN:
			throw new SAXException(Messages.getString("Engine.0")); //$NON-NLS-1$
		case FILE_TYPE_ENCRYPTED: {
			if((password == null) || (password.length==0)) {
				throw new PasswordRequiredException();
			}
			secretKey = EngineXMLCrypter.generateSecretKey(password);
			doc = EngineXMLCrypter.decryptXML(doc, secretKey);

			//check if the encryption container contains a regular plain file
			int internalDocType = getDocumentType(doc);
			if(internalDocType != FILE_TYPE_PLAIN) {
				throw new SAXException(Messages.getString("Engine.0")); //$NON-NLS-1$
			}
		}
		case FILE_TYPE_PLAIN:
			// actually - nothing to do here. The file is ready for parsing.
		}

		//reinitialize the containers
		clear();

		loadFromFileMens(doc);
		loadFromFilePregnancy(doc);
		loadFromFileBirth(doc);
		loadFromFilePregInt(doc);
		loadFromFileNotes(doc);

		// store the password if applicable
		if(docType == FILE_TYPE_ENCRYPTED) {
			workFileKey = secretKey; 
		}

		setWorkFile(file);
		modified = false;
		recalculate();
	}

	/**
	 * loadFromFile helper
	 * @param doc
	 * @throws SAXException
	 */
	private void loadFromFileNotes(Document doc)
	throws SAXException {
		NodeList nl;
		Date date;
		String text;
		nl = doc.getElementsByTagName("note"); //$NON-NLS-1$
		for(int i = 0; i<nl.getLength();i++) {
			NodeList nmp = nl.item(i).getChildNodes();
			date = null;
			text = null;
			for(int j=0;j<nmp.getLength();j++) {
				Node tmpNode = nmp.item(j);
				if(tmpNode.getNodeName().equals("date")) { //$NON-NLS-1$
					String tmpText = tmpNode.getTextContent();			
					if(tmpText == null) {
						throw new SAXException(Messages.getString("Engine.1")); //$NON-NLS-1$
					}
					try {
						date = date_format.parse(tmpText);
					} catch (ParseException e) {
						throw new SAXException(Messages.getString("Engine.2")+text); //$NON-NLS-1$
					}

				} else 
					if(tmpNode.getNodeName().equals("text")) { //$NON-NLS-1$
						text = tmpNode.getTextContent();
					}
			}
			if((text == null) || (date == null)) {
				throw new SAXException(Messages.getString("Engine.1")); //$NON-NLS-1$
			}
			notesContainer.put(date, text);
		}
	}

	/**
	 * loadFromFile helper
	 * @param doc
	 * @throws SAXException
	 */
	private void loadFromFilePregInt(Document doc) throws SAXException {
		NodeList nl;
		String text;
		Date date;
		nl = doc.getElementsByTagName("pregnancyint"); //$NON-NLS-1$
		for(int i = 0; i<nl.getLength();i++) {
			text = nl.item(i).getTextContent();
			if(text == null) {
				throw new SAXException(Messages.getString("Engine.1")); //$NON-NLS-1$
			}
			try {
				date = date_format.parse(text);
			} catch (ParseException e) {
				throw new SAXException(Messages.getString("Engine.2")+text); //$NON-NLS-1$
			}
			startDates.add(date);
			dateTypes.put(date, PCAL_TYPE_PREGNANCY_INT);
		}
	}

	/**
	 * loadFromFile helper
	 * @param doc
	 * @throws SAXException
	 */
	private void loadFromFileBirth(Document doc) throws SAXException {
		NodeList nl;
		String text;
		Date date;
		nl = doc.getElementsByTagName("birth"); //$NON-NLS-1$
		for(int i = 0; i<nl.getLength();i++) {
			text = nl.item(i).getTextContent();
			if(text == null) {
				throw new SAXException(Messages.getString("Engine.1")); //$NON-NLS-1$
			}
			try {
				date = date_format.parse(text);
			} catch (ParseException e) {
				throw new SAXException(Messages.getString("Engine.2")+text); //$NON-NLS-1$
			}
			startDates.add(date);
			dateTypes.put(date, PCAL_TYPE_BIRTH);
		}
	}

	/**
	 * loadFromFile helper
	 * @param doc
	 * @return
	 * @throws SAXException
	 */
	private void loadFromFilePregnancy(Document doc) throws SAXException {
		NodeList nl;
		String text;
		Date date;
		nl = doc.getElementsByTagName("pregnancy"); //$NON-NLS-1$
		for(int i = 0; i<nl.getLength();i++) {
			text = nl.item(i).getTextContent();
			if(text == null) {
				throw new SAXException(Messages.getString("Engine.1")); //$NON-NLS-1$
			}
			try {
				date = date_format.parse(text);
			} catch (ParseException e) {
				throw new SAXException(Messages.getString("Engine.2")+text); //$NON-NLS-1$
			}
			startDates.add(date);
			dateTypes.put(date, PCAL_TYPE_PREGNANCY);
		}
	}

	/**
	 * loadFromFile helper
	 * @param doc
	 * @return
	 * @throws SAXException
	 */
	private void loadFromFileMens(Document doc) throws SAXException {
		NodeList nl;
		String text;
		Date date;
		nl = doc.getElementsByTagName("mens"); //$NON-NLS-1$
		for(int i = 0; i<nl.getLength();i++) {
			text = nl.item(i).getTextContent();
			if(text == null) {
				throw new SAXException(Messages.getString("Engine.1")); //$NON-NLS-1$
			}
			try {
				date = date_format.parse(text);
			} catch (ParseException e) {
				throw new SAXException(Messages.getString("Engine.2")+text); //$NON-NLS-1$
			}
			startDates.add(date);
			dateTypes.put(date, PCAL_TYPE_MENS);
		}
	}

	/**
	 * Helper method for the loadFromFile
	 * @param doc - the XML document to be analyzed
	 * @return FILE_TYPE_PLAIN if the document is proper plain not encrypted one, 
	 * FILE_TYPE_ENCRYPTED if the document is proper encrypted one, 
	 * FILE_TYPE_UNKNOWN if the document is improper or have wrong version
	 */
	private int getDocumentType(Document doc)  {

		int fileType = FILE_TYPE_UNKNOWN;

		NodeList nl = doc.getElementsByTagName("EncryptedData"); //$NON-NLS-1$
		if(nl.getLength() == 1) {
			fileType = FILE_TYPE_ENCRYPTED;
		} else {
			// check for the root tag first
			Node n = doc.getFirstChild();
			if(n.getNodeName().equals("pcalendar")) { //$NON-NLS-1$
				nl = doc.getElementsByTagName("version"); //$NON-NLS-1$
				if(nl.getLength() == 1) {
					String file_version = nl.item(0).getTextContent();
					if(file_version != null) {
						if(file_version.equals(FILE_VERSION)) {
							fileType = FILE_TYPE_PLAIN;
						}				
					}
				}
			}
		}

		return fileType;
	}

	/**
	 * Save the current data to the file specified.
	 * @param file - a file reference
	 * @param password - a password to be applied to the file. If null - 
	 * the file will be saved with the original password (or unencrypted as applicable).
	 * If empty string - the password will be removed and the file will be unencrypted. 
	 * @return true on success
	 * @throws ParserConfigurationException
	 * @throws IOException
	 */
	public void saveToFile(final File file, final char[] password) throws ParserConfigurationException, IOException
	{
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();

		Element parent_element = doc.createElement("pcalendar"); //$NON-NLS-1$
		doc.appendChild(parent_element);

		Element element = doc.createElement("version"); //$NON-NLS-1$
		element.appendChild(doc.createTextNode(FILE_VERSION));
		parent_element.appendChild(element);

		element = doc.createElement("data"); //$NON-NLS-1$
		parent_element.appendChild(element);
		parent_element = element;

		saveToFileDates(doc, parent_element);
		saveToFileNotes(doc, parent_element);

		SecretKey secretKey = null;

		if(password != null) {
			// new password was specified
			if(password.length > 0) {
				secretKey = EngineXMLCrypter.generateSecretKey(password);
			}
		} else {
			secretKey = workFileKey;
		}

		if(secretKey != null) {
			doc = EngineXMLCrypter.encryptXML(doc, secretKey);
		}

		GZIPOutputStream zippedFile;

		FileOutputStream fileStream = new FileOutputStream(file);		

		zippedFile =  new GZIPOutputStream(fileStream);

		OutputFormat formatter = new OutputFormat();
		formatter.setPreserveSpace(true);
		XMLSerializer serializer =
			new XMLSerializer(zippedFile, formatter);
		serializer.serialize(doc);
		zippedFile.finish();

		setWorkFile(file);
		workFileKey = secretKey;
		modified = false;
	}


	/**
	 * A short wrapper for the main saveToFile method. Null password is used.
	 * @param file
	 * @throws ParserConfigurationException
	 * @throws IOException
	 */
	public void saveToFile(final File file) throws ParserConfigurationException, IOException {
		saveToFile(file, null);
	}


	/**
	 * saveToFile helper
	 * @param doc
	 * @param parent_element
	 */
	private void saveToFileNotes(Document doc, Element parent_element) {
		Element element;
		for(Date d: notesContainer.keySet()) {
			element = doc.createElement("note"); //$NON-NLS-1$
			Element e1 =doc.createElement("date"); //$NON-NLS-1$
			e1.appendChild(doc.createTextNode(date_format.format(d))); //$NON-NLS-1$
			element.appendChild(e1);

			e1 =doc.createElement("text"); //$NON-NLS-1$
			e1.appendChild(doc.createTextNode(notesContainer.get(d)));
			element.appendChild(e1);

			parent_element.appendChild(element);
		}
	}

	/**
	 * saveToFile helper
	 * @param doc
	 * @param parent_element
	 */
	private void saveToFileDates(Document doc, Element parent_element) {
		Element element;
		for(Date d: startDates) {
			int type = dateTypes.get(d); 

			// This is assumed "normal" behavior
			if(type == PCAL_TYPE_MENS) {
				element = doc.createElement("mens"); //$NON-NLS-1$
			} else 
				if(type == PCAL_TYPE_PREGNANCY) {
					element = doc.createElement("pregnancy"); //$NON-NLS-1$
				} else
					if(type == PCAL_TYPE_BIRTH) {
						element = doc.createElement("birth");  //$NON-NLS-1$
					} else			
						if(type == PCAL_TYPE_PREGNANCY_INT) {
							element = doc.createElement("pregnancyint");  //$NON-NLS-1$
						} else {
							continue;
						}

			element.appendChild(doc.createTextNode(date_format.format(d))); //$NON-NLS-1$
			parent_element.appendChild(element);
		}
	}

	/**
	 * Import list of menstruation dates from a plain text file (Lorg 1.x file version)
	 * @param fname name of the file
	 * @return true on success
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public boolean importFromFile(final File file) throws IOException, ParseException
	{
		BufferedReader inputStream = null;

		try {
			inputStream = 
				new BufferedReader(new FileReader(file));
			String l;
			while ((l = inputStream.readLine()) != null) {
				Date d = date_format.parse(l);
				startDates.add(d);
				dateTypes.put(d, PCAL_TYPE_MENS);
			}
		}
		finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}

		modified = true;
		recalculate();
		return true;
	}

	/**
	 * Export list of menstruation dates to a plain text file (Lorg 1.x file version)
	 * @param fname name of the file
	 * @return true on success
	 * @throws IOException 
	 */
	public void exportToFile(final File file) throws IOException
	{
		PrintWriter outputStream = null;

		try {
			outputStream = 
				new PrintWriter(new FileWriter(file));

			for(Date d : startDates) {
				if(dateTypes.get(d) == PCAL_TYPE_MENS) {
					outputStream.println(date_format.format(d)); //$NON-NLS-1$
				}
			}
		}
		finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	public void addStartDate(final Date date)
	{
		startDates.add(date);
		dateTypes.put(date, PCAL_TYPE_MENS);
		modified = true;
		recalculate();
	}
	public void addPregnancyDate(final Date date)
	{
		startDates.add(date);
		dateTypes.put(date, PCAL_TYPE_PREGNANCY);
		modified = true;
		recalculate();
	}

	public void addPregnancyInterruptDate(final Date date)
	{
		startDates.add(date);
		dateTypes.put(date, PCAL_TYPE_PREGNANCY_INT);
		modified = true;
		recalculate();
	}

	public void addBirthDate(final Date date)
	{
		startDates.add(date);
		dateTypes.put(date, PCAL_TYPE_BIRTH);
		modified = true;
		recalculate();
	}

	public void removeDateRecord(final Date date)
	{
		startDates.remove(date);
		dateTypes.remove(date);
		modified = true;
		recalculate();
	}

	public void clear()
	{
		startDates.clear();
		dateTypes.clear();
		notesContainer.clear();
		modified = false;
		setWorkFile(null);
		workFileKey = null;
		recalculate();
	}

	/**
	 * This function is called each time the statistics needs to be recalculated
	 */
	private void recalculate()
	{
		// at this point we have the sorted starting dates list
		periodsCount = 0;
		avgLength = 0;
		minLength = 0;
		maxLength = 0;
		int sumLength = 0;
		calMethodAccuracy = 3;
		if(startDates.size()<2) {
			// there are no even a single period
			return;
		}

		Date prevdate = null;

		// we need to calculate the average, min and max period length
		for(Date pstart: startDates)
		{
			if(prevdate == null) { 
				prevdate = pstart;
				continue;
			}
			// skip the non-menstruation neighbor records
			if((dateTypes.get(pstart) != PCAL_TYPE_MENS)||
					(dateTypes.get(prevdate) != PCAL_TYPE_MENS)) {
				prevdate = pstart;
				continue;
			}
			// pstart and pend points to starting and ending date of the period
			int p_length = dateDiff(prevdate, pstart);

			if((p_length < enginePreferences.regularMinLength) ||
					(p_length > enginePreferences.regularMaxLength)) {
				// bad period should not be taken into account

			} else {
				periodsCount++;
				if(p_length>maxLength) maxLength = p_length;
				if((p_length<minLength) || minLength == 0) minLength = p_length;
				sumLength+=p_length;        
			}
			prevdate = pstart;
		}
		if(periodsCount>0) avgLength = Math.round(sumLength/periodsCount);

		// calendar method accuracy depends on the medium period length,
		// the difference between the longest and the shortest periodsCount
		// and the number of the recorded periods
		// 0 - high, 1 - moderate, 2 - low
		// for the high accuracy the conditions are almost unreal
		if((periodsCount>RELIABLE_PERIODS_COUNT) &&
				(avgLength>RELIABLE_MIN_AVG_LENGTH) &&
				(avgLength<RELIABLE_MAX_AVG_LENGTH) &&
				((maxLength - minLength)<RELIABLE_LENGTH_DIFF))
			calMethodAccuracy = 0;
		else
			if((periodsCount<RELIABLE_PERIODS_COUNT)) calMethodAccuracy = 2;
			else
				if((maxLength - minLength)>UNRELIABLE_LENGTH_DIFF) calMethodAccuracy = 2;
				else
					if((avgLength<UNRELIABLE_MIN_AVG_LENGTH) &&
							(avgLength>UNRELIABLE_MAX_AVG_LENGTH)) calMethodAccuracy = 2;
					else calMethodAccuracy = 1;
	}

	public final Vector<PCalPeriodInfo> getPeriodsStats()
	{
		Vector<PCalPeriodInfo> result = new Vector<PCalPeriodInfo>();

		if(startDates.size()>1) {
			Date prevdate = null;

			for(Date pstart: startDates)
			{
				if(prevdate == null) { 
					prevdate = pstart;
					continue;
				}

				// skip the non-menstruation neighbor records
				if((dateTypes.get(pstart) != PCAL_TYPE_MENS)||
						(dateTypes.get(prevdate) != PCAL_TYPE_MENS)) {
					prevdate = pstart;
					continue;
				}

				int p_length = dateDiff(prevdate, pstart);

				if((p_length < enginePreferences.regularMinLength) ||
						(p_length > enginePreferences.regularMaxLength)) {
					// bad period should not be taken into account            
				} else {
					PCalPeriodInfo pinfo = new PCalPeriodInfo();
					pinfo.startDate = prevdate;
					pinfo.endDate = pstart;
					pinfo.length = p_length;
					result.add(pinfo);
				}
				prevdate = pstart;
			}
		}

		return result;
	}

	/**
	 * @return Last menstruation date or null if none
	 */
	public final Date getLastMenstruationDate()
	{
		if(startDates.size()>0) {
			Date last_date = startDates.last();
			if(dateTypes.get(last_date) == PCAL_TYPE_MENS) {
				return last_date;
			}        
			return null;
		} else {
			return null;
		}
	}

	/**
	 * @param date
	 * @return note for the specified date or null if none
	 */
	public String getDateNote(final Date date)
	{
		return notesContainer.get(date);
	}

	/**
	 * @param date
	 * @return true if the note exists for the date
	 */
	public boolean existsDateNote(final Date date)
	{
		return notesContainer.containsKey(date);
	}

	public void setDateNote(final Date date, final String note)
	{
		// need to check if any changes not to fall to the modified state
		// without a reason
		String old_note = notesContainer.get(date);
		if(old_note!=null) {
			if(old_note.equals(note)) {
				return;
			}
		}		
		notesContainer.put(date, note);		
		modified = true;
	}

	/**
	 * @return the list of the dates which have notes
	 */
	public final Set<Date> getNoteDates()
	{
		return notesContainer.keySet();
	}

	public void removeDateNote(final Date date)
	{
		notesContainer.remove(date);
		modified = true;
	} 

	/** the difference in days between 2 dates within the Gregorian calendar - <b>after</b> 1582
	 * @param earlier the start date
	 * @param later the end date
	 * @return number of days
	 */
	public static int dateDiff( java.util.Date earlier, java.util.Date later ) {
		java.util.GregorianCalendar start = new
		java.util.GregorianCalendar();
		java.util.GregorianCalendar finish = new
		java.util.GregorianCalendar();
		if (later.compareTo( earlier ) > 0 ) { // this Date is after the Date argument
			start.setTime(later);
			finish.setTime(earlier);
		} else 
			if (later.compareTo( earlier ) < 0 ) { // this Date is	before the Date argument
				start.setTime(earlier);
				finish.setTime(later);
			} else {
				return 0; // must be the same date
			}
		int days = 1;
		//if dates in the same year
		if ( start.get(Calendar.YEAR) == finish.get(Calendar.YEAR) ) {
			days = start.get(Calendar.DAY_OF_YEAR) -
			finish.get(Calendar.DAY_OF_YEAR);
		} else {
			days = start.get(Calendar.DAY_OF_YEAR); // number of days in the current year
			while ( start.get(Calendar.YEAR) - 1 > finish.get(Calendar.YEAR)
			) {
				start.add(Calendar.YEAR, -1); // keep counting back till they are the same year
				days += 365;
				if ( start.isLeapYear(start.get(Calendar.YEAR) ) ) {
					days++;
				}
			}
			start.add(Calendar.YEAR, -1); // get the days in the last year
			days += 365 - finish.get(Calendar.DAY_OF_YEAR);
			if ( start.isLeapYear(start.get(Calendar.YEAR) ) )
				days++;
		}
		return days;
	}

	/**
	 * Get the predicted birthday for a specified date and pregnancy day number
	 * @param date - currently selected date
	 * @param pregnancyDayNumber - the corresponding pregnancy day number
	 * @return predicted date of the birth
	 */
	public Date getPredictedBirthDay(Date date, int pregnancyDayNumber) {
		java.util.GregorianCalendar calendar = new java.util.GregorianCalendar();

		calendar.setTime(date);
		calendar.add(Calendar.DATE, REGULAR_PREGNANCY_DAYS - pregnancyDayNumber);
		return calendar.getTime();
	}

	/**
	 * @return the modified
	 */
	public boolean isModified() {
		return modified;
	}

	/**
	 * @return the calMethodAccuracy
	 */
	public int getCalMethodAccuracy() {
		return calMethodAccuracy;
	}

	/**
	 * @return the workFileName
	 */
	public final File getWorkFile() {
		return workFile;
	}

	/**
	 * Sets workFile
	 * @param f - a file reference
	 */
	private void setWorkFile(File f) {
		workFile = f;
	}


	/**
	 * @return the avgLength
	 */
	public int getAvgLength() {
		return avgLength;
	}

	/**
	 * @return the maxLength
	 */
	public int getMaxLength() {
		return maxLength;
	}

	/**
	 * @return the minLength
	 */
	public int getMinLength() {
		return minLength;
	}

	/**
	 * @return the periodsCount
	 */
	public int getPeriodsCount() {
		return periodsCount;
	}

	/**
	 * @return the enginePreferences
	 */
	public EnginePreferences getEnginePreferences() {
		return enginePreferences;
	}

	/**
	 * Set preferences, save them and recalculate the internal data
	 * @param enginePreferences the enginePreferences to set
	 */
	public void setEnginePreferences(EnginePreferences enginePreferences) {
		this.enginePreferences = enginePreferences;
		Preferences enginePrefsNode = PCalendar.settings.node("Engine"); //$NON-NLS-1$
		enginePrefsNode.putInt("Buffer Days", enginePreferences.bufferDays); //$NON-NLS-1$
		enginePrefsNode.putInt("Bad Feels Before", enginePreferences.badFeelDaysBefore); //$NON-NLS-1$
		enginePrefsNode.putInt("Bad Feels After", enginePreferences.badFeelDaysAfter); //$NON-NLS-1$
		enginePrefsNode.putInt("Reg. Min Length", enginePreferences.regularMinLength); //$NON-NLS-1$
		enginePrefsNode.putInt("Reg. Max Length", enginePreferences.regularMaxLength); //$NON-NLS-1$
		recalculate();
	}

	/**
	 * @return the default engine preferences
	 */
	public EnginePreferences getDefaultPreferences() {
		EnginePreferences enginePreferences = new EnginePreferences();
		enginePreferences.bufferDays = DEFAULT_BUFFER_DAYS;
		enginePreferences.badFeelDaysBefore = DEFAULT_BAD_FEEL_DAYS_BEFORE;
		enginePreferences.badFeelDaysAfter = DEFAULT_BAD_FEEL_DAYS_AFTER;
		enginePreferences.regularMinLength = DEFAULT_REGULAR_MIN_LENGTH;
		enginePreferences.regularMaxLength = DEFAULT_REGULAR_MAX_LENGTH;
		return enginePreferences;
	}

}