package idv.clu.the.crud.module.user.util;

import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

/**
 * @author Carl Lu
 */
public class TimeInstanceTest {

    @Test
    public void testConvertLocalDateTimeFromTaiwanToGreenwich() {
        LocalDateTime localDateTimeTaipei = LocalDateTime.of(2018, 10, 19, 0, 1, 10);
        Instant instant = TimeInstance.ISO8601TW.getInstantFromLocalDateTime(localDateTimeTaipei);
        LocalDateTime localDateTimeGreenwich = TimeInstance.ISO8601UTC.fromInstant(instant);
        assertEquals("2018-10-18T16:01:10", localDateTimeGreenwich.toString());
    }

    @Test
    public void testTimestampShouldBeEqualForDifferentTimeZones() {
        LocalDateTime localDateTimeGreenwich = LocalDateTime.of(2018, 10, 19, 0, 10, 5);
        LocalDateTime localDateTimeTaipei = LocalDateTime.of(2018, 10, 19, 8, 10, 5);
        LocalDateTime localDateTimeTokyo = LocalDateTime.of(2018, 10, 19, 9, 10, 5);

        Timestamp timestampGreenwich = TimeInstance.ISO8601UTC.getTimestamp(localDateTimeGreenwich);
        Timestamp timestampTaipei = TimeInstance.ISO8601TW.getTimestamp(localDateTimeTaipei);
        Timestamp timestampTokyo = TimeInstance.ISO8601TOKYO.getTimestamp(localDateTimeTokyo);

        assertEquals(timestampTaipei, timestampGreenwich);
        assertEquals(timestampTokyo, timestampGreenwich);
        assertEquals(timestampTaipei, timestampTokyo);
    }

    @Test
    public void testConvertTimestampToLocalDateTime() {
        LocalDateTime localDateTimeTaipei = LocalDateTime.of(2018, 10, 19, 10, 10, 5);

        Timestamp timestamp = TimeInstance.ISO8601TW.getTimestamp(localDateTimeTaipei);

        LocalDateTime localDateTimeTokyo = TimeInstance.ISO8601TOKYO.fromTimestamp(timestamp);
        LocalDateTime localDateTimeGreenwich = TimeInstance.ISO8601UTC.fromTimestamp(timestamp);

        assertEquals("2018-10-19T11:10:05", localDateTimeTokyo.toString());
        assertEquals("2018-10-19T02:10:05", localDateTimeGreenwich.toString());
    }

}
