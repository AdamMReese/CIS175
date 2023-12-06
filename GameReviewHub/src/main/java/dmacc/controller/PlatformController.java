package dmacc.controller;

import dmacc.beans.Platform;
import dmacc.repository.PlatformRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The PlatformController class handles HTTP requests related to platforms in the Game Review Hub application.
 */
@Controller
@RequestMapping("/platforms")
public class PlatformController {

	private final PlatformRepository platformRepository;

	public PlatformController(PlatformRepository platformRepository) {
		this.platformRepository = platformRepository;
	}

	@GetMapping("/list")
	public String listPlatforms(Model model) {
		List<Platform> platforms = platformRepository.findAll();
		model.addAttribute("platforms", platforms);
		return "list-platforms"; // list-platforms.html
	}

	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("platform", new Platform());
		return "add-update-platform"; // add-update-platform.html
	}

	@PostMapping("/add")
	public String addPlatform(@Valid @ModelAttribute Platform platform, BindingResult result) {
		if (result.hasErrors()) {
			return "add-update-platform"; // add-update-platform.html
		}
		platformRepository.save(platform);
		return "redirect:/platforms/list"; // redirect to list-platforms.html
	}

	@GetMapping("/view/{id}")
	public String viewPlatform(@PathVariable Long id, Model model) {
		Optional<Platform> optionalPlatform = platformRepository.findById(id);
		if (optionalPlatform.isPresent()) {
			model.addAttribute("platform", optionalPlatform.get());
			return "view-platform"; // view-platform.html
		} else {
			return "redirect:/error"; // error.html
		}
	}

	@GetMapping("/edit/{id}")
	public String editPlatform(@PathVariable Long id, Model model) {
		Optional<Platform> optionalPlatform = platformRepository.findById(id);
		if (optionalPlatform.isPresent()) {
			model.addAttribute("platform", optionalPlatform.get());
			return "add-update-platform"; // add-update-platform.html
		} else {
			return "redirect:/error"; // error.html
		}
	}

	@PostMapping("/delete/{id}")
	public String deletePlatform(@PathVariable Long id) {
		platformRepository.deleteById(id);
		return "redirect:/platforms/list"; // redirect to list-platforms.html
	}
}
