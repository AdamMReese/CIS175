/**
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Oct 29, 2023
 */

package dmacc.repository;

import dmacc.beans.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The ReviewRepository interface provides methods for accessing and manipulating Review objects in the database.
 */
public interface ReviewRepository extends JpaRepository<Review, Long> {
	/**
	 * Retrieves a list of reviews associated with a specific game.
	 *
	 * @param gameId The ID of the game.
	 * @return A list of reviews associated with the specified game.
	 */
	List<Review> findByGame_GameId(Long gameId);

	/**
	 * Retrieves a list of reviews associated with a specific user.
	 *
	 * @param userId The ID of the user.
	 * @return A list of reviews associated with the specified user.
	 */
	List<Review> findByUser_UserId(Long userId);
}
