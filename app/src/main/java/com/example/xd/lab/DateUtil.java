package com.example.xd.lab;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by root on 27.01.2016.
 */
public class DateUtil {

    public final static int ONE_DAY = 0;
    public final static int ONE_MONTH = 1;
    public final static int ONE_YEAR = 2;
    public final static SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

    public static long getTimeMillis(int type) {
        long result;
        switch (type)
        {
            case ONE_DAY :
            {
                result = 86400000;
                break;
            }
            case ONE_MONTH:
            {
                result = 2592000000L;
                break;
            }
            case ONE_YEAR:
            {
                result = 30758400000L;
                break;
            }
            default:
            {
                result = 0;
            }
        }
        return result;
    }

    public static String format(String timemillis)
    {
        return timemillis != null && timemillis != "" ? SIMPLE_DATE_FORMAT.format(new Date(Long.parseLong(timemillis))) : "";
    }
}
