package idv.clu.the.crud.module.user.service;

import idv.clu.the.crud.module.user.model.User;
import idv.clu.the.crud.module.user.repository.product.ProductRepository;
import idv.clu.the.crud.module.user.repository.user.UserQueryCriteria;
import idv.clu.the.crud.module.user.repository.user.UserRepository;
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
    private final ProductRepository productRepository;
    private final BasicPasswordEncryptor passwordEncryptor;

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    public UserServiceImpl(final UserRepository userRepository, final ProductRepository productRepository,
            final BasicPasswordEncryptor passwordEncryptor) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
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
        User user = productRepository.getById(id);
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
    public long delete(long id) {
        return userRepository.delete(id);
    }

}
