package com.github.sacredrelict.data.util;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Util class for generating random data.
 */
public class RandomDataUtil {

    public static String generateRandomString(int length, boolean useLetters, boolean useNumbers) {
        return RandomStringUtils.random(length, useLetters, useNumbers);
    }

}
