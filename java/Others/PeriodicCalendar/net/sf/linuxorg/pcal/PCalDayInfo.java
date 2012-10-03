/**
 *  Copyright (C) 2007 by Maryan Rachynskyy
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

package net.sf.linuxorg.pcal;

/**
 * This class represents the complete day information 
 * from the PCal engine viewpoint.
 * @author Maryan Rachynskyy
 */
public class PCalDayInfo {
    /**-1 if the date state is undefined
     *0 for the first day of the period
     */
	public int day_num = -1; 
	/** 
	 * true if day info is just an estimate
     * false if it is the documented fact
     */
	public boolean estimate; 
	public boolean fertile;
	public boolean pregnancy;
	public boolean birth;
	public boolean pregnancy_interruption;
	public boolean badFeel;
	public String notes;
};
 