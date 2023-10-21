/**
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Oct 20, 2023
 */

package bookOrganizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// This is the controller for the view pages of the application
@Controller
public class ViewController {

	// This is the view for the index page
	@GetMapping("/manage-owners")
	public String manageOwners() {
		return "manage-owners";
	}
}