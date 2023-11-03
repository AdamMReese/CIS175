package com.gamereviewhub.gamereviewhub.repository;

import com.gamereviewhub.gamereviewhub.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	List<Review> findByGame_GameId(Long gameId);

	List<Review> findByUser_UserId(Long userId);
}
