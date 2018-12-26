package idv.clu.the.crud.module.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import idv.clu.the.crud.module.user.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

/**
 * @author Carl Lu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {

    private String firstName;

    private String lastName;

    @Past(message = "back to the future!!!")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime birthday;

    @PositiveOrZero(message = "age should be positive or zero.")
    private int age;

    private Gender gender;

}
