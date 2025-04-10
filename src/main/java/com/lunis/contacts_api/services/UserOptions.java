package com.lunis.contacts_api.services;


import com.lunis.contacts_api.models.Contact;
import java.util.List;
import java.util.Scanner;


public class UserOptions {
    static int option;
    static Scanner scanner = new Scanner(System.in);
    static FileService fileService = new FileService();

    static ContactManagementService service = new ContactManagementService(fileService);

    public UserOptions() {
        scanner = new Scanner(System.in);
    }

    static void mainMenu(){
        if (option == 1) {
            System.out.println("Enter the contact's name:");
            String name = scanner.nextLine();
            System.out.println("Enter the phone number");
            String phoneNumber = scanner.nextLine();
            System.out.println("Enter the email address");
            String email = scanner.nextLine();
            System.out.println("Enter the age");

            while(!scanner.hasNextInt()) {
                System.out.println("Invalid! Enter a number: ");
                scanner.next();
            }

            int age = scanner.nextInt();
            scanner.nextLine();
            Contact newContact = new Contact(name, phoneNumber, email, age);
            service.addContact(newContact);
            System.out.println("Contact added successfully!");
            secondMenu();
        } else if (option == 2) {
            List<Contact> listOfContacts = service.getContacts();

            if (listOfContacts.isEmpty()) {
                System.out.println("No contacts found.");
            } else {
                for (Contact listOfContact : listOfContacts) {
                    System.out.println(listOfContact.getName());
                }
            }
            secondMenu();
        } else if (option == 3) {
            System.out.println("Type in the name of the person you want to search for:");
            String searchQuery = scanner.nextLine();
            Contact searchedContact = service.searchContact(searchQuery);
            if (searchedContact != null) {
                System.out.println("Here is the contact information:");
                System.out.println("Name: " + searchedContact.getName());
                System.out.println("Phone number: " + searchedContact.getPhoneNumber());
                System.out.println("Email: " + searchedContact.getEmail());
                System.out.println("Age: " + searchedContact.getAge());
            }
            else{
                System.out.println("No contact found with that name");
            }


            secondMenu();
        } else if (option == 4) {
            System.out.println("Who would you like to delete from your contacts?");
            String deleteContact = scanner.nextLine();
            Contact contactToDelete = service.searchContact(deleteContact);
            if (contactToDelete != null) {
                System.out.println("Are you sure you want to delete " + contactToDelete.getName() + "? (yes/no)");
                String answer = scanner.nextLine();
                if (answer.equalsIgnoreCase("yes")) {
                    service.deleteContact(deleteContact);
                }
                else{
                    System.out.println("Contact has not been removed");
                }
            }
            else{
                System.out.println("No contact found with that name");
            }
            secondMenu();
        } else if (option == 0) {
            System.exit(0);
        }
    }

    public static void welcomeMenu() {
        System.out.println("\n\n\nWelcome to your contact management system");
        System.out.println("Choose an option below to continue:");
        System.out.println("1: Add contact\n2: View all contact\n3: Search contact\n4: Delete contact\n0: Exit");

        try {
            option = Integer.parseInt(scanner.nextLine());
            mainMenu();
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number");
            welcomeMenu();
        }
    }

    static void secondMenu() {
        System.out.println("\n\n\nChoose an option:\n1: See menu again\n2: Exit");
        try {
            int answer = Integer.parseInt(scanner.nextLine());
            if (answer == 2) {
                System.exit(0);
            } else {
                welcomeMenu();
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number");
            secondMenu();
        }
    }
}
