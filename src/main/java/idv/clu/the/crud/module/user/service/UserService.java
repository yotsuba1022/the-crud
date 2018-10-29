package idv.clu.the.crud.module.user.service;

import idv.clu.the.crud.module.user.model.User;
import idv.clu.the.crud.module.user.repository.UserQueryCriteria;

import java.util.List;

/**
 * @author Carl Lu
 * <p>
 * Define the basic operations for {@link User} domain object.
 */
public interface UserService {

    long create(User user);

    List<User> getByQueryCriteria(UserQueryCriteria queryCriteria);

    long update(User user);

    long delete(long id);

    long addToAdmin(long id);

    long removeFromAdmin(long id);

    long addToVip(long id);

    long removeFromVip(long id);

    long addToTest(long id);

    long removeFromTest(long id);

    long suspend(long id);

    long reactivate(long id);

}
