package com.kevindonahue;

import java.util.ArrayList;

public class MobilePhone {

    private String myNumber; // Not necessary
    private ArrayList<Contact> contactsList;

    public MobilePhone(String myNumber) {
        this.myNumber = myNumber;
        this.contactsList = new ArrayList<>(); // Initializing ArrayList of Contacts
    }

    public ArrayList<Contact> getContactsList() {
        return contactsList;
    }

    // Check if contact already exists and then store it if it doesn't
    public boolean storeContact(Contact newContact) {
        if (queryContact(newContact) >= 0) {
            System.out.println("Error: Contact already exists.");
            return false;
        } else {
            contactsList.add(newContact);
            return true;
        }
    }

    public void modifyContact(Contact currentContact, Contact modifiedContact) {
        int position = contactsList.indexOf(currentContact);
        if (position >= 0) {
            contactsList.set(position, modifiedContact);
            System.out.println("The contact " + currentContact.getName() + " has been modified.");
        }
    }


    public boolean deleteContact(Contact contactToDelete) {
        int position = queryContact(contactToDelete);
        if (position >= 0) {
            deleteContact(position);
            return true;
        }
        System.out.println("The contact " + contactToDelete.getName() + " does not exist and therefore cannot be deleted.");
        return false;
    }

    private void deleteContact(int position) {
        Contact contactToDelete = contactsList.get(position);
        contactsList.remove(position);
        System.out.println(contactToDelete.getName() + " was successfully removed from your list of contacts.");
    }

    // Private Overloaded queryContact methods:

    private int queryContact(Contact contactToFind) {
        return contactsList.indexOf(contactToFind);
    }

    private int queryContact(String contactName) {
        for (int i = 0; i < this.contactsList.size(); i++) {
            Contact contact = this.contactsList.get(i);
            if (contact.getName().equals(contactName)) {
                return contactsList.indexOf(contact);
            }
        }
        return -1; // Contact does not exist
    }

    // Public method to hide the inner workings of ArrayList indexes
    public Contact searchContact(String contactName) {
        int position = queryContact(contactName);
        if (position >= 0) {
            return this.contactsList.get(position);
        } else {
            System.out.println("Error: Contact does not exist.");
            return null;
        }
    }

    public boolean contactExists(Contact contactToFind) {
        int position = queryContact(contactToFind);
        return position >= 0;
    }

    public void printContactList() {
        System.out.println("You have " + contactsList.size() + " contact(s) in your phone.");
        for (int i = 0; i < contactsList.size(); i++) {
            System.out.println("\nName: " + contactsList.get(i).getName());
            System.out.println("Number: " + contactsList.get(i).getNumber());
        }
    }
}
