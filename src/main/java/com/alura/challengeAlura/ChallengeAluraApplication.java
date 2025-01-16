package com.alura.challengeAlura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alura.challengeAlura.repository.AuthorDataRepository;
import com.alura.challengeAlura.repository.BookDataRepository;
import com.alura.challengeAlura.service.Menu;

@SpringBootApplication
public class ChallengeAluraApplication implements CommandLineRunner {

	@Autowired
	private BookDataRepository bookRepo;

	@Autowired
	private AuthorDataRepository authorRepo;
	public static void main(String[] args) {
		SpringApplication.run(ChallengeAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Menu menu = new Menu(bookRepo, authorRepo);
		menu.mainMenu();
	}

}
