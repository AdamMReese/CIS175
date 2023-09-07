/**
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Sep 6, 2023
 */

import java.util.List;
import java.util.Scanner;

import controller.VideoGameHelper;
import model.VideoGame;

public class StartProgram {

	// Initializing Scanner and VideoGameHelper objects
	static Scanner in = new Scanner(System.in);
	static VideoGameHelper vgh = new VideoGameHelper();

	// Method to add a new game to the database
	private static void addAGame() {
		System.out.print("Enter the title of the game: ");
		String title = in.nextLine();
		System.out.print("Enter the genre of the game: ");
		String genre = in.nextLine();
		System.out.print("Enter the platform of the game: ");
		String platform = in.nextLine();
		System.out.print("Enter the release year of the game: ");
		int releaseYear = in.nextInt();
		in.nextLine(); // Consume the newline

		// Creating a new VideoGame object and inserting it into the database
		VideoGame toAdd = new VideoGame(title, genre, platform, releaseYear);
		vgh.insertGame(toAdd);
	}

	// Method to delete a game from the database
	private static void deleteAGame() {
		System.out.print("Enter the ID of the game to delete: ");
		int id = in.nextInt();
		in.nextLine(); // Consume the newline
		vgh.deleteGame(id);
	}

	// Method to edit the details of an existing game in the database
	private static void editAGame() {
		System.out.print("Enter the ID of the game to edit: ");
		int id = in.nextInt();
		in.nextLine(); // Consume the newline
		VideoGame toEdit = vgh.findGameById(id);

		// If the game is found, updating its details
		if (toEdit != null) {
			System.out.print("Enter the new title of the game: ");
			String title = in.nextLine();
			System.out.print("Enter the new genre of the game: ");
			String genre = in.nextLine();
			System.out.print("Enter the new platform of the game: ");
			String platform = in.nextLine();
			System.out.print("Enter the new release year of the game: ");
			int releaseYear = in.nextInt();
			in.nextLine(); // Consume the newline

			// Updating the game details
			toEdit.setTitle(title);
			toEdit.setGenre(genre);
			toEdit.setPlatform(platform);
			toEdit.setReleaseYear(releaseYear);

			// Saving the updated game details to the database
			vgh.updateGame(toEdit);
		} else {
			System.out.println("Game not found.");
		}
	}

	// Method to view the list of all games in the database
	private static void viewTheList() {
		List<VideoGame> allGames = vgh.showAllGames();
		for (VideoGame singleGame : allGames) {
			System.out.println(singleGame.toString());
			System.out.println("------------------------------");
		}
	}

	// Main method to run the program
	public static void main(String[] args) {
		runMenu();
	}

	// Method to display the menu and handle user input
	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to Adam's Video Game Database! ---");
		while (goAgain) {
			System.out.println("*  Select an item:");
			System.out.println("*  1 -- Add a game");
			System.out.println("*  2 -- Edit a game");
			System.out.println("*  3 -- Delete a game");
			System.out.println("*  4 -- View the list");
			System.out.println("*  5 -- Exit the program");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine(); // Consume the newline

			// Handling different menu options based on user input
			if (selection == 1) {
				addAGame();
			} else if (selection == 2) {
				editAGame();
			} else if (selection == 3) {
				deleteAGame();
			} else if (selection == 4) {
				viewTheList();
			} else {
				vgh.cleanUp();
				System.out.println("   Goodbye!   ");
				goAgain = false;
			}
		}
	}
}
