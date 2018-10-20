package idv.clu.the.crud.module.user.util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/**
 * @author Carl Lu
 */
public enum TimeInstance implements TimeConvertible {

    ISO8601TW {
        @Override
        public Timestamp getTimestamp(LocalDateTime localDateTime) {
            return Timestamp.from(getInstantFromLocalDateTime(localDateTime));
        }

        @Override
        public Instant getInstantFromLocalDateTime(LocalDateTime localDateTime) {
            return localDateTime.toInstant(ZoneOffset.of(ZONE_OFFSET_TAIPEI));
        }

        @Override
        public LocalDateTime fromTimestamp(Timestamp timestamp) {
            return LocalDateTime.ofInstant(timestamp.toInstant(), ZoneId.of(ZONE_ID_TAIPEI));
        }

        @Override
        public LocalDateTime fromInstant(Instant instant) {
            return instant.atZone(ZoneId.of(ZONE_ID_TAIPEI)).toLocalDateTime();
        }
    }, ISO8601TOKYO {
        @Override
        public Timestamp getTimestamp(LocalDateTime localDateTime) {
            return Timestamp.from(getInstantFromLocalDateTime(localDateTime));
        }

        @Override
        public Instant getInstantFromLocalDateTime(LocalDateTime localDateTime) {
            return localDateTime.toInstant(ZoneOffset.of(ZONE_OFFSET_TOKYO));
        }

        @Override
        public LocalDateTime fromTimestamp(Timestamp timestamp) {
            return LocalDateTime.ofInstant(timestamp.toInstant(), ZoneId.of(ZONE_ID_TOKYO));
        }

        @Override
        public LocalDateTime fromInstant(Instant instant) {
            return instant.atZone(ZoneId.of(ZONE_ID_TOKYO)).toLocalDateTime();
        }
    }, ISO8601UTC {
        @Override
        public Timestamp getTimestamp(LocalDateTime localDateTime) {
            return Timestamp.from(getInstantFromLocalDateTime(localDateTime));
        }

        @Override
        public Instant getInstantFromLocalDateTime(LocalDateTime localDateTime) {
            return localDateTime.toInstant(ZoneOffset.UTC);
        }

        @Override
        public LocalDateTime fromTimestamp(Timestamp timestamp) {
            return LocalDateTime.ofInstant(timestamp.toInstant(), ZoneId.of(ZONE_ID_GREENWICH));
        }

        @Override
        public LocalDateTime fromInstant(Instant instant) {
            return instant.atZone(ZoneId.of(ZONE_ID_GREENWICH)).toLocalDateTime();
        }
    };

    private final static String ZONE_OFFSET_TAIPEI = "+8";
    private final static String ZONE_OFFSET_TOKYO = "+9";
    private final static String ZONE_ID_TAIPEI = "Asia/Taipei";
    private final static String ZONE_ID_TOKYO = "Asia/Tokyo";
    private final static String ZONE_ID_GREENWICH = "Etc/Greenwich";

}
