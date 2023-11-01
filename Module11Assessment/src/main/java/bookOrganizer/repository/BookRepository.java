/**
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Oct 20, 2023
 */

package bookOrganizer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import bookOrganizer.beans.Book;

import java.util.List;

// This is the repository for the Book class
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByShelfId(Long shelfId);
}