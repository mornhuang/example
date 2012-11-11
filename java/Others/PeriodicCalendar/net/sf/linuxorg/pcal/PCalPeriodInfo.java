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

import java.util.Date;

/**
 * @author Maryan Rachynskyy
 *
 */
public class PCalPeriodInfo {
	public Date startDate;
	public Date endDate;
	public int length = 0;
	public PCalPeriodInfo() {
	}
	public PCalPeriodInfo(final PCalPeriodInfo src) {
		startDate = src.startDate;
		endDate = src.endDate;
		length = src.length;
	}
}
