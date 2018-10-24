package idv.clu.the.crud.module.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import idv.clu.the.crud.module.user.model.Gender;
import idv.clu.the.crud.module.user.model.User;
import idv.clu.the.crud.module.user.util.TimeInstance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

/**
 * @author Carl Lu
 * <p>
 * DTO object for {@link User} domain object.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private long id;

    @NotBlank(message = "username is a required field.")
    private String username;

    @NotNull(message = "password is a required field.")
    private String password;

    @NotNull(message = "firstName is a required field.")
    private String firstName;

    @NotNull(message = "lastName is a required field.")
    private String lastName;

    @NotNull(message = "birthday is a required field.")
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime birthday;

    @NotNull(message = "age is a required field.")
    @PositiveOrZero
    private int age;

    @NotNull(message = "gender is a required field.")
    private Gender gender;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime registrationDate;

    private boolean isAdmin;

    private boolean isVip;

    private boolean isTest;

    private boolean isSuspended;

    public static UserDto of(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setBirthday(TimeInstance.ISO8601TW.getLocalDateTimeFromTimestamp(user.getBirthday()));
        userDto.setAge(user.getAge());
        userDto.setGender(user.getGender());
        userDto.setRegistrationDate(TimeInstance.ISO8601TW.getLocalDateTimeFromTimestamp(user.getRegistrationDate()));
        userDto.setAdmin(user.isAdmin());
        userDto.setVip(user.isVip());
        userDto.setTest(user.isTest());
        userDto.setSuspended(user.isSuspended());
        return userDto;
    }

}
