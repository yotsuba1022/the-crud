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

    User getById(long id);

    List<User> getByQueryCriteria(UserQueryCriteria queryCriteria);

    long update(User user);

    long softDelete(long id);

    long delete(long id);

    long getLastUserId();

}
