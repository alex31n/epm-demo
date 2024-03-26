package com.bits.epm.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Component
@Slf4j
public class DateUtils {

    public static Date parse(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateString);
    }



    public static String ageFromDate(String dateInString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
        LocalDate dateTime = LocalDate.parse(dateInString, formatter);
        int years = Period.between(dateTime, LocalDate.now()).getYears();
        return String.valueOf(years);
    }

    public static String ageFromDate(Date date){
        LocalDate localCreationTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localCurrentTime = LocalDate.now();
        int years = Period.between(localCreationTime, localCurrentTime).getYears();

        return String.valueOf(years);
    }

}
