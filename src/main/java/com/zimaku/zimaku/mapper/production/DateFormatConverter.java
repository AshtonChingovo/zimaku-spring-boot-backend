package com.zimaku.zimaku.mapper.production;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateFormatConverter {

    private String date;

    public DateFormatConverter(String date) {
        this.date = date;
    }

    // age in days
    public String convert(){

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDate date = LocalDate.parse(this.date, inputFormatter);

        // initial value is zero, add 1 so days start at 1 instead
        // i.e. all stock starts at one day old not zero
        long daysDifference = ChronoUnit.DAYS.between(date, LocalDate.now()) + 1L;

        return daysDifference + " days";
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
