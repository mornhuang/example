package com.taifook.adminportal.common.util;

import java.util.Date;
import java.text.SimpleDateFormat;

public class DateUtil{

    public static String formatDate(Date date)
    {
        if(date==null)
        {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = df.format(date);
        return dateStr;
    }

    public static String formatDateToMM(Date date)
    {
        if(date==null)
        {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateStr = df.format(date);
        return dateStr;
    }

    public static String formatOnlyDate(Date date)
    {
        if(date==null)
        {
            return "";
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = df.format(date);
        return dateStr;
    }

}
