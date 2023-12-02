/**
 * @author Aaron Carpenter - acarpenter5@dmacc.edu
 * CIS175 - Fall 2023
 * Nov 13, 2023
 */

package dmacc.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

/**
 * Represents a user in the system.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;

	@Column(name = "username", unique = true)
	private String username;

	@Column(name = "password")
	private String password;

	/**
	 * Constructs a User object with the specified username and password.
	 * 
	 * @param username the username of the user
	 * @param password the password of the user
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

}
