package com.example.rpham.tonaltask.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class TimeUtils {

    private TimeUtils() {
        // This private constructor should never be called.
        throw new AssertionError("Instantiating a utility class is not allowed.");
    }

    /**
     * Formats the given timestamp (unix format) to a String with format "EEEE MMM dd, yyyy hh:mm aa".
     *
     * @param timestamp Timestamp to be converted.
     * @return String of the formatted timestamp.
     */
    public static String toString(long timestamp) {
        Date date = new Date();
        date.setTime(timestamp * 1000L);
        String format = "EEEE MMM dd, yyyy hh:mm aa";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format, Locale.US);
        return simpleDateFormat.format(date);
    }
}
