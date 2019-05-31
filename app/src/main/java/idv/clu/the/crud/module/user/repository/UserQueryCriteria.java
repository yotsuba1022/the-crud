package idv.clu.the.crud.module.user.repository;

import idv.clu.the.crud.module.user.model.Gender;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

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
    private String gender;
    private boolean isVip;
    private String orderBy;
    private boolean isDesc;
    private String limit;
    private String offset;

    private UserQueryCriteria(final Builder builder) {
        this.id = builder.id;
        this.username = builder.username;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.age = builder.age;
        this.gender = builder.gender;
        this.isVip = builder.isVip;
        this.orderBy = builder.orderBy;
        this.isDesc = builder.isDesc;
        this.limit = builder.limit;
        this.offset = builder.offset;
    }

    public static class Builder {

        private Long id;
        private String username;
        private String firstName;
        private String lastName;
        private Integer age;
        private String gender;
        private boolean isVip;
        private String orderBy;
        private boolean isDesc;
        private String limit;
        private String offset;

        public UserQueryCriteria build() {
            return new UserQueryCriteria(this);
        }

        public Builder setId(Long id) {
            if (null != id && id <= 0) {
                throw new IllegalArgumentException("Invalid criteria parameter: id");
            }
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
            if (null != age && age <= 0) {
                throw new IllegalArgumentException("Invalid criteria parameter: age");
            }
            this.age = age;
            return this;
        }

        public Builder setGender(String genderStr) {
            if (!StringUtils.isEmpty(genderStr)) {
                Gender gender = Gender.from(genderStr);
                this.gender = gender.toString();
            }
            return this;
        }

        public Builder isVip(boolean vip) {
            isVip = vip;
            return this;
        }

        public Builder setOrderBy(String orderBy) {
            this.orderBy = orderBy;
            return this;
        }

        public Builder isDesc(boolean desc) {
            isDesc = desc;
            return this;
        }

        public Builder setLimit(String limit) {
            this.limit = limit;
            return this;
        }

        public Builder setOffset(String offset) {
            this.offset = offset;
            return this;
        }

    }

}
