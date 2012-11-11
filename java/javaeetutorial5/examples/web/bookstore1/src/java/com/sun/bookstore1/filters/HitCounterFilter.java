/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore1.filters;

import com.sun.bookstore1.filters.CharResponseWrapper;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.sun.bookstore1.util.Counter;


public final class HitCounterFilter implements Filter {
    private FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(
        ServletRequest request,
        ServletResponse response,
        FilterChain chain) throws IOException, ServletException {
        if (filterConfig == null) {
            return;
        }

        HttpServletRequest hr = (HttpServletRequest) request;
        HttpSession session = hr.getSession();
        ResourceBundle messages = (ResourceBundle) session.getAttribute(
                    "messages");

        if (messages == null) {
            Locale locale = request.getLocale();
            messages = ResourceBundle.getBundle(
                        "com.sun.bookstore.messages.BookstoreMessages",
                        locale);
            session.setAttribute("messages", messages);
        }

        StringWriter sw = new StringWriter();
        PrintWriter writer = new PrintWriter(sw);

        Counter counter = (Counter) filterConfig.getServletContext()
                                                .getAttribute("hitCounter");
        writer.println();
        writer.println(
                "=======================================================");
        writer.println("The number of hits is: " + counter.incCounter());
        writer.println(
                "=======================================================");

        // Log the resulting string
        writer.flush();
        System.out.println(sw.getBuffer().toString());

        PrintWriter out = response.getWriter();
        CharResponseWrapper wrapper = new CharResponseWrapper(
                    (HttpServletResponse) response);
        chain.doFilter(request, wrapper);

        CharArrayWriter caw = new CharArrayWriter();
        caw.write(
                wrapper.toString().substring(
                        0,
                        wrapper.toString().indexOf("</body>") - 1));
        caw.write(
                "<p>\n<center>" + messages.getString("Visitor")
                + "<font color='red'>" + counter.getCounter()
                + "</font></center>");
        caw.write("\n</body></html>");
        response.setContentLength(caw.toString()
                                     .getBytes().length);
        out.write(caw.toString());
        out.close();
    }

    public String toString() {
        if (filterConfig == null) {
            return ("HitCounterFilter()");
        }

        StringBuffer sb = new StringBuffer("HitCounterFilter(");
        sb.append(filterConfig);
        sb.append(")");

        return (sb.toString());
    }
}
