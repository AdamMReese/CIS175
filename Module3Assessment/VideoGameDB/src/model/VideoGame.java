/*
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Sep 10, 2023
 */

package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

// Annotation to specify that this class is a JPA entity
@Entity
public class VideoGame {

	// Annotation to specify the primary key field of the entity
	@Id
	// Annotation to specify the primary key generation strategy
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // Primary key field representing the unique identifier for each video game
						// record

	private String title; // Field representing the title of the video game
	private String genre; // Field representing the genre of the video game
	private String platform; // Field representing the platform on which the video game is available
	private int releaseYear; // Field representing the release year of the video game

	// Default constructor required by JPA
	public VideoGame() {
	}

	// Constructor with parameters to initialize a new VideoGame object with
	// specified values
	public VideoGame(String title, String genre, String platform, int releaseYear) {
		this.title = title;
		this.genre = genre;
		this.platform = platform;
		this.releaseYear = releaseYear;
	}

	// Getter and setter methods for each field

	// Method to retrieve the ID of the video game
	public int getId() {
		return id;
	}

	// Method to set the ID of the video game
	public void setId(int id) {
		this.id = id;
	}

	// Method to retrieve the title of the video game
	public String getTitle() {
		return title;
	}

	// Method to set the title of the video game
	public void setTitle(String title) {
		this.title = title;
	}

	// Method to retrieve the genre of the video game
	public String getGenre() {
		return genre;
	}

	// Method to set the genre of the video game
	public void setGenre(String genre) {
		this.genre = genre;
	}

	// Method to retrieve the platform of the video game
	public String getPlatform() {
		return platform;
	}

	// Method to set the platform of the video game
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	// Method to retrieve the release year of the video game
	public int getReleaseYear() {
		return releaseYear;
	}

	// Method to set the release year of the video game
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	// Method to return a formatted string representing the VideoGame object
	@Override
	public String toString() {
		return String.format("Title: %s\nGenre: %s\nPlatform: %s\nRelease Year: %d\n", title, genre, platform,
				releaseYear);
	}
}
