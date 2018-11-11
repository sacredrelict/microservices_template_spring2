package com.github.sacredrelict.data.common.component.util;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Util class for dates.
 */
public class DateUtils {

    /**
     * LocalDateTime to Long converter.
     * @param localDateTime - date in LocalDateTime format.
     * @return Long object.
     */
    public static Long toLong(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(ZoneOffset.UTC)).getTime();
    }

}
