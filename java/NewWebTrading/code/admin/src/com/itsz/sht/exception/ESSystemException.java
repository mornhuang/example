/**
 *  Copyright (c) 2002 Tai Fook Securities Group Limited. All rights reserved.
 *  This file contains the valuable properties of Tai Fook Securities Group
 *  Limited, embodying substantial creative efforts and confidential
 *  information, ideas and expressions. No part of this file may be reproduced
 *  or distributed in any form or by any means, or stored in a data base or a
 *  retrieval system, without the prior written permission of Tai Fook
 *  Securities Group Limited.
 */
package com.itsz.sht.exception;

import com.taifook.framework.foundation.exception.*;

/**
 *  A <b>TFSystemException</b> is the abstract class of all TaiFook
 *  RuntimeExceptions.  The kind of exceptions should be used if you don't
 *  expect the caller to handle the exceptions
 */

public class ESSystemException extends TFSystemException {
    public ESSystemException() {
        super();
    }

    public ESSystemException(String message) {
        super(message);
    }

    public ESSystemException(String message, Throwable enclosedException) {
        super(message, enclosedException);
    }

    public ESSystemException(Throwable enclosedException) {
        super(enclosedException);
    }
}
