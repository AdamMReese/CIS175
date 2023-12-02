package dmacc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dmacc.beans.Game;

/**
 * This interface represents a repository for managing Game entities.
 * It extends the JpaRepository interface, providing basic CRUD operations for Game objects.
 * Additional custom methods can be added to this interface if needed.
 */
public interface GameRepository extends JpaRepository<Game, Long> {
    // Add custom methods here if needed
}
