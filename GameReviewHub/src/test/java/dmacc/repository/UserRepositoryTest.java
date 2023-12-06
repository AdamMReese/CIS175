/**
 * @author Aaron Carpenter - acarpenter5@dmacc.edu
 * CIS175 - Fall 2023
 * Nov 13, 2023
 */

package dmacc.repository;

import dmacc.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private User testUser;

    @BeforeEach
    public void setUp() {
        testUser = new User("testuser", "testpassword");
        userRepository.save(testUser);
    }

    @AfterEach
    public void tearDown() {
        userRepository.delete(testUser);
    }

    @Test
    void testExistsByUsername() {
        assertTrue(userRepository.existsByUsername("testuser"));
        assertFalse(userRepository.existsByUsername("nonexistentuser"));
    }

    @Test
    void testFindByUsernameAndPassword() {
        User foundUser = userRepository.findByUsernameAndPassword("testuser", "testpassword");
        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
        assertEquals("testpassword", foundUser.getPassword());

        assertNull(userRepository.findByUsernameAndPassword("nonexistentuser", "password"));
        assertNull(userRepository.findByUsernameAndPassword("testuser", "wrongpassword"));
    }

    @Test
    void testFindByUsername() {
        User foundUser = userRepository.findByUsername("testuser");
        assertNotNull(foundUser);
        assertEquals("testuser", foundUser.getUsername());
        assertEquals("testpassword", foundUser.getPassword());

        assertNull(userRepository.findByUsername("nonexistentuser"));
    }
}
