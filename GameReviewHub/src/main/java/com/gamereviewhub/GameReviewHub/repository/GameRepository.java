package com.gamereviewhub.gamereviewhub.repository;

import com.gamereviewhub.gamereviewhub.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
