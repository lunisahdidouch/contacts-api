package com.lunis.contacts_api.services;
import com.lunis.contacts_api.models.Contact;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService implements IFileService {

    @Override
    public void writeToFile(List<Contact> contacts) {
            ObjectMapper objectMapper = new ObjectMapper();
            ArrayNode contactsArray = objectMapper.createArrayNode();

            for (Contact contact : contacts) {
                ObjectNode contactNode = objectMapper.createObjectNode();
                contactNode.put("name", contact.getName());
                contactNode.put("phoneNumber", contact.getPhoneNumber());
                contactNode.put("emailAddress", contact.getEmail());
                contactNode.put("age", contact.getAge());

                contactsArray.add(contactNode);
            }

            try {
                objectMapper.writeValue(new File("contacts-list.json"), contactsArray);
            } catch (IOException e) {
                System.out.println("Error writing contacts file: " + e.getMessage());
            }
    }

    @Override
    public List<Contact> readFromFile() {
        ArrayList<Contact> contactsList = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("contacts-list.json");

        if (!file.exists()) {
            return contactsList;
        }

        try {
            JsonNode rootArray = objectMapper.readTree(file);
            for (JsonNode contactNode : rootArray) {
                String name = contactNode.get("name").asText();
                String phoneNumber = contactNode.get("phoneNumber").asText();
                String email = contactNode.get("emailAddress").asText();
                int age = contactNode.get("age").asInt();

                contactsList.add(new Contact(name, phoneNumber, email, age));
            }
        } catch (IOException e) {
            System.out.println("Error reading contacts file: " + e.getMessage());
        }
        return contactsList;
    }
}
