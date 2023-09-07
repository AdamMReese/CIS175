/*
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Sep 6, 2023
 */

package model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class VideoGame {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // Primary key field
	private String title; // Field to store the title of the video game
	private String genre; // Field to store the genre of the video game
	private String platform; // Field to store the platform of the video game
	private int releaseYear; // Field to store the release year of the video game

	// Default constructor
	public VideoGame() {
	}

	// Constructor with parameters to initialize a new VideoGame object
	public VideoGame(String title, String genre, String platform, int releaseYear) {
		this.title = title;
		this.genre = genre;
		this.platform = platform;
		this.releaseYear = releaseYear;
	}

	// Getters and setters for each field

	// Method to get the ID of the video game
	public int getId() {
		return id;
	}

	// Method to set the ID of the video game
	public void setId(int id) {
		this.id = id;
	}

	// Method to get the title of the video game
	public String getTitle() {
		return title;
	}

	// Method to set the title of the video game
	public void setTitle(String title) {
		this.title = title;
	}

	// Method to get the genre of the video game
	public String getGenre() {
		return genre;
	}

	// Method to set the genre of the video game
	public void setGenre(String genre) {
		this.genre = genre;
	}

	// Method to get the platform of the video game
	public String getPlatform() {
		return platform;
	}

	// Method to set the platform of the video game
	public void setPlatform(String platform) {
		this.platform = platform;
	}

	// Method to get the release year of the video game
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
		return String.format("ID: %d\nTitle: %s\nGenre: %s\nPlatform: %s\nRelease Year: %d\n", id, title, genre,
				platform, releaseYear);
	}
}
