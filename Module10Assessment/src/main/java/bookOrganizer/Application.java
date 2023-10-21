/**
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Oct 20, 2023
 */

package bookOrganizer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

// Main class
@SpringBootApplication
@EntityScan("bookOrganizer.beans")
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}