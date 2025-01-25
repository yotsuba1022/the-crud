package idv.clu.the.crud.module.user.repository;

import idv.clu.the.crud.module.user.model.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author Carl Lu
 * Repository for USER table in a database, implemented by Mybatis mapper.
 */
@Mapper
public interface UserRepository {

    @Insert("INSERT INTO USERS(username, password, first_name, last_name, birthday, age, gender, registration_date, "
            + "is_admin, is_vip, is_test, is_suspended) "
            + "VALUES(#{username}, #{password}, #{firstName}, #{lastName}, #{birthday}, #{age}, #{gender}, "
            + "#{registrationDate}, #{isAdmin}, #{isVip}, #{isTest}, #{isSuspended})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    long create(User user);

    @Select("SELECT * from USERS where id = #{id}")
    User getById(long id);

    @SelectProvider(type = UserProvider.class, method = "getByQueryCriteria")
    List<User> getByQueryCriteria(UserQueryCriteria queryCriteria);

    @Update("UPDATE USERS SET username = #{username}, first_name = #{firstName}, last_name = #{lastName},"
            + "birthday = #{birthday}, age = #{age}, gender = #{gender}, is_admin = #{isAdmin}, is_vip = #{isVip}, "
            + "is_test = #{isTest}, is_suspended = #{isSuspended} WHERE id = #{id}")
    long update(User user);

    @Delete("DELETE from USERS where id = #{id}")
    long delete(long id);

    @Select("SELECT count(*) from USERS")
    long count();

}
