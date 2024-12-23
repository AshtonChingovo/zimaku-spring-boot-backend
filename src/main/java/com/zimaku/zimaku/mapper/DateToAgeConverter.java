package com.zimaku.zimaku.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateToAgeConverter {

    private String date;

    public DateToAgeConverter(String date) {
        this.date = date;
    }

    // age in days
    public String convert(){

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate date = LocalDate.parse(this.date, inputFormatter);

        long daysDifference = ChronoUnit.DAYS.between(date, LocalDate.now());

        return String.valueOf(daysDifference);
    }

    // age in weeks & days
/*    public String convert(){

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate date = LocalDate.parse(this.date, inputFormatter);

        long daysDifference = ChronoUnit.DAYS.between(date, LocalDate.now());

        var ageWeeks = daysDifference / 7;
        var ageDays = daysDifference % 7;
        var weeksString = ageWeeks == 1 ? "wk" : "wks";

        return String.format("%d %s %d d", ageWeeks, weeksString, ageDays);
    }*/
}
