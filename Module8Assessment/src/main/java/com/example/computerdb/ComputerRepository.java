package com.example.computerdb;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComputerRepository extends JpaRepository<Computer, Long> {

	List<Computer> findByMonitor(String monitor);

	List<Computer> findByForGamingIsTrue();

	Computer findFirstByMouse(String mouse);

	List<Computer> findByKeyboardContainingAndForGaming(String partialKeyboardName, Boolean forGaming);

	List<Computer> findTop5DistinctByForGamingIsTrue(Sort sort);

	List<Computer> findByKeyboardIsNot(String keyboard);

	List<Computer> findByMouseStartingWith(String monitorPrefix);

	List<Computer> findByMonitorEndingWith(String monitorSuffix);

	List<Computer> findTop2ByMouse(String mouse);

	Integer countByKeyboard(String keyboard);

	List<Computer> findByForGamingIs(Boolean forGaming);
}