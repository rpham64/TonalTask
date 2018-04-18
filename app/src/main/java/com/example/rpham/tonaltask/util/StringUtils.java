package com.example.rpham.tonaltask.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class StringUtils {

    private StringUtils() {
        // This private constructor should never be called.
        throw new AssertionError("Instantiating this utility class is not allowed.");
    }

    /**
     * Checks if the given zip code is a valid US zip code.
     *
     * @param zipCode Zip code to validate.
     * @return True if the given zip code is valid.
     */
    public static boolean isValidZipCode(String zipCode) {
        String regex = "^[0-9]{5}(?:-[0-9]{4})?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(zipCode);
        return matcher.matches();
    }
}
