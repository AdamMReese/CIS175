/**
 * @author Aaron Carpenter - acarpenter5@dmacc.edu
 * CIS175 - Fall 2023
 * Nov 13, 2023
 */

package dmacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dmacc.beans.User;

/**
 * This interface represents a repository for managing User entities.
 * It extends the JpaRepository interface, providing CRUD operations for User entities.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	/**
	 * Checks if a user with the given username exists.
	 *
	 * @param username the username to check
	 * @return true if a user with the given username exists, false otherwise
	 */
	boolean existsByUsername(String username);

	/**
	 * Finds a user by their username and password.
	 *
	 * @param username the username of the user
	 * @param password the password of the user
	 * @return the user with the given username and password, or null if not found
	 */
	User findByUsernameAndPassword(String username, String password);

	/**
	 * Finds a user by their username.
	 *
	 * @param username the username of the user
	 * @return the user with the given username, or null if not found
	 */
	User findByUsername(String username);
}
