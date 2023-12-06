/**
 * @author Aaron Carpenter - acarpenter5@dmacc.edu
 * CIS175 - Fall 2023
 * Nov 13, 2023
 */

package dmacc.controller;

import dmacc.beans.User;
import dmacc.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The UserController class handles the HTTP requests related to user management.
 */
@Controller
public class UserController {

	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@GetMapping("/createAccount")
	public String showCreateAccountForm() {
		return "account-creation"; // account-creation.html
	}

	@PostMapping("/createAccount")
	public String createAccount(@RequestParam String username, @RequestParam String password) {
		if (userRepository.existsByUsername(username)) {
			return "redirect:/error.html"; // error.html
		} else {
			User user = new User(username, password);
			userRepository.save(user);
			return "redirect:/index.html"; // redirect to index.html
		}
	}

	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, HttpSession session) {
		User user = userRepository.findByUsernameAndPassword(username, password);
		if (user != null) {
			session.setAttribute("userId", user.getUserId());
			return "redirect:/dashboard.html"; // redirect to dashboard.html
		} else {
			return "redirect:/error-user-pass.html"; // redirect to error-user-pass.html
		}
	}

}
