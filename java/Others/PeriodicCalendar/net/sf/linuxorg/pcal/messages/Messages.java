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

package net.sf.linuxorg.pcal.messages;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.KeyStroke;

/**
 * This class handles the localized messages loading
 * @author Maryan Rachynskyy
 *
 */
public class Messages {
	private static final String BUNDLE_NAME = "net.sf.linuxorg.pcal.messages.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	private Messages() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
	public static int getMnemonic(String key) {
		// Unfortunately Java does not support non-latin mnemonics properly
		char keyChar = (getString(key)).charAt(0);
        return KeyStroke.getKeyStroke(keyChar, 0).getKeyCode();
	}	 
}
