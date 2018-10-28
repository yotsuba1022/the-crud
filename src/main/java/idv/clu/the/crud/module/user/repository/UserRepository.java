package idv.clu.the.crud.module.user.repository;

import idv.clu.the.crud.module.user.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * @author Carl Lu
 * Repository for USER table in database, implemented by Mybatis mapper.
 */
@Mapper
public interface UserRepository {

    @Insert("INSERT INTO USER(username, password, first_name, last_name, birthday, age, gender, registration_date, is_admin, is_vip, is_test, is_suspended) "
            + "VALUES(#{username}, #{password}, #{firstName}, #{lastName}, #{birthday}, #{age}, #{gender}, #{registrationDate}, #{isAdmin}, #{isVip}, #{isTest}, #{isSuspended})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    long create(User user);

    @SelectProvider(type = UserProvider.class, method = "getByQueryCriteria")
    List<User> getByQueryCriteria(UserQueryCriteria queryCriteria);

}
