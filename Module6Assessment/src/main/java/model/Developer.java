/*
* @author Adam Reese - amreese3
* CIS175 - Fall 2023
* Sep 28, 2023
*/

package model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Developer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;

	@OneToMany(mappedBy = "developer", cascade = CascadeType.ALL)
	private List<model.VideoGame> videoGames;

	// Default constructor
	public Developer() {
	}

	// Constructor with name parameter
	public Developer(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<model.VideoGame> getVideoGames() {
		return videoGames;
	}

	public void setVideoGames(List<model.VideoGame> videoGames) {
		this.videoGames = videoGames;
	}
}
