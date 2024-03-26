package com.bits.epm.utils;

import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Component
public class DateUtils {

    public static Date parse(String dateString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(dateString);
    }

    public static String ageFromDate(Date date){
        LocalDate localCreationTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localCurrentTime = LocalDate.now();
        int years = Period.between(localCreationTime, localCurrentTime).getYears();

        return String.valueOf(years);
    }
}
