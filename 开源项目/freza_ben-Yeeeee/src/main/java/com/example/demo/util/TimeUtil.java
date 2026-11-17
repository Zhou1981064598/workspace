package com.example.demo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {


    public static Date changeStringToDateYMD(String datetime) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
        return simpleDateFormat.parse(datetime);
    }

    public static Date changeStringToDateHM(String datetime) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        return simpleDateFormat.parse(datetime);
    }

    public static String getYyMmDd(String date) {
        String[] time = date.split(" ");
        return time[0];
    }

    public static Date getDateFromStringYMDHMS(String date) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        return simpleDateFormat.parse(date);
    }

    public static String getHhMm(String date) {
        String[] time = date.split(" ");
        String[] hourAndMin = time[1].split(":");
        return hourAndMin[0].concat(":").concat(hourAndMin[1]).concat(":00");
    }
}
