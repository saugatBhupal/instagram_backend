package com.example.demo.utils.dateTimeUtil;

import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {

    public static Date getDate(){
        LocalDate now = LocalDate.now();
        return(toDate(now));
    }

    public static String getTime(){
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss a");
        String formattedTime = currentTime.format(formatter);
        return(formattedTime);
    }

    public static String getFullTime(){
        LocalTime currentTime = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm:ss");
        String formattedTime = currentTime.format(formatter);
        return(formattedTime);
    }

    public static Long elapsed(Date start, Date end){
        long difference = ChronoUnit.YEARS.between(toLocalDate(start), toLocalDate(end));
        return(difference);
    }

    public static Long elapsedPresent(LocalDate from) {
        LocalDateTime fromDateTime = from.atStartOfDay();
        LocalDateTime nowDateTime = LocalDateTime.now();
        long difference = ChronoUnit.MICROS.between(fromDateTime, nowDateTime);
        return difference;
    }

    public static LocalDate toLocalDate(Date date){
        return(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public static Date toDate(LocalDate date){
        return(java.sql.Date.valueOf(date));
    }

}