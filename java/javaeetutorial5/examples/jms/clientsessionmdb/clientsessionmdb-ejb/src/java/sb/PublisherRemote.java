/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package sb;

import javax.ejb.Remote;


/**
 * Remote interface for Publisher enterprise bean. Declares one
 * business method.
 */
@Remote
public interface PublisherRemote {
    void publishNews();
}
