/**
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Oct 20, 2023
 */

package bookOrganizer.controller;

import bookOrganizer.beans.Book;
import bookOrganizer.beans.Owner;
import bookOrganizer.beans.Shelf;
import bookOrganizer.repository.BookRepository;
import bookOrganizer.repository.OwnerRepository;
import bookOrganizer.repository.ShelfRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/shelves")
public class ShelfController {

    // Injecting the repositories
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ShelfRepository shelfRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    // Render the shelves of the selected owner
    @GetMapping
    public String showShelvesForSelectedOwner(HttpSession session, Model model) {
        Long selectedOwnerId = (Long) session.getAttribute("selectedOwnerId");
        // If the selected owner's ID is in the session, display their shelves
        if (selectedOwnerId != null) {
            List<Shelf> shelves = shelfRepository.findByOwnerId(selectedOwnerId);
            model.addAttribute("shelves", shelves);
        }
        return "manage-shelves"; // Directs to the manage shelves HTML page
    }

    // Handle a single shelf by its ID
    @GetMapping("/{id:[\\d]+}")
    public String getShelfById(@PathVariable Long id, Model model) {
        Optional<Shelf> optionalShelf = shelfRepository.findById(id);
        if (optionalShelf.isPresent()) {
            model.addAttribute("shelf", optionalShelf.get());
            return "redirect:/shelves/view/{id}"; // Directs to the view shelf HTML page
        } else {
            return "redirect:/error"; // Directs to the error HTML page
        }
    }

    // Add a new shelf
    @GetMapping("/add")
    public String addNewShelf(Model model) {
        model.addAttribute("shelf", new Shelf()); // Add an empty Shelf object to the model
        return "add-update-shelf"; // Directs to the add/update shelf HTML page
    }

    // Edit an existing shelf
    @GetMapping("/edit/{id}")
    public String editShelf(@PathVariable Long id, Model model) {
        Optional<Shelf> optionalShelf = shelfRepository.findById(id);
        if (optionalShelf.isPresent()) {
            model.addAttribute("shelf", optionalShelf.get());
            return "add-update-shelf"; // Directs to the add/update shelf HTML page
        } else {
            return "redirect:/error"; // Directs to the error HTML page
        }
    }

    // Delete a shelf by its ID
    @GetMapping("/delete-shelf/{id}")
    public String deleteShelf(@PathVariable Long id) {
        shelfRepository.deleteById(id);
        return "redirect:/shelves"; // Directs to the manage shelves HTML page
    }

    // View a shelf by its ID
    @GetMapping("/view/{id}")
    public String viewShelf(@PathVariable Long id, Model model) {
        Optional<Shelf> optionalShelf = shelfRepository.findById(id);
        if (optionalShelf.isPresent()) {
            Shelf shelf = optionalShelf.get();
            model.addAttribute("shelf", shelf);
            List<Book> books = bookRepository.findByShelfId(id);
            model.addAttribute("books", books);
            return "view-shelf"; // Directs to the view shelf HTML page
        } else {
            return "redirect:/error"; // Directs to the error HTML page
        }
    }

    // Add a new book to a shelf
    @GetMapping("/view/{id}/add-book")
    public String addNewBookForShelf(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("shelfId", id);
        return "redirect:/books/add-book"; // Directs to the add book HTML page
    }

    // Manage the books on a shelf
    @GetMapping("/view/{id}/manage-books")
    public String manageBooksForShelf(@PathVariable Long id, Model model) {
        Optional<Shelf> optionalShelf = shelfRepository.findById(id);
        // If the shelf exists, display its books
        if (optionalShelf.isPresent()) {
            Shelf shelf = optionalShelf.get();
            model.addAttribute("shelf", shelf);
            List<Book> books = bookRepository.findByShelfId(id);
            model.addAttribute("books", books);
            return "manage-books"; // Directs to the manage books HTML page
        } else {
            return "redirect:/error"; // Directs to the error HTML page
        }
    }

    // Save a new or updated shelf
    @PostMapping("/save")
    public String saveShelf(@ModelAttribute Shelf shelf, HttpSession session) {
        Long selectedOwnerId = (Long) session.getAttribute("selectedOwnerId");
        // If the selected owner's ID is in the session, set the shelf's owner to that owner
        if (selectedOwnerId != null) {
            Optional<Owner> optionalOwner = ownerRepository.findById(selectedOwnerId);
            if (optionalOwner.isPresent()) {
                Owner owner = optionalOwner.get();
                shelf.setOwner(owner);
            } else {
                return "redirect:/error"; // Directs to the error HTML page
            }
        } else {
            return "redirect:/error"; // Directs to the error HTML page
        }

        shelfRepository.save(shelf);
        return "redirect:/shelves"; // Directs to the manage shelves HTML page
    }
}
