/**
 *  Copyright (C) 2008 by Maryan Rachynskyy
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

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.Vector;

import net.sf.linuxorg.pcal.PCalDayInfo;
import net.sf.linuxorg.pcal.PCalPeriodInfo;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Maryan Rachynskyy
 *
 */
public class EngineTest {

	private Engine engine;
	private GregorianCalendar calendar;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		engine = new Engine();
		calendar = new GregorianCalendar();
		
		// Record some sample menstruation dates
		calendar.set(2007, 9, 1);
		engine.addStartDate(calendar.getTime());
		calendar.set(2007, 9, 26);
		engine.addStartDate(calendar.getTime());
		calendar.set(2007, 10, 20);
		engine.addStartDate(calendar.getTime());
		calendar.set(2007, 11, 15);
		engine.addStartDate(calendar.getTime());
		calendar.set(2008, 0, 9);
		engine.addStartDate(calendar.getTime());
	}

	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.Engine#getDayInfo(java.util.Date)}.
	 */
	@Test
	public void testGetDayInfo() {
		PCalDayInfo dayInfo;
		
		// test day 1 combinations
		// recorded day 0
		calendar.set(2007, 9, 26);
		dayInfo = engine.getDayInfo(calendar.getTime());		
		assertEquals(dayInfo.day_num,0);
		assertFalse(dayInfo.estimate);
		assertFalse(dayInfo.fertile);
		assertFalse(dayInfo.pregnancy);
		assertFalse(dayInfo.birth);
		assertFalse(dayInfo.pregnancy_interruption);
		assertTrue(dayInfo.badFeel);
		//estimated day 0
		calendar.set(2008, 1, 3);
		dayInfo = engine.getDayInfo(calendar.getTime());		
		assertEquals(dayInfo.day_num,0);
		assertTrue(dayInfo.estimate);
		assertFalse(dayInfo.fertile);
		assertFalse(dayInfo.pregnancy);
		assertFalse(dayInfo.birth);
		assertFalse(dayInfo.pregnancy_interruption);
		assertTrue(dayInfo.badFeel);		
		
		// test fertile days combinations
		calendar.set(2007, 9, 12);
		dayInfo = engine.getDayInfo(calendar.getTime());		
		assertEquals(dayInfo.day_num, 11);
		assertFalse(dayInfo.estimate);
		assertTrue(dayInfo.fertile);
		assertFalse(dayInfo.pregnancy);
		assertFalse(dayInfo.birth);
		assertFalse(dayInfo.pregnancy_interruption);
		assertFalse(dayInfo.badFeel);		
		
		calendar.set(2008, 0, 19);
		dayInfo = engine.getDayInfo(calendar.getTime());		
		assertEquals(dayInfo.day_num, 10);
		assertTrue(dayInfo.estimate);
		assertTrue(dayInfo.fertile);
		assertFalse(dayInfo.pregnancy);
		assertFalse(dayInfo.birth);
		assertFalse(dayInfo.pregnancy_interruption);
		assertFalse(dayInfo.badFeel);		
		
		// test non-fertile days combinations
		calendar.set(2007, 10, 25);
		dayInfo = engine.getDayInfo(calendar.getTime());		
		assertEquals(dayInfo.day_num, 5);
		assertFalse(dayInfo.estimate);
		assertFalse(dayInfo.fertile);
		assertFalse(dayInfo.pregnancy);
		assertFalse(dayInfo.birth);
		assertFalse(dayInfo.pregnancy_interruption);
		assertFalse(dayInfo.badFeel);		
		
		calendar.set(2008, 0, 11);
		dayInfo = engine.getDayInfo(calendar.getTime());		
		assertEquals(dayInfo.day_num, 2);
		assertTrue(dayInfo.estimate);
		assertFalse(dayInfo.fertile);
		assertFalse(dayInfo.pregnancy);
		assertFalse(dayInfo.birth);
		assertFalse(dayInfo.pregnancy_interruption);
		assertFalse(dayInfo.badFeel);		
		
		// test pregnancy days combinations
		calendar.set(2008, 6, 2);
		Date date = calendar.getTime();
		engine.addPregnancyDate(date);

		calendar.set(2009, 3, 6);		
		
		// estimated pregnancy date
		dayInfo = engine.getDayInfo(calendar.getTime());		
		assertEquals(dayInfo.day_num, 278);
		assertTrue(dayInfo.estimate);
		assertFalse(dayInfo.fertile);
		assertTrue(dayInfo.pregnancy);
		assertFalse(dayInfo.birth);
		assertFalse(dayInfo.pregnancy_interruption);
		assertTrue(dayInfo.badFeel);		
		
		// estimated birth date
		calendar.set(2009, 3, 8);
		dayInfo = engine.getDayInfo(calendar.getTime());		
		assertEquals(dayInfo.day_num, 280);
		assertTrue(dayInfo.estimate);
		assertFalse(dayInfo.fertile);
		assertFalse(dayInfo.pregnancy);
		assertFalse(dayInfo.pregnancy_interruption);
		assertTrue(dayInfo.birth);
		assertTrue(dayInfo.badFeel);		
		

		engine.addBirthDate(calendar.getTime());
		
		// recorded birth
		dayInfo = engine.getDayInfo(calendar.getTime());		
		assertEquals(dayInfo.day_num, 0);
		assertFalse(dayInfo.estimate);
		assertFalse(dayInfo.fertile);
		assertFalse(dayInfo.pregnancy);
		assertFalse(dayInfo.pregnancy_interruption);
		assertTrue(dayInfo.birth);
		assertTrue(dayInfo.badFeel);		
		
		// recorded pregnancy
		calendar.set(2009, 3, 6);
		dayInfo = engine.getDayInfo(calendar.getTime());		
		assertEquals(dayInfo.day_num, 278);
		assertFalse(dayInfo.estimate);
		assertFalse(dayInfo.fertile);
		assertTrue(dayInfo.pregnancy);
		assertFalse(dayInfo.birth);
		assertFalse(dayInfo.pregnancy_interruption);
		assertTrue(dayInfo.badFeel);		
		
		// test the interrupted pregnancy functionality
		calendar.set(2009, 3, 8);
		engine.removeDateRecord(calendar.getTime());
		
		calendar.set(2009, 3, 6);
		engine.addPregnancyInterruptDate(calendar.getTime());
		
		// interruption date
		dayInfo = engine.getDayInfo(calendar.getTime());
		assertEquals(dayInfo.day_num, 0);
		assertFalse(dayInfo.estimate);
		assertFalse(dayInfo.fertile);
		assertFalse(dayInfo.pregnancy);
		assertFalse(dayInfo.birth);
		assertTrue(dayInfo.pregnancy_interruption);
		assertTrue(dayInfo.badFeel);
		
		// after interruption
		calendar.set(2009, 3, 7);
		dayInfo = engine.getDayInfo(calendar.getTime());
		assertEquals(dayInfo.day_num, -1);
		assertTrue(dayInfo.estimate);
		assertFalse(dayInfo.fertile);
		assertFalse(dayInfo.pregnancy);
		assertFalse(dayInfo.birth);
		assertFalse(dayInfo.pregnancy_interruption);
		assertFalse(dayInfo.badFeel);

		// before interruption
		calendar.set(2009, 3, 5);
		dayInfo = engine.getDayInfo(calendar.getTime());
		assertEquals(dayInfo.day_num, 277);
		assertFalse(dayInfo.estimate);
		assertFalse(dayInfo.fertile);
		assertTrue(dayInfo.pregnancy);
		assertFalse(dayInfo.birth);
		assertFalse(dayInfo.pregnancy_interruption);
		assertTrue(dayInfo.badFeel);		

	}

	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.Engine#addStartDate(java.util.Date)}.
	 */
	@Test
	public void testAddStartDate() {
		calendar.set(2008, 1, 2);
		Date date = calendar.getTime();
		engine.addStartDate(date);
		assertTrue(engine.getDayInfo(date).day_num == 0);
		assertFalse(engine.getDayInfo(date).pregnancy);
	}

	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.Engine#addPregnancyDate(java.util.Date)}.
	 */
	@Test
	public void testAddPregnancyDate() {
		calendar.set(2008, 6, 2);
		Date date = calendar.getTime();
		engine.addPregnancyDate(date);
		assertTrue(engine.getDayInfo(date).day_num == 0);
		assertTrue(engine.getDayInfo(date).pregnancy);
		calendar.set(2009, 3, 8);		
		date = calendar.getTime();
		assertTrue(engine.getDayInfo(date).birth);
		assertTrue(engine.getDayInfo(date).estimate);		
	}

	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.Engine#addBirthDate(java.util.Date)}.
	 */
	@Test
	public void testAddBirthDate() {
		calendar.set(2008, 6, 2);
		Date date = calendar.getTime();
		engine.addPregnancyDate(date);
		assertTrue(engine.getDayInfo(date).day_num == 0);
		assertTrue(engine.getDayInfo(date).pregnancy);
		calendar.set(2009, 3, 1);
		date = calendar.getTime();
		engine.addBirthDate(date);
		assertTrue(engine.getDayInfo(date).birth);
		assertFalse(engine.getDayInfo(date).estimate);		
	}

	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.Engine#removeDateRecord(java.util.Date)}.
	 */
	@Test
	public void testRemoveDateRecord() {
		calendar.set(2008, 1, 2);
		Date date = calendar.getTime();
		engine.addStartDate(date);
		engine.removeDateRecord(date);
		assertTrue(engine.getDayInfo(date).estimate);
	}

	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.Engine#clear()}.
	 */
	@Test
	public void testClear() {		
		engine.clear();
		assertEquals(engine.getPeriodsCount(),0);
	}

	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.Engine#getPeriodsStats()}.
	 */
	@Test
	public void testGetPeriodsStats() {
		Vector<PCalPeriodInfo> stats = engine.getPeriodsStats();
		assertEquals(stats.size(),4);
		assertEquals(stats.get(0).length,25);
		assertEquals(stats.get(1).length,25);
		assertEquals(stats.get(2).length,25);
		assertEquals(stats.get(3).length,25);
	}

	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.Engine#getLastMenstruationDate()}.
	 */
	@Test
	public void testGetLastMenstruationDate() {
		calendar.set(2008, 0, 9);
		Date date = engine.getLastMenstruationDate();
		assertEquals(date.getTime(),calendar.getTimeInMillis());
	}

	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.Engine#getDateNote(java.util.Date)}.
	 */
	@Test
	public void testGetDateNote() {
		calendar.set(2008, 4, 9);
		Date date = calendar.getTime();
		engine.setDateNote(date, "Test Notes"); //$NON-NLS-1$
		assertEquals(engine.getDateNote(date), "Test Notes"); //$NON-NLS-1$
	}

	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.Engine#existsDateNote(java.util.Date)}.
	 */
	@Test
	public void testExistsDateNote() {
		calendar.set(2008, 4, 9);
		Date date = calendar.getTime();
		engine.setDateNote(date, "Test Notes"); //$NON-NLS-1$
		assertTrue(engine.existsDateNote(date));
	}

	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.Engine#setDateNote(java.util.Date, java.lang.String)}.
	 */
	@Test
	public void testSetDateNote() {
		testGetDateNote();
	}

	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.Engine#getNoteDates()}.
	 */
	@Test
	public void testGetNoteDates() {
		calendar.set(2008, 4, 9);
		Date date1 = calendar.getTime();
		engine.setDateNote(date1, "Test Notes"); //$NON-NLS-1$
		calendar.set(2008, 4, 11);
		Date date2 = calendar.getTime();
		engine.setDateNote(date2, "Test Notes2"); //$NON-NLS-1$
		Set<Date> dates = engine.getNoteDates();
		assertEquals(dates.size(), 2);
		assertTrue(dates.contains(date1));
		assertTrue(dates.contains(date2));
	}

	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.Engine#removeDateNote(java.util.Date)}.
	 */
	@Test
	public void testRemoveDateNote() {
		calendar.set(2008, 4, 9);
		Date date = calendar.getTime();
		engine.setDateNote(date, "Test Notes"); //$NON-NLS-1$
		assertTrue(engine.existsDateNote(date));
		engine.removeDateNote(date);
		assertFalse(engine.existsDateNote(date));
	}

	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.Engine#dateDiff(java.util.Date, java.util.Date)}.
	 */
	@Test
	public void testDateDiff() {
		calendar.set(2008, 4, 9);
		Date date1 = calendar.getTime();
		calendar.set(2011, 0, 21);
		Date date2 = calendar.getTime();
		assertEquals(Engine.dateDiff(date2, date1), 987);
	}

	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.Engine#getPredictedBirthDay(java.util.Date, int)}.
	 */
	@Test
	public void testGetPredictedBirthDay() {
		calendar.set(2008, 6, 2);
		Date date = calendar.getTime();
		engine.addPregnancyDate(date);
		calendar.set(2009, 3, 8);
		assertEquals(calendar.getTimeInMillis(), engine.getPredictedBirthDay(date, 0).getTime());
	}

	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.Engine#isModified()}.
	 */
	@Test
	public void testIsModified() {
		assertTrue(engine.isModified());
		engine.clear();
		assertFalse(engine.isModified());
	}

	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.Engine#getCalMethodAccuracy()}.
	 */
	@Test
	public void testGetCalMethodAccuracy() {
		assertEquals(engine.getCalMethodAccuracy(), 2);
		calendar.set(2008, 1, 3);
		engine.addStartDate(calendar.getTime());
		assertEquals(engine.getCalMethodAccuracy(), 1);
	}

	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.Engine#getAvgLength()}.
	 */
	@Test
	public void testGetAvgLength() {
		assertEquals(engine.getAvgLength(), 25);
		calendar.set(2008, 1, 8);
		engine.addStartDate(calendar.getTime());
		assertEquals(engine.getAvgLength(), 26);;
	}

	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.Engine#getMaxLength()}.
	 */
	@Test
	public void testGetMaxLength() {
		assertEquals(engine.getMaxLength(), 25);
		calendar.set(2008, 1, 8);
		engine.addStartDate(calendar.getTime());		
		assertEquals(engine.getMaxLength(), 30);;
	}

	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.Engine#getMinLength()}.
	 */
	@Test
	public void testGetMinLength() {
		assertEquals(engine.getMinLength(), 25);
		calendar.set(2008, 0, 31);
		engine.addStartDate(calendar.getTime());		
		assertEquals(engine.getMinLength(), 25);;
	}

	/**
	 * Test method for {@link net.sf.linuxorg.pcal.engine.Engine#getPeriodsCount()}.
	 */
	@Test
	public void testGetPeriodsCount() {
		assertEquals(engine.getPeriodsCount(), 4);
		calendar.set(2008, 1, 2);
		engine.addStartDate(calendar.getTime());		
		assertEquals(engine.getPeriodsCount(), 5);		
	}
}
