/**
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Oct 20, 2023
 */

package bookOrganizer.beans;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Year;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

// Declare the column names and lengths for the book table
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(length = 100)
	private String title;

	@Column(length = 100)
	private String author;

	@Column(length = 13)
	private String isbn;

	@Column(length = 4)
	private Year publish_date;

	@Column(length = 10000)
	private String summary;

	@ManyToOne
	@JoinColumn(name = "shelf_id", nullable = false)
	private Shelf shelf;
}