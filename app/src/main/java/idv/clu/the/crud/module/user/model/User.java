package idv.clu.the.crud.module.user.model;

import idv.clu.the.crud.module.user.dto.UpdateUserDto;
import idv.clu.the.crud.module.user.dto.UserDto;
import idv.clu.the.crud.module.user.util.TimeInstance;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Domain object for USER table.
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderClassName = "Builder", toBuilder = true)
@Data
public class User {
    private long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private Timestamp birthday;
    private int age;
    private Gender gender;
    private Timestamp registrationDate;
    private boolean isAdmin;
    private boolean isVip;
    private boolean isTest;
    private boolean isSuspended;

    /**
     * Creates a User from a UserDto.
     */
    public static User from(UserDto userDto) {
        return User.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .birthday(Timestamp.valueOf(userDto.getBirthday()))
                .age(userDto.getAge())
                .gender(userDto.getGender())
                .registrationDate(userDto.getRegistrationDate() != null ?
                        Timestamp.valueOf(userDto.getRegistrationDate()) :
                        TimeInstance.ISO8601TW.getTimestampFromLocalDateTime(LocalDateTime.now()))
                .isAdmin(userDto.isAdmin())
                .isVip(userDto.isVip())
                .isTest(userDto.isTest())
                .isSuspended(userDto.isSuspended())
                .build();
    }

    /**
     * Updates the current User based on an UpdateUserDto.
     */
    public User updateBy(UpdateUserDto updateUserDto) {
        if (StringUtils.hasText(updateUserDto.getFirstName())) {
            this.setFirstName(updateUserDto.getFirstName());
        }

        if (StringUtils.hasText(updateUserDto.getLastName())) {
            this.setLastName(updateUserDto.getLastName());
        }

        if (updateUserDto.getBirthday() != null) {
            this.setBirthday(TimeInstance.ISO8601TW.getTimestampFromLocalDateTime(updateUserDto.getBirthday()));
        }

        if (updateUserDto.getAge() > 0) {
            this.setAge(updateUserDto.getAge());
        }

        if (updateUserDto.getGender() != null) {
            this.setGender(updateUserDto.getGender());
        }

        if (updateUserDto.isAdmin() != this.isAdmin) {
            this.setAdmin(updateUserDto.isAdmin());
        }

        if (updateUserDto.isVip() != this.isVip) {
            this.setVip(updateUserDto.isVip());
        }

        if (updateUserDto.isTest() != this.isTest) {
            this.setTest(updateUserDto.isTest());
        }

        if (updateUserDto.isSuspended() != this.isSuspended) {
            this.setSuspended(updateUserDto.isSuspended());
        }

        return this;
    }
}
