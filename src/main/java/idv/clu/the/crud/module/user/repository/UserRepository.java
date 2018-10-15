package idv.clu.the.crud.module.user.repository;

import idv.clu.the.crud.module.user.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

/**
 * @author Carl Lu
 */
@Mapper
public interface UserRepository {

    @Insert("INSERT INTO USER(username, password, first_name, last_name, birthday, age, gender, registration_date, is_admin, is_vip, is_test, is_suspended) "
            + "VALUES(#{username}, #{password}, #{firstName}, #{lastName}, #{birthday}, #{age}, #{gender}, #{registrationDate}, #{isAdmin}, #{isVip}, #{isTest}, #{isSuspended})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    long createUser(User user);

}
