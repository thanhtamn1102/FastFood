package com.example.fastfoodpos.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

public class TimeUtils {

    public static LocalDateTime toLocalDateTime(long timestamp) {
        LocalDateTime triggerTime =
                LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp),
                        TimeZone.getDefault().toZoneId());
        return triggerTime;
    }

    public static long getTimestamp(LocalDateTime localDateTime) {
        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        long date = zdt.toInstant().toEpochMilli();
        return date;
    }

    public static String getStringFromLocalDateTime(LocalDateTime localDateTime) {
        return localDateTime.getHour() + ":" + localDateTime.getMinute() + ":" + localDateTime.getSecond() + "   " +
                localDateTime.getDayOfMonth() + "/" + localDateTime.getMonthValue() + "/" + localDateTime.getYear();
    }

}
