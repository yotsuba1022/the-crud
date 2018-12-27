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
 * Repository for USER table in database, implemented by Mybatis mapper.
 */
@Mapper
public interface UserRepository {

    @Insert("INSERT INTO USER(username, password, first_name, last_name, birthday, age, gender, registration_date, "
            + "is_admin, " + "is_vip, is_test, is_suspended) "
            + "VALUES(#{username}, #{password}, #{firstName}, #{lastName}, #{birthday}, #{age}, #{gender}, "
            + "#{registrationDate}," + " #{isAdmin}, #{isVip}, #{isTest}, #{isSuspended})")
    @Options(useGeneratedKeys = true, keyColumn = "id")
    long create(User user);

    @Select("SELECT * from USER where id = #{id}")
    User getById(long id);

    @SelectProvider(type = UserProvider.class, method = "getByQueryCriteria")
    List<User> getByQueryCriteria(UserQueryCriteria queryCriteria);

    @Update({"UPDATE USER SET " + "username = #{username}," + "first_name = #{firstName}," + "last_name = #{lastName},"
            + "birthday = #{birthday}," + "age = #{age}," + "gender = #{gender} WHERE id = #{id}"})
    long update(User user);

    @Delete("DELETE from USER where id = #{id}")
    long delete(long id);

}
