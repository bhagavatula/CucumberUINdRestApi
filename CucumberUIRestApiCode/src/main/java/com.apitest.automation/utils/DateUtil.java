package com.apitest.automation.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Map;

import static com.apitest.automation.utils.DateUtil.getFirstDayThisMonth;

public class DateUtil {
    public  DateUtil(){
    }

    public static void addDatestoMap(Map<String, Object> scenario){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-01");
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        scenario.put("first_day_this_month", getFirstDayThisMonth(dateTimeFormatter));
        scenario.put("first_day_this_month", getFirstDayNextMonth(dateTimeFormatter));
        scenario.put("first_day_this_month", getLastDayNextMonth(dateTimeFormatter));
        scenario.put("first_day_this_month", getPlus30Years(dateTimeFormatter));
    }

    public static String getFirstDayThisMonth(DateTimeFormatter dateTimeFormatter){
        return LocalDate.now().format(dateTimeFormatter);
    }
    public static String getFirstDayNextMonth(DateTimeFormatter dateTimeFormatter){
        LocalDate d = LocalDate.now().plusMonths(1L);
        return d.with(TemporalAdjusters.firstDayOfMonth()).format(dateTimeFormatter);
    }

    public static String getLastDayNextMonth(DateTimeFormatter dateTimeFormatter){
        LocalDate d = LocalDate.now().plusMonths(1L);
        return d.with(TemporalAdjusters.lastDayOfMonth()).format(dateTimeFormatter);
    }

    public static String getPlus30Years(DateTimeFormatter dateFormatter){
        LocalDate d = LocalDate.now().plusMonths(36L);
        return d.with(TemporalAdjusters.firstDayOfMonth()).format(dateFormatter);
    }

    public static String getLogTime(){
        return getTimeStampByFormat("MM-dd-yyyy HH:mm:ss");
    }

    public static String getTimeStampByFormat(String format){
        DateFormat dateformat = new SimpleDateFormat(format);
        Calendar cal = Calendar.getInstance();
        return dateformat.format(cal.getTime());
    }
}
