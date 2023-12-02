/**
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Dec 1, 2023
 */

package dmacc.controller;

import dmacc.beans.Game;
import dmacc.beans.Platform;
import dmacc.beans.Review;
import dmacc.repository.GameRepository;
import dmacc.repository.PlatformRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * This class is a controller that handles the views and actions related to game and platform data.
 */
@Controller
public class ViewController {

	private final GameRepository gameRepository;

	private final PlatformRepository platformRepository;

	public ViewController(GameRepository gameRepository, PlatformRepository platformRepository) {
		this.gameRepository = gameRepository;
		this.platformRepository = platformRepository;
	}

	// View all platforms in alphabetical order
	@GetMapping("/view/platforms")
	public String viewPlatforms(Model model) {
		List<Platform> platforms = platformRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
		model.addAttribute("platforms", platforms);
		return "view-platforms";
	}

	// View all games in alphabetical order
	@GetMapping("/view/games")
	public String viewGames(Model model) {
		List<Game> games = gameRepository.findAll(Sort.by(Sort.Direction.ASC, "title"));
		model.addAttribute("games", games);
		return "view-games";
	}

	// View a particular game and its reviews
	@GetMapping("/view/game/{id}")
	public String viewGame(@PathVariable("id") Long id, Model model) {
		Optional<Game> optionalGame = gameRepository.findById(id);
		if (optionalGame.isPresent()) {
			Game game = optionalGame.get();
			List<Review> reviews = game.getReviews().stream()
					.sorted(Comparator.comparing(Review::getId).reversed())
					.collect(Collectors.toList());
			model.addAttribute("game", game);
			model.addAttribute("reviews", reviews);
			return "view-game";
		} else {
			return "redirect:/error";
		}
	}
}
