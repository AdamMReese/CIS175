/**
 * @author Adam Reese - amreese3
 * CIS175 - Fall 2023
 * Dec 1, 2023
 */

package dmacc.repository;

import dmacc.beans.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * This interface represents a repository for managing Platform entities.
 * It extends the JpaRepository interface, providing CRUD operations for Platform objects.
 */
@Repository
public interface PlatformRepository extends JpaRepository<Platform, Long> {
}
