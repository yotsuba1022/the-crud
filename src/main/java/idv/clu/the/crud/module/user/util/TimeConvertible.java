package idv.clu.the.crud.module.user.util;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

/**
 * @author Carl Lu
 */
public interface TimeConvertible {

    Timestamp getTimestamp(LocalDateTime localDateTime);

    Instant getInstantFromLocalDateTime(LocalDateTime localDateTime);

    LocalDateTime fromTimestamp(Timestamp timestamp);

    LocalDateTime fromInstant(Instant instant);

}
