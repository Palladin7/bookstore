package ua.com.alevel.bookstore.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.bookstore.entity.User;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private static final String email = "test_test@gmail.com";

    @BeforeEach
    public void init() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode("test"));
        user.setFirstName("Test");
        user.setLastName("Test");
        user.setRoles("USER");

        userRepository.save(user);
    }

    @AfterEach
    public void destroy() {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            userRepository.delete(user);
        }
    }

    @Test
    public void testCreateUser() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setEmail("john_hones@gmail.com");
        user.setPassword(passwordEncoder.encode("12345"));
        user.setFirstName("John");
        user.setLastName("Jones");
        user.setRoles("USER");

        User savedUser = userRepository.save(user);

        User existUser = userRepository.findById(savedUser.getId()).get();

        assertThat(existUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testFindUserByEmail() {
        User user = userRepository.findByEmail(email);
        assertThat(user).isNotNull();
    }

    @Test
    public void testUpdateUser() {
        String newEmail = "newEmail";

        User user = userRepository.findByEmail(email);
        user.setEmail(newEmail);

        userRepository.save(user);

        user = userRepository.findByEmail(newEmail);
        assertThat(user).isNotNull();
    }

    @Test
    public void testDeleteUser() {
        User user = userRepository.findByEmail(email);
        userRepository.delete(user);
        user = userRepository.findByEmail(email);

        assertThat(user).isNull();
    }
}
