package dmacc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * The IndexController class handles the requests for the index page and other related pages.
 */
@Controller
public class IndexController {

    /**
     * Handles the GET request for the index page.
     * @return The name of the index page view.
     */
    @GetMapping("/index")
    public String showIndexPage() {
        return "index";
    }

    /**
     * Handles the GET request for the catalog page.
     * @return The name of the catalog page view.
     */
    @GetMapping("/Catalog")
    public String showCatalogPage() {
        return "Catalog"; 
    }

    /**
     * Handles the GET request for the register page.
     * @return The name of the register page view.
     */
    @GetMapping("/Register")
    public String showRegisterPage() {
        return "Register"; 
    }

    /**
     * Handles the GET request for the review page.
     * @return The name of the review page view.
     */
    @GetMapping("/Review")
    public String showReviewPage() {
        return "Review"; 
    }

    /**
     * Handles the GET request for the contact page.
     * @return The name of the contact page view.
     */
    @GetMapping("/Contact")
    public String showContactPage() {
        return "Contact"; 
    }

    /**
     * Handles the GET request for the PC games page.
     * @return The name of the PC games page view.
     */
    @GetMapping("/PcGames")
    public String showPcGames() {
        return "PcGames";
    }
}
