package idv.clu.the.crud.module.user.service;

import idv.clu.the.crud.module.user.model.User;
import idv.clu.the.crud.module.user.repository.UserRepository;
import org.jasypt.encryption.StringEncryptor;
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
    private final StringEncryptor stringEncryptor;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public UserServiceImpl(final UserRepository userRepository, final StringEncryptor stringEncryptor) {
        this.userRepository = userRepository;
        this.stringEncryptor = stringEncryptor;
    }

    @Override
    public long createUser(User user) {
        user.setPassword(stringEncryptor.encrypt(user.getPassword()));
        return userRepository.create(user);
    }

    @Override
    public User getUserById(long id) {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    @Override
    public List<User> getUserList(int limit, int offset) {
        return null;
    }

    @Override
    public long updateUser(User user) {
        return 0;
    }

    @Override
    public long deleteUser(long id) {
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
