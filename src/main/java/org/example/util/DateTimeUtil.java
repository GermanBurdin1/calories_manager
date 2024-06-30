package org.example.util;

import java.time.LocalTime;

public class DateTimeUtil {
    public static boolean isBetweenHalfOpen (LocalTime localTime, LocalTime startTime, LocalTime endTime) {
        return !localTime.isBefore(startTime) && localTime.isBefore(endTime);
    }
}
