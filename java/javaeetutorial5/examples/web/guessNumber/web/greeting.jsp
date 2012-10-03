<!DOCTYPE html
PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--
 * Copyright (c) 2006 Sun Microsystems, Inc.  All rights reserved.  U.S. 
 * Government Rights - Commercial software.  Government users are subject 
 * to the Sun Microsystems, Inc. standard license agreement and 
 * applicable provisions of the FAR and its supplements.  Use is subject 
 * to license terms.  
 * 
 * This distribution may include materials developed by third parties. 
 * Sun, Sun Microsystems, the Sun logo, Java and J2EE are trademarks 
 * or registered trademarks of Sun Microsystems, Inc. in the U.S. and 
 * other countries.  
 * 
 * Copyright (c) 2004-2006 Sun Microsystems, Inc. Tous droits reserves.
 * 
 * Droits du gouvernement americain, utilisateurs gouvernementaux - logiciel
 * commercial. Les utilisateurs gouvernementaux sont soumis au contrat de 
 * licence standard de Sun Microsystems, Inc., ainsi qu'aux dispositions 
 * en vigueur de la FAR (Federal Acquisition Regulations) et des 
 * supplements a celles-ci.  Distribue par des licences qui en 
 * restreignent l'utilisation.
 * 
 * Cette distribution peut comprendre des composants developpes par des 
 * tierces parties. Sun, Sun Microsystems, le logo Sun, Java et J2EE 
 * sont des marques de fabrique ou des marques deposees de Sun 
 * Microsystems, Inc. aux Etats-Unis et dans d'autres pays.
-->

<HTML xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <HEAD> <title>Hello</title> </HEAD>
    <%@ page contentType="application/xhtml+xml" %>
    <%@ taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
    <%@ taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
    <body bgcolor="white">
    <f:view>
    <h:form id="helloForm" >
      <h2>Hi. My name is Duke.  I'm thinking of a number from
      <h:outputText lang="en_US" value="#{UserNumberBean.minimum}"/> to
      <h:outputText value="#{UserNumberBean.maximum}"/>.  Can you guess
      it?</h2>

      <h:graphicImage id="waveImg" url="/wave.med.gif" alt="Duke waving" />
      <h:inputText id="userNo" label="User Number" value="#{UserNumberBean.userNumber}"
      		   converterMessage="#{ErrMsg.userNoConvert}">
                      <f:validateLongRange minimum="#{UserNumberBean.minimum}" maximum="#{UserNumberBean.maximum}" /> 
      </h:inputText>
      <h:commandButton id="submit" action="success" value="Submit" />
      <p>
      <h:message style="color: red; font-family: 'New Century Schoolbook', serif; font-style: oblique; text-decoration: overline" id="errors1" for="userNo"/>
    </p>
   </h:form>
  </f:view>
 </body>
</HTML>  
