package idv.clu.the.crud.module.user.service;

import idv.clu.the.crud.module.user.model.User;
import idv.clu.the.crud.module.user.repository.UserQueryCriteria;
import idv.clu.the.crud.module.user.repository.UserRepository;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Carl Lu
 * <p>
 * The implementation of {@link UserService}.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BasicPasswordEncryptor passwordEncryptor;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public UserServiceImpl(final UserRepository userRepository, final BasicPasswordEncryptor passwordEncryptor) {
        this.userRepository = userRepository;
        this.passwordEncryptor = passwordEncryptor;
    }

    @Override
    public long create(final User user) {
        user.setPassword(passwordEncryptor.encryptPassword(user.getPassword()));
        userRepository.create(user);
        return user.getId();
    }

    @Override
    public List<User> getByQueryCriteria(final UserQueryCriteria queryCriteria) {
        return userRepository.getByQueryCriteria(queryCriteria);
    }

    @Override
    public List<User> getUsers(int limit, int offset) {
        return null;
    }

    @Override
    public long update(User user) {
        return 0;
    }

    @Override
    public long delete(long id) {
        return 0;
    }

    @Override
    public long addToAdmin(long id) {
        return 0;
    }

    @Override
    public long removeFromAdmin(long id) {
        return 0;
    }

    @Override
    public long addToVip(long id) {
        return 0;
    }

    @Override
    public long removeFromVip(long id) {
        return 0;
    }

    @Override
    public long addToTest(long id) {
        return 0;
    }

    @Override
    public long removeFromTest(long id) {
        return 0;
    }

    @Override
    public long suspend(long id) {
        return 0;
    }

    @Override
    public long reactivate(long id) {
        return 0;
    }

}
