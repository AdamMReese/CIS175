package com.gamereviewhub.gamereviewhub;

import com.gamereviewhub.gamereviewhub.model.Game;
import com.gamereviewhub.gamereviewhub.model.Review;
import com.gamereviewhub.gamereviewhub.repository.GameRepository;
import com.gamereviewhub.gamereviewhub.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ReviewRepositoryTest {

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private GameRepository gameRepository;

	@Test
	public void testFindByGameId() {
		try {
			// Save a game entity
			Game game = new Game();
			gameRepository.save(game);

			// Create and save two reviews associated with the game
			Review review1 = new Review();
			review1.setGame(game);
			reviewRepository.save(review1);

			Review review2 = new Review();
			review2.setGame(game);
			reviewRepository.save(review2);

			// Test the findByGame_GameId method
			List<Review> reviews = reviewRepository.findByGame_GameId(game.getGameId());
			assertEquals(2, reviews.size());

		} catch (Exception e) {
			// Log or print the exception
			e.printStackTrace();
			// Optionally, rethrow the exception if you want the test to fail on exceptions
			throw e;
		}
	}
}
