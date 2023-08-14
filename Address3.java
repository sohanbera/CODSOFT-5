import java.util.ArrayList;
import java.util.Scanner;

public class Address3 {
    private static ArrayList<Contact> contacts = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book System!");

        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            String command = showMenuAndGetCommand(scanner);

            switch (command) {
                case "1":
                    addContact(scanner);
                    break;
                case "2":
                    displayAllContacts();
                    break;
                case "3":
                    searchContact(scanner);
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }

        scanner.close();
        System.out.println("Goodbye! Exiting Address Book System.");
    }

    private static String showMenuAndGetCommand(Scanner scanner) {
        System.out.println("\nPlease choose an option:");
        System.out.println("1. Add Contact");
        System.out.println("2. Display All Contacts");
        System.out.println("3. Search Contact");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
        return scanner.nextLine();
    }

    private static void addContact(Scanner scanner) {
        System.out.print("Enter contact name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter email address: ");
        String emailAddress = scanner.nextLine();

        Contact newContact = new Contact(name, phoneNumber, emailAddress);
        contacts.add(newContact);

        System.out.println("Contact added successfully!");
    }

    private static void displayAllContacts() {
        System.out.println("\nAll contacts in the address book:");
        for (Contact contact : contacts) {
            System.out.println(contact);
            System.out.println("----------------------");
        }
    }

    private static void searchContact(Scanner scanner) {
        System.out.print("Enter the name of the contact to search: ");
        String searchName = scanner.nextLine();

        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(searchName)) {
                System.out.println("Contact found:");
                System.out.println(contact);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Contact not found for the name: " + searchName);
        }
    }

    private static class Contact {
        private String name;
        private String phoneNumber;
        private String emailAddress;

        public Contact(String name, String phoneNumber, String emailAddress) {
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.emailAddress = emailAddress;
        }

        public String getName() {
            return name;
        }

       
        public String toString() {
            return "Name: " + name + "\nPhone Number: " + phoneNumber + "\nEmail Address: " + emailAddress;
        }
    }
}