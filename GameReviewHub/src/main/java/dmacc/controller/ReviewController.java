package dmacc.controller;

import dmacc.beans.Game;
import dmacc.beans.Review;
import dmacc.repository.GameRepository;
import dmacc.repository.ReviewRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * The ReviewController class is responsible for handling HTTP requests related to reviews.
 * It provides methods for listing reviews by game or user, creating new reviews, updating existing reviews,
 * deleting reviews, and handling error cases.
 */
@Controller
@RequestMapping("/reviews")
public class ReviewController {

	private final ReviewRepository reviewRepository;

	private final GameRepository gameRepository;

	public ReviewController(ReviewRepository reviewRepository, GameRepository gameRepository) {
		this.reviewRepository = reviewRepository;
		this.gameRepository = gameRepository;
	}

	// List reviews for a particular game
	@GetMapping("/list/{gameId}")
	public String listReviewsByGame(@PathVariable Long gameId, Model model) {
		model.addAttribute("reviews", reviewRepository.findByGame_GameId(gameId));
		return "review-list"; // review-list.html
	}

	// List reviews by a particular user
	@GetMapping("/list/user/{userId}")
	public String listReviewsByUser(@PathVariable Long userId, Model model) {
		model.addAttribute("reviews", reviewRepository.findByUser_UserId(userId));
		return "review-list"; // review-list.html
	}

	// Form for creating a new review
	@GetMapping("/new/{gameId}")
	public String newReview(@PathVariable Long gameId, Model model) {
		Optional<Game> game = gameRepository.findById(gameId);
		if (game.isPresent()) {
			model.addAttribute("game", game.get());
			model.addAttribute("review", new Review());
			return "review-form"; // review-form.html
		}
		return "redirect:/error"; // error.html
	}

	// Form for updating an existing review
	@GetMapping("/edit/{reviewId}")
	public String editReview(@PathVariable Long reviewId, Model model) {
		Optional<Review> review = reviewRepository.findById(reviewId);
		if (review.isPresent()) {
			model.addAttribute("review", review.get());
			return "review-form"; // review-form.html
		}
		return "redirect:/error"; // error.html
	}

	// Saving a new or updated review
	@PostMapping("/save")
	public String saveReview(@ModelAttribute Review review) {
		reviewRepository.save(review);
		if (review.getGame() != null) {
			return "redirect:/reviews/list/" + review.getGame().getGameId(); // review-list.html
		} else {
			// handle the error cases by redirecting to an error page
			return "redirect:/error"; // error.html
		}
	}

	// Deleting a review
	@GetMapping("/delete/{reviewId}")
	public String deleteReview(@PathVariable Long reviewId) {
		Optional<Review> review = reviewRepository.findById(reviewId);
		if (review.isPresent()) {
			Review reviewObj = review.get();
			Game game = reviewObj.getGame();
			if (game != null) {
				reviewRepository.delete(reviewObj);
				return "redirect:/reviews/list/" + game.getGameId(); // review-list.html
			} else {
				// Add a log statement to help debug why game is null
				System.err.println("Game is null for reviewId: " + reviewId);
				// Since the game is null, redirect to a generic reviews list page or another
				// page that makes sense in your application
				return "redirect:/reviews/list"; // review-list.html
			}
		}
		return "redirect:/error"; // error.html
	}

	// Redirect to error page
	@GetMapping
	public String showErrorPage() {
		return "error";
	} // error.html
}
