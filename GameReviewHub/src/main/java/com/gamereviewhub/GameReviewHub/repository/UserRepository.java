package com.gamereviewhub.gamereviewhub.repository;

import com.gamereviewhub.gamereviewhub.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  UserRepository extends JpaRepository<User, Long> {
}