package dmacc.beans;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.proxy.HibernateProxy;

import java.util.List;

/**
 * Represents a game in the Game Review Hub application.
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long gameId;

	// Validation annotations for title, genre, and platform
	@NotBlank(message = "Title is required")
	@Size(max = 255, message = "Title cannot exceed 255 characters")
	private String title;

	@NotBlank(message = "Genre is required")
	@Size(max = 100, message = "Genre cannot exceed 100 characters")
	private String genre;

	// Many-to-one relationship with Platform
	@ManyToOne
	@JoinColumn(name = "platform_id", nullable = false)
	private Platform platform;

	// One-to-many relationship with Review
	@OneToMany(mappedBy = "game")
	@ToString.Exclude
	private List<Review> reviews;

	@Override
	public final boolean equals(Object o) {
        return this == o;
    }

	@Override
	public final int hashCode() {
		return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
	}
}
