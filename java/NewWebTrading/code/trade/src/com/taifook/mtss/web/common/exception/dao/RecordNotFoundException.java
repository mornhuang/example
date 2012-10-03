/*
 *  Copyright (c) 2004 Tai Fook Securities Group Limited.
 *  All rights reserved.
 *
 *  This file contains the valuable properties of Tai Fook Securities
 *  Group Limited, embodying substantial creative efforts and confidential
 *  information, ideas and expressions. No part of this file may be
 *  reproduced or distributed in any form or by any means, or stored
 *  in a data base or a retrieval system, without the prior written
 *  permission of Tai Fook Securities Group Limited.
 *
 */
package com.taifook.mtss.web.common.exception.dao;


public class RecordNotFoundException extends DAOException {

    protected String messageKey = null;
    protected Object[] args = null;

    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(String message, Throwable enclosedException) {
        super(message, enclosedException);
    }

    public RecordNotFoundException(Throwable enclosedException) {
        super(enclosedException);
    }

    public void setMessageKey( String messageKey ) {
        this.messageKey = messageKey;
    }

    public String getMessageKey() {
        return this.messageKey;
    }

    public void setArgs( Object[] args ) {
        this.args = args;
    }

    public Object[] getArgs() {
        return this.args;
    }
}
