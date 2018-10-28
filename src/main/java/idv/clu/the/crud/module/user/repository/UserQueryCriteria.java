package idv.clu.the.crud.module.user.repository;

import idv.clu.the.crud.module.user.model.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Carl Lu
 */
@NoArgsConstructor
@Data
public class UserQueryCriteria {

    private Long id;
    private String username;
    private String firstName;
    private String lastName;
    private Integer age;
    private Gender gender;
    private boolean isAdmin;
    private boolean isVip;
    private boolean isTest;
    private boolean isSuspended;

    private UserQueryCriteria(final Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.gender = builder.gender;
        this.isAdmin = builder.isAdmin;
        this.isVip = builder.isVip;
        this.isTest = builder.isTest;
        this.isSuspended = builder.isSuspended;
    }

    public static class Builder {

        private Long id;
        private String username;
        private String firstName;
        private String lastName;
        private int age;
        private Gender gender;
        private boolean isAdmin;
        private boolean isVip;
        private boolean isTest;
        private boolean isSuspended;

        public UserQueryCriteria build() {
            return new UserQueryCriteria(this);
        }

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setUsername(String username) {
            this.username = username;
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

        public Builder setAge(Integer age) {
            this.age = age;
            return this;
        }

        public Builder setGender(Gender gender) {
            this.gender = gender;
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
