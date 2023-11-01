/**
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Oct 20, 2023
 */

package bookOrganizer.repository;

import bookOrganizer.beans.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

// This is the repository for the Owner class
public interface OwnerRepository extends JpaRepository<Owner, Long> {
}