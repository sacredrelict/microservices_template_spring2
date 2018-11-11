package com.github.sacredrelict.api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Util class for dates.
 */
public class DateUtils {

    private static final Logger LOG = LoggerFactory.getLogger(DateUtils.class);
    private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Parse Date from String, using simple format.
     * @param dateString
     * @return
     */
    public static Date parseDate(String dateString) {
        Date date = null;
        try {
            date = simpleDateFormat.parse(dateString);
        } catch (ParseException ex) {
            LOG.error("Error while parsing date from string: ", ex);
        }
        return date;
    }

    /**
     * Check is date in String format is valid
     * @param dateString - date in String format.
     * @return boolean result.
     */
    public static boolean isDateValid(String dateString) {
        try {
            simpleDateFormat.parse(dateString);
        } catch (ParseException ex) {
            return false;
        }
        return true;
    }

}
