package idv.clu.the.crud.bdd.module.user.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import cucumber.deps.com.thoughtworks.xstream.annotations.XStreamConverter;
import idv.clu.the.crud.bdd.cucumber.component.converter.LocalDateTimeConverter;
import idv.clu.the.crud.bdd.cucumber.component.converter.StringToGenderConverter;
import idv.clu.the.crud.module.user.model.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Carl Lu
 */
@NoArgsConstructor
@Data
public class UpdateUser {

    private String firstName;
    private String lastName;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @XStreamConverter(LocalDateTimeConverter.class)
    private LocalDateTime birthday;

    private int age;

    @XStreamConverter(StringToGenderConverter.class)
    private Gender gender;

}
