package com.kevindonahue;

/* Simple Mobile Phone Contacts Application */
/* By Kevin Donahue */

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone("0834706292");

    public static void main(String[] args) {

        boolean quit = false;
        int choice;
        startPhone();
        printMenuOptions();
        while (!quit) {
            System.out.println("\nPlease enter your choice: (Press 0 for menu options) ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Clear out the buffer

            switch (choice) {
                case 0:
                    printMenuOptions();
                    break;
                case 1:
                    mobilePhone.printContactList();
                    break;
                case 2:
                    addContact();
                    break;
                case 3:
                    modifyContact();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    searchForContact();
                    break;
                case 6:
                    System.out.println("\nExiting...");
                    quit = true;
                    break;
            }
        }
    }

    private static void startPhone() {
        System.out.println("Phone is powering on...");
    }

    private static void searchForContact() {
        System.out.println("Enter the contact name you want to search for: ");
        String contactToSearch = scanner.nextLine();
        Contact searchContact = getContactFromName(contactToSearch);
        if (mobilePhone.contactExists(searchContact)) {
            System.out.println("Contact " + searchContact.getName() + " was found. Their number is " + searchContact.getNumber());
        } else {
            System.out.println("No results were found for " + contactToSearch);
        }
    }

    private static void deleteContact() {
        System.out.println("Please enter the name of the contact you would like to delete: ");
        String contactToDelete = scanner.nextLine();
        Contact toDelete = mobilePhone.searchContact(contactToDelete);
        if (mobilePhone.contactExists(toDelete)) {
            mobilePhone.deleteContact(toDelete);
        } else {
            System.out.println("That contact cannot be deleted as it does not exist.");
            deleteContact();
        }
    }

    private static Contact getContactFromName(String contactName) {
        for (int i = 0; i < mobilePhone.getContactsList().size(); i++) {
            if (mobilePhone.getContactsList().get(i).getName().equalsIgnoreCase(contactName)) {
                return mobilePhone.getContactsList().get(i);
            }
        }
        return null;
    }

    private static void modifyContact() {
        System.out.println("Please enter name of the contact you would like to modify: ");
        String name = scanner.nextLine();
        Contact existingContact = mobilePhone.searchContact(name);
        if (existingContact == null) {
            System.out.println("Sorry, contact '" + name + "' does not exist.");
            return;
        }
        System.out.println("Enter a new contact name: ");
        String newName = scanner.nextLine();
        System.out.println("Enter a new contact number: ");
        String newNumber = scanner.nextLine();
        Contact modifiedContact = Contact.createContact(newName, newNumber);
        if (mobilePhone.contactExists(modifiedContact)) {
            System.out.println("Error modifying contact record.");
        } else {
            mobilePhone.modifyContact(existingContact, modifiedContact);
        }
    }

    private static void addContact() {
        System.out.println("Please enter the name of the contact you would like to add: ");
        String name = scanner.nextLine();
        System.out.println("Next, please enter the phone number of the contact: ");
        String phoneNumber = scanner.nextLine();
        Contact newContact = Contact.createContact(name, phoneNumber); // Creates new Contact object
        if (mobilePhone.storeContact(newContact)) {
            System.out.println("Contact added to phone: " + name + "\nNumber: " + phoneNumber);
        } else {
            System.out.println("Unable to add" + name + "\n" + phoneNumber + " already exists.");
        }
    }

    private static void printMenuOptions() {
        System.out.println("\n Press ");
        System.out.println("\n 0 - To display the options menu.");
        System.out.println("\n 1 - To display your list of contacts.");
        System.out.println("\n 2 - To add a new contact.");
        System.out.println("\n 3 - To modify a contact.");
        System.out.println("\n 4 - To delete a contact.");
        System.out.println("\n 5 - To search for a contact.");
        System.out.println("\n 6 - To exit.");
    }
}
