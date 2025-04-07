package com.lunis.contacts_api.services;

import com.lunis.contacts_api.models.Contact;

import java.util.List;

public interface IFileService {
    void writeToFile(List<Contact> contacts);

    List<Contact> readFromFile();
}
