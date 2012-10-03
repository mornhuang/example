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

import java.util.prefs.Preferences;

import net.sf.linuxorg.pcal.engine.Engine;

/**
 * This class is just an entry point to the application.
 * It is parsing command line arguments and initiates the application core.
 * @author Maryan Rachynskyy
 *
 */

public class PCalendar {

	/**
	 * The primary source of the application core version information. 
	 */
	public static final String APP_VERSION = "3.1.0"; //$NON-NLS-1$

	public static MainWindow mainWindow;
	public static Engine engine;
	public static String preloadFile = ""; //$NON-NLS-1$
	public static final Preferences settings = Preferences.userNodeForPackage(PCalendar.class);

	/**
	 * Application entry point
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length > 0) {
			// try to open the file specified
			preloadFile = args[0];
		}
		engine = new Engine();
		mainWindow = new MainWindow();
	}
}
