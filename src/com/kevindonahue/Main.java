package com.kevindonahue;

/* Simple Mobile Phone Contacts Application */
/* By Kevin Donahue */

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static MobilePhone mobilePhone = new MobilePhone();

    public static void main(String[] args) {

        boolean quit = false;
        int choice;
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
                    modifyContactName();
                    break;
                case 4:
                    modifyContactNumber();
                    break;
                case 5:
                    deleteContact();
                    break;
                case 6:
                    searchForContact();
                    break;
                case 7:
                    System.out.println("\nExiting...");
                    quit = true;
                    break;
            }
        }
    }

    private static void searchForContact() {
        System.out.println("Enter the contact name you want to search for: ");
        String contactToSearch = scanner.nextLine();
        Contact searchContact = getContactFromName(contactToSearch);
        if (mobilePhone.contactExists(searchContact)) {
            System.out.println("Contact " + searchContact.getName() + " was found. Their number is " + searchContact.getNumber());
        }
    }

    private static void deleteContact() {
        System.out.println("Please enter the name of the contact you would like to delete: ");
        String contactToDelete = scanner.nextLine();
        Contact delete = getContactFromName(contactToDelete);
        mobilePhone.deleteContact(delete);
    }

    private static Contact getContactFromName(String contactName) {
        for (int i = 0; i < mobilePhone.getContactsList().size(); i++) {
            if (mobilePhone.getContactsList().get(i).getName().equalsIgnoreCase(contactName)) {
                return mobilePhone.getContactsList().get(i);
            }
        }
        return null;
    }

    private static void modifyContactName() {
        System.out.println("Please enter the name of the contact you would like to modify: ");
        String contactToModify = scanner.nextLine();
        Contact originalName = getContactFromName(contactToModify);
        System.out.println("Enter the new name for this contact: ");
        originalName.setName(scanner.nextLine());
    }

    private static void modifyContactNumber() {
        System.out.println("Please enter the name of the contact you would like to modify: ");
        String contactToModify = scanner.nextLine();
        Contact originalNumber = getContactFromName(contactToModify);
        System.out.println("Please enter the new phone number for this contact: ");
        originalNumber.setNumber(scanner.nextLine());
    }

    private static void addContact() {
        System.out.println("Please enter the name of the contact you would like to add: ");
        String name = scanner.nextLine();
        System.out.println("Next, please enter the phone number of the contact: ");
        String phoneNumber = scanner.nextLine();
        Contact newContact = new Contact(name, phoneNumber);
        mobilePhone.storeContact(newContact);
    }

    private static void printMenuOptions() {
        System.out.println("\n Press ");
        System.out.println("\n 0 - To display the options menu.");
        System.out.println("\n 1 - To display your list of contacts.");
        System.out.println("\n 2 - To add a new contact.");
        System.out.println("\n 3 - To modify a contact Name.");
        System.out.println("\n 4 - To modify a contact Number.");
        System.out.println("\n 5 - To delete a contact.");
        System.out.println("\n 6 - To search for a contact.");
        System.out.println("\n 7 - To exit.");
    }
}
