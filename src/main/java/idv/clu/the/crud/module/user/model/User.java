package idv.clu.the.crud.module.user.model;

import idv.clu.the.crud.module.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Carl Lu
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    private long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDateTime birthday;
    private int age;
    private Gender gender;
    private LocalDateTime registrationDate;
    private boolean isAdmin;
    private boolean isVip;
    private boolean isTest;
    private boolean isSuspended;

    private User(Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.password = builder.password;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.birthday = builder.birthday;
        this.age = builder.age;
        this.gender = builder.gender;
        this.registrationDate = builder.registrationDate;
        this.isAdmin = builder.isAdmin;
        this.isVip = builder.isVip;
        this.isTest = builder.isTest;
        this.isSuspended = builder.isSuspended;
    }

    public static User from(UserDto userDto) {
        return new Builder().setUsername(userDto.getUsername())
                .setPassword(userDto.getPassword())
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setBirthday(userDto.getBirthday())
                .setAge(userDto.getAge())
                .setGender(userDto.getGender())
                .setRegistrationDate(userDto.getRegistrationDate())
                .setAdmin(userDto.isAdmin())
                .setVip(userDto.isVip())
                .setTest(userDto.isTest())
                .setSuspended(userDto.isSuspended())
                .build();
    }

    public static class Builder {
        private long id;
        private String username;
        private String password;
        private String firstName;
        private String lastName;
        private LocalDateTime birthday;
        private int age;
        private Gender gender;
        private LocalDateTime registrationDate;
        private boolean isAdmin;
        private boolean isVip;
        private boolean isTest;
        private boolean isSuspended;

        public User build() {
            return new User(this);
        }

        public Builder setId(long id) {
            this.id = id;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setBirthday(LocalDateTime birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        public Builder setRegistrationDate(LocalDateTime registrationDate) {
            this.registrationDate = registrationDate == null ? LocalDateTime.now() : registrationDate;
            return this;
        }

        public Builder setAdmin(boolean admin) {
            isAdmin = admin;
            return this;
        }

        public Builder setVip(boolean vip) {
            isVip = vip;
            return this;
        }

        public Builder setTest(boolean test) {
            isTest = test;
            return this;
        }

        public Builder setSuspended(boolean suspended) {
            isSuspended = suspended;
            return this;
        }
    }

}
