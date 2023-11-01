/**
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Oct 20, 2023
 */

package bookOrganizer.repository;

import bookOrganizer.beans.Shelf;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// This is the repository for the Shelf class
public interface ShelfRepository extends JpaRepository<Shelf, Long> {
    List<Shelf> findByOwnerId(Long ownerId); // Rreturns all shelves owned by a specific owner
}