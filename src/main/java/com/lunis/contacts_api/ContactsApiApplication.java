package com.lunis.contacts_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.lunis.contacts_api.services.UserOptions.welcomeMenu;

@SpringBootApplication
public class ContactsApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactsApiApplication.class, args);
		welcomeMenu();
	}

}
