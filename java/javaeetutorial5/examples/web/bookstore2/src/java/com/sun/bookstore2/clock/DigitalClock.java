/*
 * Copyright 2007 Sun Microsystems, Inc.
 * All rights reserved.  You may not modify, use,
 * reproduce, or distribute this software except in
 * compliance with  the terms of the License at:
 * http://developer.sun.com/berkeley_license.html
 */


package com.sun.bookstore2.clock;

import java.util.*;
import java.awt.*;
import java.applet.*;
import java.text.*;


public class DigitalClock extends Applet implements Runnable {
    Color numberColor; // Color of numbers
    Date currentDate; // Used to get date to display
    DateFormat formatter; // Formats the date displayed
    Font clockFaceFont;
    Locale locale;
    String country;
    String language;
    String lastdate; // String to hold date displayed
    Thread timer; // The thread that displays clock

    public void init() {
        numberColor = Color.black;

        try {
            language = getParameter("language");
        } catch (Exception E) {
        }

        try {
            country = getParameter("country");
        } catch (Exception E) {
        }

        if (country == null) {
            country = "";
        } else {
            System.err.println(country);
        }

        if (language == null) {
            locale = Locale.getDefault();
        } else {
            System.err.println(language);
            locale = new Locale(language, country);
        }

        System.err.println(locale.getDisplayName());

        try {
            setBackground(
                    new Color(
                            Integer.parseInt(
                                getParameter("bgcolor"),
                                16)));
        } catch (Exception E) {
        }

        try {
            numberColor = new Color(
                        Integer.parseInt(
                            getParameter("fgcolor"),
                            16));
        } catch (Exception E) {
        }

        formatter = DateFormat.getDateTimeInstance(
                    DateFormat.FULL,
                    DateFormat.MEDIUM,
                    locale);
        currentDate = new Date();
        lastdate = formatter.format(currentDate);
        clockFaceFont = new Font("Sans-Serif", Font.PLAIN, 14);
        resize(275, 25); // Set clock window size
    }

    // Paint is the main part of the program
    public void paint(Graphics g) {
        String today;
        currentDate = new Date();
        formatter = DateFormat.getDateTimeInstance(
                    DateFormat.FULL,
                    DateFormat.MEDIUM,
                    locale);
        today = formatter.format(currentDate);
        g.setFont(clockFaceFont);

        // Erase and redraw  
        g.setColor(getBackground());
        g.drawString(lastdate, 0, 12);

        g.setColor(numberColor);
        g.drawString(today, 0, 12);
        lastdate = today;
        currentDate = null;
    }

    public void start() {
        timer = new Thread(this);
        timer.start();
    }

    public void stop() {
        timer = null;
    }

    public void run() {
        Thread me = Thread.currentThread();

        while (timer == me) {
            try {
                Thread.currentThread()
                      .sleep(100);
            } catch (InterruptedException e) {
            }

            repaint();
        }
    }

    public void update(Graphics g) {
        paint(g);
    }

    public String getAppletInfo() {
        return "Digital Clock.";
    }

    public String[][] getParameterInfo() {
        String[][] info = {
                {
                    "bgcolor", "hexadecimal RGB number",
                    "The background color. Default is the color of your browser."
                },
                { "fgcolor", "hexadecimal RGB number", "The color of the date." }
            };

        return info;
    }
}
