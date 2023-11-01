/**
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Oct 20, 2023
 */

package bookOrganizer.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bookOrganizer.beans.Owner;
import bookOrganizer.repository.OwnerRepository;
import jakarta.servlet.http.HttpSession;

// Controller for the owner-related pages
@Controller
@RequestMapping("/owners")
public class OwnerController {

	// Injecting the owner repository
	@Autowired
	private OwnerRepository ownerRepository;

	// Render the owner dashboard
	@GetMapping("/owner-dashboard")
	public String ownerDashboard(Model model, HttpSession session) {
		Long selectedOwnerId = (Long) session.getAttribute("selectedOwnerId");
		// If the selected owner's ID is in the session, display their information
		if (selectedOwnerId != null) {
			Optional<Owner> selectedOwner = ownerRepository.findById(selectedOwnerId);
			// If the selected owner exists, add them to the model
			if (selectedOwner.isPresent()) {
				model.addAttribute("selectedOwner", selectedOwner.get());
			} else { // Otherwise, remove the selected owner's ID from the session
				session.removeAttribute("selectedOwnerId");
			}
		}

		// Populate the list of all owners to be displayed in the dropdown
		List<Owner> owners = ownerRepository.findAll();
		model.addAttribute("owners", owners);

		return "owner-dashboard"; // Directs to the owner dashboard HTML page
	}

	// Handle the selection of an owner from the dropdown
	@PostMapping("/select-owner")
	public String selectOwner(@RequestParam("selectedOwnerId") Long selectedOwnerId, HttpSession session) {
		// Store the selected owner's ID in the session for future use
		session.setAttribute("selectedOwnerId", selectedOwnerId);

		return "redirect:/shelves"; // Redirect to the dashboard
	}

	// Render the manage owners page
	@GetMapping("/manage")
	public String manageOwners(Model model) {
		List<Owner> owners = ownerRepository.findAll();
		model.addAttribute("owners", owners);
		return "manage-owners"; // Directs to the manage owners HTML page
	}

	// Render the add/update owner page
	@GetMapping("/add-owner")
	public String addOwner(Model model) {
		model.addAttribute("owner", new Owner());
		return "add-update-owner"; // Directs to the add/update owner HTML page
	}

	// Render the add/update owner page with the owner's information
	@GetMapping("/update-owner/{id}")
	public String updateOwner(@PathVariable Long id, Model model) {
		Optional<Owner> owner = ownerRepository.findById(id);
		model.addAttribute("owner", owner.orElse(new Owner()));
		return "add-update-owner"; // Directs to the add/update owner HTML page
	}

	// Handle the addition or update of an owner
	@PostMapping("/save-owner")
	public String saveOwner(Owner owner) {
		ownerRepository.save(owner);
		return "redirect:/owners/manage"; // Directs to the manage owners HTML page
	}

	// Handle the deletion of an owner
	@GetMapping("/delete-owner/{id}")
	public String deleteOwner(@PathVariable Long id) {
		ownerRepository.deleteById(id);
		return "redirect:/owners/manage"; // Directs to the manage owners HTML page
	}
}