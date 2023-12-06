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
    @GetMapping("/catalog")
    public String showCatalogPage() {
        return "catalog";
    }

    /**
     * Handles the GET request for the register page.
     * @return The name of the register page view.
     */
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    /**
     * Handles the GET request for the review page.
     * @return The name of the review page view.
     */
    @GetMapping("/review")
    public String showReviewPage() {
        return "review";
    }

    /**
     * Handles the GET request for the contact page.
     * @return The name of the contact page view.
     */
    @GetMapping("/contact")
    public String showContactPage() {
        return "contact";
    }

    /**
     * Handles the GET request for the PC games page.
     * @return The name of the PC games page view.
     */
    @GetMapping("/pc-games")
    public String showPcGames() {
        return "pc-games";
    }
}
