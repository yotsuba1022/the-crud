package idv.clu.the.crud.module.user.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import idv.clu.the.crud.module.user.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;

/**
 * @author Carl Lu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private long id;

    @NotNull(message = "username is a required field.")
    private String username;

    @NotNull(message = "password is a required field.")
    private String password;

    @NotNull(message = "firstName is a required field.")
    private String firstName;

    @NotNull(message = "lastName is a required field.")
    private String lastName;

    @NotNull(message = "birthday is a required field.")
    @Past
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime birthday;

    @NotNull(message = "age is a required field.")
    @PositiveOrZero
    private int age;

    @NotNull(message = "gender is a required field.")
    private Gender gender;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registrationDate;

    private boolean isAdmin;

    private boolean isVip;

    private boolean isTest;

    private boolean isSuspended;

}
