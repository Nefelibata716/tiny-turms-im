package com.gl.tiny.turms.im.util;

import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;

/**
 * @author James Chen
 */
public class TimeZoneConst {

    private TimeZoneConst() {
    }

    /**
     * turms servers always use the UTC time zone, and it is a bad practice to use other time zones
     * especially servers may be deployed in different countries, so we don't allow users to set
     * their own time zone.
     */
    public static final ZoneId ZONE_ID = ZoneOffset.UTC;
    public static final TimeZone ZONE = TimeZone.getTimeZone(ZONE_ID);

}