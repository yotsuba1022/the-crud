package idv.clu.the.crud.module.user.repository;

import idv.clu.the.crud.module.user.model.Gender;
import idv.clu.the.crud.module.user.model.User;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserRepositoryTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BasicPasswordEncryptor passwordEncryptor;

    @Test
    public void testCreateAndGetById() {
        User expected = new User.Builder().setUsername("yotsuba1022")
                .setPassword(passwordEncryptor.encryptPassword("!QAZxsw2"))
                .setFirstName("Carl")
                .setLastName("Lu")
                .setBirthday(LocalDateTime.of(1988, 10, 22, 23, 5, 5))
                .setAge(30)
                .setGender(Gender.MALE)
                .setRegistrationDate(LocalDateTime.of(2018, 10, 16, 23, 2, 10))
                .build();

        // Before insert, the ID should be zero.
        assertEquals(0, expected.getId());

        userRepository.create(expected);

        // After insert, the auto-generated ID should be set to ID field. (Holy Mybatis)
        User actual = userRepository.getById(expected.getId());

        assertEquals(expected, actual);
    }

}
