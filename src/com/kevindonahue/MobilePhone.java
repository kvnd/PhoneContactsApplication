package com.kevindonahue;

import java.util.ArrayList;

public class MobilePhone {

    private ArrayList<Contact> contactsList = new ArrayList<>();

    public ArrayList<Contact> getContactsList() {
        return contactsList;
    }

    // Check if contact already exists and then store it if it doesn't
    public void storeContact(Contact newContact) {
        int position = contactsList.indexOf(newContact);
        if (position >= 0) {
            System.out.println("Error: Contact already exists.");
        } else {
            contactsList.add(newContact);
        }
    }

    public void modifyContact(Contact currentContact, Contact modifiedContact) {
        int position = contactsList.indexOf(currentContact);
        if (position >= 0) {
            contactsList.set(position, modifiedContact);
            System.out.println("The contact " + currentContact.getName() + " has been modified.");
        }
    }

    public void deleteContact(Contact contactToDelete) {
        int position = contactsList.indexOf(contactToDelete);
        if (position >= 0)
            deleteContact(position);
    }
    private void deleteContact(int position) {
        Contact contactToDelete = contactsList.get(position);
        contactsList.remove(position);
        System.out.println(contactToDelete.getName() + " was successfully removed from your list of contacts.");
    }

    public boolean contactExists(Contact contactToFind) {
        int position = queryContact(contactToFind);
        if (position >= 0) {
            return true;
        }
        else {
            return false;
        }
    }
    private int queryContact(Contact contactToFind) {
        return contactsList.indexOf(contactToFind);
    }

    public void printContactList() {
        System.out.println("You have " + contactsList.size() + " contact(s) in your phone.");
        for (int i = 0; i < contactsList.size(); i++){
            System.out.println("\nName: " + contactsList.get(i).getName());
            System.out.println("Number: " + contactsList.get(i).getNumber());
        }
    }
}
