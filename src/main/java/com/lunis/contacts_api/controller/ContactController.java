package com.lunis.contacts_api.controller;

import com.lunis.contacts_api.models.Contact;
import com.lunis.contacts_api.services.IContactManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; // Important for responses
import org.springframework.web.bind.annotation.*; // Crucial annotations

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    @Value("${monkey}")
    String monkey;
    private final IContactManagementService contactService;

    public ContactController(IContactManagementService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public List<Contact> getAllContacts() {
        System.out.println("Monkey: " + monkey);
        return contactService.getContacts();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED) // Sets the HTTP status code for success to 201
    public Contact addContact(@RequestBody Contact newContact) {
        contactService.addContact(newContact);
        return newContact;
    }

    @GetMapping("/{name}")
    public ResponseEntity<Contact> searchContactByName(@PathVariable(required = false) String name) {
        try{
            Contact found = contactService.searchContact(name);
            if (found != null) {
                return ResponseEntity.ok(found);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteContactByName(@PathVariable String name) {
        Contact existingContact = contactService.searchContact(name);
        if (existingContact != null) {
            contactService.deleteContact(name);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}