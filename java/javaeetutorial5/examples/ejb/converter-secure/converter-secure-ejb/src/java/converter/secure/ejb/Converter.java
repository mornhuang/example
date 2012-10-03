/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package converter.secure.ejb;

import java.math.BigDecimal;
import javax.ejb.Remote;


@Remote
public interface Converter {
    public BigDecimal dollarToYen(BigDecimal dollars);

    public BigDecimal yenToEuro(BigDecimal yen);
}
