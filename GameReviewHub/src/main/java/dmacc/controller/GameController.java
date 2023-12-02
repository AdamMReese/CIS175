package dmacc.controller;

import dmacc.beans.Game;
import dmacc.beans.Platform;
import dmacc.repository.GameRepository;
import dmacc.repository.PlatformRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * The GameController class is responsible for handling HTTP requests related to games.
 * It provides methods for listing games, adding a new game, viewing a game, editing a game, and deleting a game.
 */
@Controller
@RequestMapping("/games")
public class GameController {

	@Autowired
	private GameRepository gameRepository;

	@Autowired
	private PlatformRepository platformRepository;

	@GetMapping("/list")
	public String listGames(Model model) {
		List<Game> games = gameRepository.findAll();
		model.addAttribute("games", games);
		return "list-games";
	}

	@GetMapping("/add")
	public String showAddForm(Model model) {
		List<Platform> platforms = platformRepository.findAll();
		model.addAttribute("platforms", platforms);
		model.addAttribute("game", new Game());
		return "add-update-game";
	}

	@PostMapping("/add")
	public String addGame(@Valid @ModelAttribute Game game, BindingResult result) {
		if (result.hasErrors()) {
			return "add-update-game";
		}
		gameRepository.save(game);
		return "redirect:/games/view/" + game.getGameId();
	}

	@GetMapping("/view/{id}")
	public String viewGame(@PathVariable Long id, Model model) {
		Optional<Game> optionalGame = gameRepository.findById(id);
		if (optionalGame.isPresent()) {
			model.addAttribute("game", optionalGame.get());
			return "view-game";
		} else {
			return "redirect:/error";
		}
	}

	@GetMapping("/edit/{id}")
	public String editGame(@PathVariable Long id, Model model) {
		Optional<Game> optionalGame = gameRepository.findById(id);
		if (optionalGame.isPresent()) {
			List<Platform> platforms = platformRepository.findAll();
			model.addAttribute("platforms", platforms);
			model.addAttribute("game", optionalGame.get());
			return "add-update-game";
		} else {
			return "redirect:/error";
		}
	}

	@PostMapping("/delete/{id}")
	public String deleteGame(@PathVariable Long id) {
		gameRepository.deleteById(id);
		return "redirect:/games/list";
	}
}
