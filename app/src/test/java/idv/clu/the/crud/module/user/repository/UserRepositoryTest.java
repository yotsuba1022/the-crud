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
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


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
        var updatedRow = userRepository.create(input);
        assertTrue(updatedRow > 0);

        User expected = input.toBuilder().id(input.getId()).build();

        User actual = userRepository.getById(userRepository.getLastUserId());
        assertEquals(expected, actual);
    }

    @Test
    public void testUpdate() {
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
        var updatedRow = userRepository.create(input);
        assertTrue(updatedRow > 0);

        User updatedUser = input.toBuilder()
                .age(35)
                .build();
        updatedRow = userRepository.update(updatedUser);
        assertTrue(updatedRow > 0);

        User actual = userRepository.getById(updatedUser.getId());
        assertEquals(updatedUser, actual);
    }

    @Test
    public void testSoftDelete() {
        User input = User.builder()
                .username("delete_me")
                .password(passwordEncryptor.encryptPassword("!QAZxsw2"))
                .firstName("Delete")
                .lastName("Me")
                .birthday(Timestamp.valueOf(LocalDateTime.of(1988, 10, 22, 23, 5, 5)))
                .age(10)
                .gender(Gender.MALE)
                .registrationDate(Timestamp.valueOf(LocalDateTime.of(2018, 10, 16, 23, 2, 10)))
                .build();
        var affectedRow = userRepository.create(input);
        assertTrue(affectedRow > 0);

        affectedRow = userRepository.softDelete(input.getId());
        assertTrue(affectedRow > 0);

        UserQueryCriteria queryCriteria = new UserQueryCriteria.Builder()
                .setUsername(input.getUsername())
                .setOrderBy("id")
                .isDesc(true)
                .setLimit("10")
                .setOffset("0")
                .isDeleted(true)
                .build();

        User actual = userRepository.getByQueryCriteria(queryCriteria)
                .stream()
                .filter(user -> user.getId() == input.getId())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No user found with the given criteria"));

        assertNotNull(actual);
    }

    @Test
    public void testDelete() {
        User input = User.builder()
                .username("delete_me_permanently")
                .password(passwordEncryptor.encryptPassword("!QAZxsw2"))
                .firstName("Delete")
                .lastName("Me")
                .birthday(Timestamp.valueOf(LocalDateTime.of(1988, 10, 22, 23, 5, 5)))
                .age(15)
                .gender(Gender.MALE)
                .registrationDate(Timestamp.valueOf(LocalDateTime.of(2018, 10, 16, 23, 2, 10)))
                .build();
        var affectedRow = userRepository.create(input);
        assertTrue(affectedRow > 0);

        affectedRow = userRepository.delete(input.getId());
        assertTrue(affectedRow > 0);

        UserQueryCriteria queryCriteria = new UserQueryCriteria.Builder()
                .setUsername(input.getUsername())
                .setOrderBy("id")
                .isDesc(true)
                .setLimit("10")
                .setOffset("0")
                .build();

        var result = userRepository.getByQueryCriteria(queryCriteria);

        assertTrue(result.isEmpty());
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

        var updatedRow = userRepository.create(input);
        assertTrue(updatedRow > 0);
        User expected = input.toBuilder().id(input.getId()).build();

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
                .orElseThrow(() -> new NoSuchElementException("No user found with the given criteria"));

        assertEquals(expected, actual);
    }

}
