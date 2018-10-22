package idv.clu.the.crud.module.user.util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * @author Carl Lu
 */
public interface TimeConvertible {

    Timestamp getTimestampFromLocalDateTime(LocalDateTime localDateTime);

    Instant getInstantFromLocalDateTime(LocalDateTime localDateTime);

    LocalDateTime getLocalDateTimeFromTimestamp(Timestamp timestamp);

    LocalDateTime getLocalDateTimeFromInstant(Instant instant);

}
