package dmacc.controller;

import dmacc.beans.Platform;
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
 * The PlatformController class handles HTTP requests related to platforms in the Game Review Hub application.
 */
@Controller
@RequestMapping("/platforms")
public class PlatformController {

	@Autowired
	private PlatformRepository platformRepository;

	@GetMapping("/list")
	public String listPlatforms(Model model) {
		List<Platform> platforms = platformRepository.findAll();
		model.addAttribute("platforms", platforms);
		return "list-platforms";
	}

	@GetMapping("/add")
	public String showAddForm(Model model) {
		model.addAttribute("platform", new Platform());
		return "add-update-platform";
	}

	@PostMapping("/add")
	public String addPlatform(@Valid @ModelAttribute Platform platform, BindingResult result) {
		if (result.hasErrors()) {
			return "add-update-platform";
		}
		platformRepository.save(platform);
		return "redirect:/platforms/list";
	}

	@GetMapping("/view/{id}")
	public String viewPlatform(@PathVariable Long id, Model model) {
		Optional<Platform> optionalPlatform = platformRepository.findById(id);
		if (optionalPlatform.isPresent()) {
			model.addAttribute("platform", optionalPlatform.get());
			return "view-platform";
		} else {
			return "redirect:/error";
		}
	}

	@GetMapping("/edit/{id}")
	public String editPlatform(@PathVariable Long id, Model model) {
		Optional<Platform> optionalPlatform = platformRepository.findById(id);
		if (optionalPlatform.isPresent()) {
			model.addAttribute("platform", optionalPlatform.get());
			return "add-update-platform";
		} else {
			return "redirect:/error";
		}
	}

	@PostMapping("/delete/{id}")
	public String deletePlatform(@PathVariable Long id) {
		platformRepository.deleteById(id);
		return "redirect:/platforms/list";
	}
}
