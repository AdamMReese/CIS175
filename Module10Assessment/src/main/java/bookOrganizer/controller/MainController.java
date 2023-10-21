/**
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Oct 20, 2023
 */

package bookOrganizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// This is the controller for the main pages of the application
@Controller
public class MainController {

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/owner-dashboard")
	public String ownerDashboard() {
		return "ownerDashboard";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@GetMapping("/shelf")
	public String shelf() {
		return "shelf";
	}

}
