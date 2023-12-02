package dmacc.beans;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/**
 * Represents a gaming platform.
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Platform {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long platformId;

	@Column(nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "platform")
	@ToString.Exclude
	private List<Game> games;
}
