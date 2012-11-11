/*
 * Java USB Library
 * Copyright (C) 2000 by David Brownell
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package usb.util;

import java.util.Locale;

// $Id: LangCode.java,v 1.1 2008/10/15 09:28:02 huangmeng Exp $


/**
 * I18N support.
 * USB does not use standard ISO language codes, but instead uses numeric
 * codes defined by Microsoft.  This class maps some of those
 * to standard Java locale constants (which use the ISO codes), but not all
 * of the numeric codes are recognized.  Locales using English, French,
 * German, Italian, Portuguese, Chinese, Japanese, Korean,
 * or Russian as their language should have reasonably good support.
 *
 * <p><em>Note:</em> Some embedded configurations don't guarantee
 * enough functionality to rely on this class being available.
 */
public class LangCode
{
    private LangCode () { }

    /**
     * Utility method returning a locale appropriate to the USB
     * language code passed, or null if the code is not recognized.
     *
     * @see usb.core.ControlMessage#getLanguages
     */
    public static Locale getLocale (int langcode)
    {
	// This is just the standard JDK 1.1 constants plus
	// additions for a few major omitted languages.

	if (langcode == 0x0404)
	    return Locale.TRADITIONAL_CHINESE;
	if (langcode == 0x0804)
	    return Locale.SIMPLIFIED_CHINESE;

	if (langcode == 0x0411)
	    return Locale.JAPANESE;

	if (langcode == 0x0412)
	    return Locale.KOREAN;

	if (langcode == 0x0407)
	    return Locale.GERMAN;

	if (langcode == 0x0409)
	    return Locale.US;
	if (langcode == 0x0809)
	    return Locale.UK;
	if (langcode == 0x1009)
	    return Locale.CANADA;

	if ((langcode & 0x00ff) == 0x000A)	// spanish
	    return new Locale ("es", "");

	if (langcode == 0x040C)
	    return Locale.FRANCE;
	if (langcode == 0x0c0C)
	    return Locale.CANADA_FRENCH;

	if (langcode == 0x0410)
	    return Locale.ITALIAN;

	if ((langcode & 0x00ff) == 0x0016)	// portuguese
	    return new Locale ("pt", "");

	if ((langcode & 0x00ff) == 0x0019)	// russian
	    return new Locale ("ru", "");

	return null;
    }

}
