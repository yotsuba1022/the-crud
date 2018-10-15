package idv.clu.the.crud.module.user.model;

import idv.clu.the.crud.module.user.dto.UserDto;

import java.time.LocalDateTime;

/**
 * @author Carl Lu
 */
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

    public User() {
    }

    private User(Builder builder) {
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

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDateTime getBirthday() {
        return birthday;
    }

    public int getAge() {
        return age;
    }

    public Gender getGender() {
        return gender;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isVip() {
        return isVip;
    }

    public boolean isTest() {
        return isTest;
    }

    public boolean isSuspended() {
        return isSuspended;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username='" + username + '\'' + '\'' + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\'' + ", birthday=" + birthday + ", age=" + age + ", gender=" + gender
                + ", registrationDate=" + registrationDate + ", isAdmin=" + isAdmin + ", isVip=" + isVip + ", isTest=" + isTest
                + ", isSuspended=" + isSuspended + '}';
    }

    public static class Builder {
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

        User build() {
            return new User(this);
        }

        Builder setUsername(String username) {
            this.username = username;
            return this;
        }

        Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        Builder setBirthday(LocalDateTime birthday) {
            this.birthday = birthday;
            return this;
        }

        Builder setAge(int age) {
            this.age = age;
            return this;
        }

        Builder setGender(Gender gender) {
            this.gender = gender;
            return this;
        }

        Builder setRegistrationDate(LocalDateTime registrationDate) {
            this.registrationDate = registrationDate == null ? LocalDateTime.now() : registrationDate;
            return this;
        }

        Builder setAdmin(boolean admin) {
            isAdmin = admin;
            return this;
        }

        Builder setVip(boolean vip) {
            isVip = vip;
            return this;
        }

        Builder setTest(boolean test) {
            isTest = test;
            return this;
        }

        Builder setSuspended(boolean suspended) {
            isSuspended = suspended;
            return this;
        }
    }

}
