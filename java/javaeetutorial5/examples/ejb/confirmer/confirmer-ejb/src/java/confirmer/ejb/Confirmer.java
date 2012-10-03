/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package confirmer.ejb;

import javax.ejb.Remote;


/**
 * This is the business interface for Confirmer enterprise bean.
 */
@Remote
public interface Confirmer {
    void sendNotice(String recipient);
}
