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
    public User getById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public List<User> getByQueryCriteria(final UserQueryCriteria queryCriteria) {
        return userRepository.getByQueryCriteria(queryCriteria);
    }

    @Override
    public long update(User updatedUser) {
        return userRepository.update(updatedUser);
    }

    @Override
    public long softDelete(long id) {
        return userRepository.softDelete(id);
    }

    @Override
    public long delete(long id) {
        return userRepository.delete(id);
    }

    @Override
    public long getLastUserId() {
        Long lastUserId = userRepository.getLastUserId();
        return lastUserId == null ? 0 : lastUserId;
    }

}
