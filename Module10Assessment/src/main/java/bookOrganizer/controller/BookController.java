/**
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Oct 20, 2023
 */

package bookOrganizer.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bookOrganizer.beans.Book;
import bookOrganizer.beans.Shelf;
import bookOrganizer.repository.BookRepository;
import bookOrganizer.repository.ShelfRepository;

// This controller handles the book-related pages
@Controller
@RequestMapping("/books")
public class BookController {

	// Injecting the repositories
	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private ShelfRepository shelfRepository;

	// Render the books of the selected shelf
	@GetMapping("/add-book")
	public String addNewBook(@RequestParam(required = false) Long shelfId, Model model) {
		Book book = new Book();
		// If the shelf ID is in the request parameters, set the book's shelf to that
		if (shelfId != null) {
			Shelf shelf = shelfRepository.findById(shelfId).orElse(null);
			book.setShelf(shelf);
		}
		// Add the book to the model
		model.addAttribute("book", book);
		return "add-update-book"; // Directs to the add/update book HTML page
	}

	// Handle a single book by its ID
	@PostMapping("/save")
	public String saveBook(@ModelAttribute Book book, @RequestParam(required = false) Long shelfId) {
		// Save the book to the database
		if (shelfId != null) {
			Shelf shelf = shelfRepository.findById(shelfId).orElse(null);
			book.setShelf(shelf);
		}
		Book savedBook = bookRepository.save(book);
		Long id = savedBook.getId();
		return "redirect:/books/view/" + id; // Directs to the specific book's view page
	}

	// Handle a single book by its ID
	@GetMapping("/view/{id}")
	public String viewBook(@PathVariable Long id, Model model) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		// If the book is in the database, display it
		if (optionalBook.isPresent()) {
			model.addAttribute("book", optionalBook.get());
			return "view-book"; // Directs to the view book HTML page
		} else {
			return "redirect:/error"; // Directs to the error HTML page
		}
	}

	// Add a summary to a book by its ID
	@PostMapping("/update/{id}/add-summary")
	public String addSummary(@PathVariable Long id, @RequestParam String summary) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		// If the book is in the database, add the summary
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			book.setSummary(summary);
			bookRepository.save(book);
			return "redirect:/books/view/" + id; // Directs to the specific book's view page
		} else {
			return "redirect:/error"; // Directs to the error HTML page
		}
	}

	// Edit a book by its ID
	@GetMapping("/edit/{id}")
	public String editBook(@PathVariable Long id, Model model) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		// If the book is in the database, display it
		if (optionalBook.isPresent()) {
			model.addAttribute("book", optionalBook.get());
			return "add-update-book"; // Directs to the add/update book HTML page
		} else {
			return "redirect:/error"; // Directs to the error HTML page
		}
	}

	// Delete a book by its ID
	@GetMapping("/delete/{id}")
	public String deleteBook(@PathVariable Long id) {
		bookRepository.deleteById(id);
		return "redirect:/shelves"; // Directs to the manage shelves HTML page
	}
}