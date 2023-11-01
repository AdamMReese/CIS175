/**
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Oct 20, 2023
 */

package bookOrganizer.beans;

import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

// Declare the column names and lengths for the shelf database table
public class Shelf {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String genre;

	@ManyToOne
	private Owner owner;

	@OneToMany(mappedBy = "shelf")
	private List<Book> books;
}