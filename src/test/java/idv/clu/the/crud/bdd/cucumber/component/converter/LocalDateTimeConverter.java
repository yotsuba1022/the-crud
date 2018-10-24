package idv.clu.the.crud.bdd.cucumber.component.converter;

import cucumber.deps.com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Carl Lu
 */
public class LocalDateTimeConverter extends AbstractSingleValueConverter {

    @Override
    public boolean canConvert(Class type) {
        return type.equals(LocalDateTime.class);
    }

    @Override
    public Object fromString(String localDateTimeStr) {
        return LocalDateTime.parse(localDateTimeStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

}
