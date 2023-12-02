/**
 * @author Aaron Carpenter - acarpenter5@dmacc.edu
 * CIS175 - Fall 2023
 * Nov 13, 2023
 */

package dmacc.controller;

import dmacc.beans.User;
import dmacc.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The UserController class handles the HTTP requests related to user management.
 */
@Controller
public class UserController {

	@Autowired
	private UserRepository userRepository;

	// 
	@GetMapping("/createAccount")
	public String showCreateAccountForm() {
		return "accountCreation";
	}

	@PostMapping("/createAccount")
	public String createAccount(@RequestParam String username, @RequestParam String password) {
		if (userRepository.existsByUsername(username)) {
			return "redirect:/Error.html";
		} else {
			User user = new User(username, password);
			userRepository.save(user);
			return "redirect:/Index.html";
		}
	}

	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, HttpSession session,
			Model model) {
		User user = userRepository.findByUsernameAndPassword(username, password);
		if (user != null) {
			session.setAttribute("userId", user.getUserId());
			return "redirect:/Dashboard.html";
		} else {
			return "redirect:/ErrorUserPass.html";
		}
	}

}
