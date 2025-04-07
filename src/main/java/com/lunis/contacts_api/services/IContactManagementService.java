package com.lunis.contacts_api.services;

import com.lunis.contacts_api.models.Contact;

import java.util.List;

public interface IContactManagementService {
    void addContact(Contact newContact);
    List<Contact> getContacts();
    Contact searchContact(String name);
    void deleteContact(String name);
}