package com.gamereviewhub.gamereviewhub.controller;

import com.gamereviewhub.gamereviewhub.model.Game;
import com.gamereviewhub.gamereviewhub.model.Review;
import com.gamereviewhub.gamereviewhub.repository.GameRepository;
import com.gamereviewhub.gamereviewhub.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private GameRepository gameRepository;

	// List reviews for a particular game
	@GetMapping("/list/{gameId}")
	public String listReviewsByGame(@PathVariable Long gameId, Model model) {
		model.addAttribute("reviews", reviewRepository.findByGame_GameId(gameId));
		return "review-list";
	}

	// List reviews by a particular user
	@GetMapping("/list/user/{userId}")
	public String listReviewsByUser(@PathVariable Long userId, Model model) {
		model.addAttribute("reviews", reviewRepository.findByUser_UserId(userId));
		return "review-list";
	}

	// Form for creating a new review
	@GetMapping("/new/{gameId}")
	public String newReview(@PathVariable Long gameId, Model model) {
		Optional<Game> game = gameRepository.findById(gameId);
		if (game.isPresent()) {
			model.addAttribute("game", game.get());
			model.addAttribute("review", new Review());
			return "review-form";
		}
		return "redirect:/error";
	}

	// Form for updating an existing review
	@GetMapping("/edit/{reviewId}")
	public String editReview(@PathVariable Long reviewId, Model model) {
		Optional<Review> review = reviewRepository.findById(reviewId);
		if (review.isPresent()) {
			model.addAttribute("review", review.get());
			return "review-form";
		}
		return "redirect:/error";
	}

	// Saving a new or updated review
	@PostMapping("/save")
	public String saveReview(@ModelAttribute Review review) {
		reviewRepository.save(review);
		if (review.getGame() != null) {
			return "redirect:/reviews/list/" + review.getGame().getGameId();
		} else {
			// handle the error cases by redirecting to an error page
			return "redirect:/error";
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
				return "redirect:/reviews/list/" + game.getGameId();
			} else {
				// Add a log statement to help debug why game is null
				System.err.println("Game is null for reviewId: " + reviewId);
				// Since the game is null, redirect to a generic reviews list page or another
				// page that makes sense in your application
				return "redirect:/reviews/list";
			}
		}
		return "redirect:/error";
	}

	// Redirect to error page
	@GetMapping
	public String showErrorPage() {
		return "error";
	}

}
