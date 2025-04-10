package com.lunis.contacts_api.services;

import com.lunis.contacts_api.models.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ContactManagementService implements IContactManagementService{
    ArrayList<Contact> contacts = new ArrayList<>();
    IFileService fileService;

    public ContactManagementService(IFileService fileService) {
        this.fileService = fileService;
        // Load initial contacts when the service is created
        this.contacts = new ArrayList<>(this.fileService.readFromFile());
        System.out.println("ContactManagementService created, loaded " + this.contacts.size() + " contacts.");
    }

    @Override
    public void addContact(Contact newContact) {
        contacts.add(newContact);
        fileService.writeToFile(contacts);
    }

    @Override
    public List<Contact> getContacts() {
        contacts = (ArrayList<Contact>) fileService.readFromFile();
        return contacts;
    }

    @Override
    public Contact searchContact(String name) {
        if(name.length() > 5) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        for(Contact contact : contacts) {
            if (Objects.equals(contact.getName(), name)) {
                return contact;
            }
        }
        return null;
    }

    @Override
    public void deleteContact(String name) {
        boolean contactFound = false;
        for(int i = 0; i < contacts.size(); i++) {
            Contact contact = contacts.get(i);
            if (Objects.equals(contact.getName(), name)) {
                contactFound = true;
                contacts.remove(i);
                fileService.writeToFile(contacts);
                System.out.println("Contact has successfully been removed");
                break;
            }
        }
    }
}
