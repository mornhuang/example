/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package converter.secure.ejb;

import java.math.BigDecimal;
import javax.ejb.*;
import java.security.Principal;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.annotation.security.RolesAllowed;
import javax.annotation.security.DeclareRoles;


/**
 * This is the bean class for the ConverterBean enterprise bean.
 * Created Jan 20, 2006 1:14:27 PM
 * @author ian
 */
@Stateless
@DeclareRoles("BeanUser")
public class ConverterBean implements converter.secure.ejb.Converter {
    @Resource
    SessionContext ctx;
    private BigDecimal euroRate = new BigDecimal("0.0071");
    private BigDecimal yenRate = new BigDecimal("115.3100");

    @RolesAllowed("BeanUser")
    public BigDecimal dollarToYen(BigDecimal dollars) {
        BigDecimal result = new BigDecimal("0.0");
        Principal callerPrincipal = ctx.getCallerPrincipal();

        if (ctx.isCallerInRole("BeanUser")) {
            result = dollars.multiply(yenRate);

            return result.setScale(2, BigDecimal.ROUND_UP);
        } else {
            return result.setScale(2, BigDecimal.ROUND_UP);
        }
    }

    @RolesAllowed("BeanUser")
    public BigDecimal yenToEuro(BigDecimal yen) {
        BigDecimal result = new BigDecimal("0.0");
        Principal callerPrincipal = ctx.getCallerPrincipal();

        if (ctx.isCallerInRole("BeanUser")) {
            result = yen.multiply(euroRate);

            return result.setScale(2, BigDecimal.ROUND_UP);
        } else {
            return result.setScale(2, BigDecimal.ROUND_UP);
        }
    }
}
