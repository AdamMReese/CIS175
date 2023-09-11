
/**
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Sep 10, 2023
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import controller.VideoGameHelper;
import model.VideoGame;

public class StartProgram {

	// Scanner object to read user inputs
	static Scanner in = new Scanner(System.in);

	// VideoGameHelper object to interact with the database
	static VideoGameHelper vgh = new VideoGameHelper();

	// Boolean flag to control the main menu loop
	static boolean goAgain = true;

	public static void main(String[] args) {
		// Entry point of the program
		runMenu();
	}

	// Method to display the menu and handle user input
	public static void runMenu() {
		// Main menu loop
		System.out.println("--- Welcome to Adam's Video Game Database! ---");
		while (goAgain) {
			System.out.println("*  Select an item:");
			System.out.println("*  1 -- Add a game");
			System.out.println("*  2 -- Edit a game");
			System.out.println("*  3 -- Delete a game");
			System.out.println("*  4 -- View the list");
			System.out.println("*  5 -- Export the table");
			System.out.println("*  6 -- Exit the program");

			while (true) {
				System.out.print("*  Your selection: ");
				if (in.hasNextInt()) {
					int selection = in.nextInt();
					in.nextLine();
					if (selection >= 1 && selection <= 6) {
						handleMenuSelection(selection);
						break;
					} else {
						System.out.println("Invalid selection. Please try again.");
					}
				} else {
					System.out.println("Invalid selection. Please try again.");
					in.next();
				}
			}
		}
	}

	private static void handleMenuSelection(int selection) {
		// Method to handle user's menu selection and redirect to the appropriate method
		switch (selection) {
		case 1:
			addAGame();
			break;
		case 2:
			editAGame();
			break;
		case 3:
			deleteAGame();
			break;
		case 4:
			viewTheList();
			break;
		case 5:
			exportTable();
			break;
		case 6:
			System.out.print("Are you sure you want to exit? (yes/no): "); // Confirmation before exiting
			String confirmation = in.nextLine();
			if (confirmation.equalsIgnoreCase("yes")) {
				vgh.cleanUp();
				System.out.println("   Goodbye!   ");
				goAgain = false;
			}
			break;
		default:
			System.out.println("Invalid selection. Please try again.");
		}
	}

	private static void addAGame() {
	    // Method to add a new game record to the database
	    String title = "";
	    while (title.isEmpty()) {
	        System.out.print("Enter the title of the game: ");
	        title = in.nextLine();
	        if (title.isEmpty()) {
	            System.out.println("Title cannot be empty. Please try again.");
	        }
	    }

	    String genre = "";
	    while (genre.isEmpty()) {
	        System.out.print("Enter the genre of the game: ");
	        genre = in.nextLine();
	        if (genre.isEmpty()) {
	            System.out.println("Genre cannot be empty. Please try again.");
	        }
	    }

	    String platform = "";
	    while (platform.isEmpty()) {
	        System.out.print("Enter the platform of the game: ");
	        platform = in.nextLine();
	        if (platform.isEmpty()) {
	            System.out.println("Platform cannot be empty. Please try again.");
	        }
	    }

	    int releaseYear = 0;
	    while (true) {
	        System.out.print("Enter the release year of the game (eg, '1997'): ");
	        String input = in.nextLine();
	        if (input.isEmpty()) {
	            System.out.println("Release year must be a valid year. Please try again.");
	        } else if (input.matches("\\d{4}")) {
	            releaseYear = Integer.parseInt(input);
	            break;
	        } else {
	            System.out.println("Release year must be a valid year. Please try again.");
	        }
	    }

	    // Creating a new VideoGame object and inserting it into the database
	    VideoGame toAdd = new VideoGame(title, genre, platform, releaseYear);
	    vgh.insertGame(toAdd);
	    
	    System.out.println("Game added successfully." + "\n"); // New line to indicate successful addition
	}


	private static void deleteAGame() {
		// Method to delete a game record from the database
		viewTheList(); // Display the list of games with index numbers
		System.out.print("Enter the number of the game to delete: ");
		int index = in.nextInt();
		in.nextLine(); // Consume the newline

		List<VideoGame> allGames = vgh.showAllGames();
		if (index > 0 && index <= allGames.size()) {
			VideoGame toDelete = allGames.get(index - 1);

			System.out.println("Game found: ");
			System.out.println(toDelete.toString());
			System.out.print("Do you want to delete this game? (yes/no): ");
			String confirmation = in.nextLine();

			if (confirmation.equalsIgnoreCase("yes")) {
				vgh.deleteGame(toDelete.getId());
				System.out.println("Game deleted successfully." + "\n");
			} else {
				System.out.println("Deletion operation cancelled.");
			}
		} else {
			System.out.println("Invalid number. Please try again.");
		}
	}

	private static void editAGame() {
		// Method to edit the details of an existing game record in the database
		viewTheList(); // Display the list of games with index numbers
		int index = -1;
		while (true) {
			System.out.print("Enter the number of the game to edit: ");
			if (in.hasNextInt()) {
				index = in.nextInt();
				in.nextLine(); // Consume the newline
				List<VideoGame> allGames = vgh.showAllGames();
				if (index > 0 && index <= allGames.size()) {
					break;
				} else {
					System.out.println("Invalid number. Please try again.");
				}
			} else {
				System.out.println("Invalid input. Please enter a valid number.");
				in.next(); // Consumes the invalid input to prevent an infinite loop
			}
		}

		List<VideoGame> allGames = vgh.showAllGames();
		VideoGame toEdit = allGames.get(index - 1);

		System.out.println("Game found: ");
		System.out.println(toEdit.toString());
		System.out.print("Do you want to edit this game? (yes/no): ");
		String confirmation = in.nextLine();

		// If the user confirms, proceed to edit the game details
		if (confirmation.equalsIgnoreCase("yes")) {
			boolean editing = true;
			while (editing) {
				System.out.println("Select the attribute you want to edit:");
				System.out.println("1. Title");
				System.out.println("2. Genre");
				System.out.println("3. Platform");
				System.out.println("4. Release Year");
				System.out.println("5. Finish Editing");
				int selection = -1;
				while (true) {
					System.out.print("Your selection: ");
					if (in.hasNextInt()) {
						selection = in.nextInt();
						in.nextLine(); // Consume the newline
						if (selection >= 1 && selection <= 5) {
							break;
						} else {
							System.out.println("Invalid selection. Please try again.");
						}
					} else {
						System.out.println("Invalid input. Please enter a valid number.");
						in.next(); // Consumes the invalid input to prevent an infinite loop
					}
				}

				switch (selection) {
				case 1:
					String title = "";
					while (title.isEmpty()) {
						System.out.print("Enter the new title of the game: ");
						title = in.nextLine();
						if (title.isEmpty()) {
							System.out.println("Title cannot be empty. Please try again.");
						}
					}
					toEdit.setTitle(title);
					break;
				case 2:
					String genre = "";
					while (genre.isEmpty()) {
						System.out.print("Enter the new genre of the game: ");
						genre = in.nextLine();
						if (genre.isEmpty()) {
							System.out.println("Genre cannot be empty. Please try again.");
						}
					}
					toEdit.setGenre(genre);
					break;
				case 3:
					String platform = "";
					while (platform.isEmpty()) {
						System.out.print("Enter the new platform of the game: ");
						platform = in.nextLine();
						if (platform.isEmpty()) {
							System.out.println("Platform cannot be empty. Please try again.");
						}
					}
					toEdit.setPlatform(platform);
					break;
				case 4:
					int releaseYear = -1;
					while (true) {
						System.out.print("Enter the new release year of the game: ");
						if (in.hasNextInt()) {
							releaseYear = in.nextInt();
							in.nextLine();
							if (releaseYear >= 1000 && releaseYear <= 9999) {
								break;
							} else {
								System.out.println("Release year must be a valid year. Please try again.");
							}
						} else {
							System.out.println("Invalid input. Please enter a valid year.");
							in.next();
						}
					}
					toEdit.setReleaseYear(releaseYear);
					break;
				case 5:
					editing = false;
					break;
				default:
					System.out.println("Invalid selection. Please try again.");

				}
			}

			// Saving the updated game details to the database
			vgh.updateGame(toEdit);
			
		    System.out.println("Game edited successfully." + "\n"); // New line to indicate successful editing
		} else {
			System.out.println("Edit operation cancelled.");
		}
	}

	private static void viewTheList() {
		// Method to display the list of all game records in the database
		List<VideoGame> allGames = vgh.showAllGames();
		int index = 1;
		for (VideoGame singleGame : allGames) {
			System.out.println(index + ". " + singleGame.toString());
			index++;
		}
	}

	private static void exportTable() {
		// Method to export the database table to a file (CSV or TXT format)
		int selection = -1;
		while (true) {
			// Prompt the user to select the file format for export
			System.out.println("Select the file format to export:");
			System.out.println("1. CSV");
			System.out.println("2. TXT");
			System.out.println("0. Go Back");
			System.out.print("Your selection: ");

			// Read the user's selection
			if (in.hasNextInt()) {
				selection = in.nextInt();
				in.nextLine(); // Consume the newline

				if (selection == 0) {
					return; // If user selects 0, return to the previous menu
				}

				if (selection == 1 || selection == 2) {
					break;
				} else {
					System.out.println("Invalid selection. Please try again.");
				}
			} else {
				System.out.println("Invalid input. Please enter a valid number.");
				in.next(); // Consumes the invalid input to prevent an infinite loop
			}
		}

		// Retrieve all game records from the database
		List<VideoGame> allGames = vgh.showAllGames();

		// Determine the file extension based on the user's selection
		String fileExtension = (selection == 1) ? ".csv" : ".txt";

		// Create a folder named "Exports" if it doesn't exist
		File exportsDir = new File("Exports");
		if (!exportsDir.exists()) {
			exportsDir.mkdir();
		}

		// Determine the subfolder based on the file type
		String subFolder = (selection == 1) ? "CSV" : "TXT";

		// Create the subfolder if it doesn't exist
		File subFolderDir = new File("Exports/" + subFolder);
		if (!subFolderDir.exists()) {
			subFolderDir.mkdir();
		}

		// Prompt the user to enter the base name for the file
		String fileNameBase = "";
		while (true) {
			System.out.print("Enter the name of the file (without extension): ");
			fileNameBase = in.nextLine();

			System.out.print("You entered '" + fileNameBase + "'. Is this correct? (yes/no): ");
			String confirmation = in.nextLine();

			if (confirmation.equalsIgnoreCase("yes")) {
				break;
			} else {
				System.out.println("Please enter the file name again.");
			}
		}

		// Adding a timestamp to the file name to avoid overwriting files and to keep a
		// history of exports
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
		String fileName = "Exports/" + subFolder + "/" + fileNameBase + "_" + timestamp + fileExtension;

		File file = new File(fileName);
		while (true) {
			if (file.exists()) {
				System.out.print("A file with this name already exists. Do you want to overwrite it? (yes/no): ");
				String confirmation = in.nextLine();
				if (confirmation.equalsIgnoreCase("yes") || confirmation.equalsIgnoreCase("no")) {
					if (!confirmation.equalsIgnoreCase("yes")) {
						System.out.println("Export operation cancelled.");
						return;
					}
					break;
				} else {
					System.out.println("Invalid input. Please enter 'yes' or 'no'.");
				}
			} else {
				break;
			}
		}

		// Switch statement to handle different file formats (CSV or TXT)
		switch (selection) {
		case 1:
			// Exporting data in CSV format
			try (PrintWriter writer = new PrintWriter(new File(fileName))) {
				// StringBuilder to construct the CSV content
				StringBuilder sb = new StringBuilder();

				// Adding the header row to the CSV content
				sb.append("ID,Title,Genre,Platform,Release Year\n");

				// Loop through all games and append each game's details to the CSV content
				for (VideoGame game : allGames) {
					sb.append(game.getId()).append(',');
					sb.append(game.getTitle()).append(',');
					sb.append(game.getGenre()).append(',');
					sb.append(game.getPlatform()).append(',');
					sb.append(game.getReleaseYear()).append('\n');
				}

				// Write the CSV content to the file
				writer.write(sb.toString());

				// Notify the user that the data has been exported
				System.out.println("Data has been exported to " + fileName);
			} catch (FileNotFoundException e) {
				// Handle any file writing errors
				System.out.println("Error exporting data: " + e.getMessage());
			}
			break;
		case 2:
			// Exporting data in TXT format
			try (PrintWriter writer = new PrintWriter(new File(fileName))) {
				// Loop through all games and write each game's details to the TXT file
				for (VideoGame game : allGames) {
					writer.println("ID: " + game.getId());
					writer.println("Title: " + game.getTitle());
					writer.println("Genre: " + game.getGenre());
					writer.println("Platform: " + game.getPlatform());
					writer.println("Release Year: " + game.getReleaseYear());
					writer.println("------------------------------");
				}

				// Notify the user that the data has been exported
				System.out.println("Data has been exported to " + fileName);
			} catch (FileNotFoundException e) {
				// Handle any file writing errors
				System.out.println("Error exporting data: " + e.getMessage());
			}
			break;
		default:
			// Handling invalid selections
			System.out.println("Invalid selection. Please try again.");
		}
	}
}