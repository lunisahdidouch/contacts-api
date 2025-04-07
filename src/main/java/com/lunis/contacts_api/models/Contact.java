package com.lunis.contacts_api.models;

public class Contact {
    private String name;
    private String phoneNumber;
    private String email;
    private int age;

    public Contact(String name, String phoneNumber, String email, int age) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public int getAge() {
        return this.age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
