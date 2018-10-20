package idv.clu.the.crud.module.user.service;

import idv.clu.the.crud.module.user.dto.UserDto;
import idv.clu.the.crud.module.user.model.User;

import java.util.List;

/**
 * @author Carl Lu
 * <p>
 * Define the basic operations for {@link User} domain object.
 */
public interface UserService {

    long createUser(User user);

    UserDto getUserById(long id);

    User getUserByUsername(String username);

    List<User> getUserList(int limit, int offset);

    long updateUser(User user);

    long deleteUser(long id);

    long addToAdmin(long id);

    long removeFromAdmin(long id);

    long addToVip(long id);

    long removeFromVip(long id);

    long addToTest(long id);

    long removeFromTest(long id);

    long suspend(long id);

    long reactivate(long id);

}
