package com.travix.medusa.busyflights.utils;

import com.travix.medusa.busyflights.domain.busyflights.BusyFlightsResponse;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    private DateTimeUtils() {
    }

    public static String convertResponseDateTime(String date, DateTimeFormatter format) {
        if (format == DateTimeFormatter.ISO_INSTANT) {
            Instant instant = Instant.parse(date);
            return LocalDateTime.ofInstant(instant, ZoneOffset.UTC) // the time zone has to be double-checked with the supplier, but it is a convention that instants are in UTC
                    .format(BusyFlightsResponse.DATE_TIME_FORMAT);
        } else {
            return LocalDateTime.parse(date, format)
                    .format(BusyFlightsResponse.DATE_TIME_FORMAT);
        }
    }
}
