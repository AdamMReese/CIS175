package com.example.computerdb;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class ComputerApplication implements CommandLineRunner {

	@Autowired
	ComputerRepository computerRepository;

	@Autowired
	ApplicationContext applicationContext;

	public static void main(String[] args) {
		SpringApplication.run(ComputerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Computer computer = new Computer(null, null, null, false);
		computer.setMonitor("Default");
		computer.setMouse("Default");
		computer.setKeyboard("Default");
		computer.setForGaming(false);

		final List<Computer> computers = Arrays.asList(new Computer("Asus", "Logitech", "Akko", true),
				new Computer("Logitech", "Razer", "Logitech", true), new Computer("Acer", "Asus", "Razer", true),
				new Computer("Samsung", "Corsair", "Drop", true), new Computer("MSI", "HyperX", "Ducky", true),
				new Computer("Dell", "Logitech", "Razer", true), new Computer("Acer", "HyperX", "Akko", false),
				new Computer("Logitech", "Corsair", "Logitech", false));

		if (computerRepository.findAll().isEmpty()) {
			computerRepository.saveAll(computers);
		}

		System.out.println("----------Find By Monitor Name (Logitech)----------");
		final List<Computer> findByMonitor = computerRepository.findByMonitor("Logitech");
		findByMonitor.forEach(System.out::println);

		System.out.println("----------Find Computers That Are For Gaming----------");
		final List<Computer> findByForGamingIsTrue = computerRepository.findByForGamingIsTrue();
		findByForGamingIsTrue.forEach(System.out::println);

		System.out.println("----------Find First Mouse With 'Corsair'----------");
		final Computer findFirstByMouse = computerRepository.findFirstByMouse("Corsair");
		System.out.println(findFirstByMouse);

		System.out.println("----------Find Keyboard Containing 'Ak' AND if TRUE For Gaming----------");
		final List<Computer> findByKeyboardContainingAndForGaming = computerRepository
				.findByKeyboardContainingAndForGaming("Ak", true);
		findByKeyboardContainingAndForGaming.forEach(System.out::println);

		System.out.println("----------Find Keyboards NOT Containing 'Akko'----------");
		final List<Computer> findByKeyboardIsNot = computerRepository.findByKeyboardIsNot("Akko");
		findByKeyboardIsNot.forEach(System.out::println);

		System.out.println("----------Find Mice? Starting With 'a'----------");
		final List<Computer> findByMouseStartingWith = computerRepository.findByMouseStartingWith("a");
		findByMouseStartingWith.forEach(System.out::println);

		System.out.println("----------Find Monitor Ending With 'r'----------");
		final List<Computer> findByMonitorEndingWith = computerRepository.findByMonitorEndingWith("r");
		findByMonitorEndingWith.forEach(System.out::println);

		System.out.println("----------Find First 2 'HyperX' Results ----------");
		List<Computer> findTop2ByMouse = computerRepository.findTop2ByMouse("HyperX");
		findTop2ByMouse.forEach(System.out::println);

		System.out.println("----------Find the Number of Entities With 'Logitech'----------");
		final Integer countByKeyboard = computerRepository.countByKeyboard("Logitech");
		System.out.println(countByKeyboard);

		System.out.println("----------Find Computers That Are NOT For Gaming----------");
		final List<Computer> findByForGamingIs = computerRepository.findByForGamingIs(false);
		findByForGamingIs.forEach(System.out::println);

		Sort sort = Sort.by(Sort.Direction.DESC, "id");

		System.out.println("----------SORT----------");
		final List<Computer> findTop5DistinctByForGamingIsTrue = computerRepository
				.findTop5DistinctByForGamingIsTrue(sort);
		findTop5DistinctByForGamingIsTrue.forEach(System.out::println);

	}

}