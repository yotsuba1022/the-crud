package idv.clu.the.crud.module.user.repository;

import idv.clu.the.crud.module.user.model.Gender;
import idv.clu.the.crud.module.user.model.User;
import org.jasypt.util.password.BasicPasswordEncryptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserRepositoryTest {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BasicPasswordEncryptor passwordEncryptor;

    @Test
    public void testCreateAndGetById() {
        User input = User.builder()
                .username("yotsuba1022")
                .password(passwordEncryptor.encryptPassword("!QAZxsw2"))
                .firstName("Carl")
                .lastName("Lu")
                .birthday(Timestamp.valueOf(LocalDateTime.of(1988, 10, 22, 23, 5, 5)))
                .age(30)
                .gender(Gender.MALE)
                .registrationDate(Timestamp.valueOf(LocalDateTime.of(2018, 10, 16, 23, 2, 10)))
                .build();

        userRepository.create(input);
        long latestId = userRepository.count();
        User expected = input.toBuilder().id(latestId).build();

        UserQueryCriteria queryCriteria = new UserQueryCriteria.Builder()
                .setOrderBy("id")
                .isDesc(true)
                .setLimit("10")
                .setOffset("0")
                .build();

        // After insert, the auto-generated ID should be set to ID field. (Holy Mybatis)
        User actual = userRepository.getByQueryCriteria(queryCriteria)
                .stream()
                .filter(user -> user.getId() == latestId)
                .findFirst()
                .get();

        assertEquals(expected, actual);
    }

    @Test
    public void testGetByQueryCriteria() {
        User input = User.builder()
                .username("clu")
                .password(passwordEncryptor.encryptPassword("55667788"))
                .firstName("Carl")
                .lastName("Lu")
                .birthday(Timestamp.valueOf(LocalDateTime.of(1988, 10, 22, 23, 5, 5)))
                .age(30)
                .gender(Gender.MALE)
                .registrationDate(Timestamp.valueOf(LocalDateTime.of(2018, 10, 16, 23, 2, 10)))
                .build();

        userRepository.create(input);
        long latestId = userRepository.count();
        User expected = input.toBuilder().id(latestId).build();

        UserQueryCriteria queryCriteria = new UserQueryCriteria.Builder()
                .setUsername(expected.getUsername())
                .setOrderBy("id")
                .isDesc(true)
                .setLimit("10")
                .setOffset("0")
                .build();

        User actual = userRepository.getByQueryCriteria(queryCriteria)
                .stream()
                .filter(user -> user.getId() == expected.getId())
                .findFirst()
                .get();

        assertEquals(expected, actual);
    }

}
