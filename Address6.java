import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Address6 {
    private static ArrayList<Contact> contacts = new ArrayList<>();
    private static final String FILE_NAME = "address_book.txt";

    public static void main(String[] args) {
        System.out.println("Welcome to Address Book System!");

   
        readContactsFromFile();

        boolean running = true;
        Scanner scanner = new Scanner(System.in);

        while (running) {
            String command = showMenuAndGetCommand(scanner);

            switch (command) {
                case "1":
                    addContact(scanner);
                    break;
                case "2":
                    editContact(scanner);
                    break;
                case "3":
                    searchContact(scanner);
                    break;
                case "4":
                    displayAllContacts();
                    break;
                case "5":
                    saveContactsToFile();
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
        System.out.println("1. Add New Contact");
        System.out.println("2. Edit Contact");
        System.out.println("3. Search Contact");
        System.out.println("4. Display All Contacts");
        System.out.println("5. Save and Exit");
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

    
        while (name.isEmpty() || phoneNumber.isEmpty() || emailAddress.isEmpty()) {
            System.out.println("All fields are required. Please try again.");
            System.out.print("Enter contact name: ");
            name = scanner.nextLine();
            System.out.print("Enter phone number: ");
            phoneNumber = scanner.nextLine();
            System.out.print("Enter email address: ");
            emailAddress = scanner.nextLine();
        }

        Contact newContact = new Contact(name, phoneNumber, emailAddress);
        contacts.add(newContact);

        System.out.println("Contact added successfully!");
    }

    private static void editContact(Scanner scanner) {
        System.out.print("Enter the name of the contact to edit: ");
        String searchName = scanner.nextLine();

        boolean found = false;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(searchName)) {
                System.out.print("Enter new phone number: ");
                String newPhoneNumber = scanner.nextLine();
                System.out.print("Enter new email address: ");
                String newEmailAddress = scanner.nextLine();

                
                while (!isValidPhoneNumber(newPhoneNumber) || !isValidEmailAddress(newEmailAddress)) {
                    System.out.println("Invalid phone number or email address format. Please try again.");
                    System.out.print("Enter new phone number: ");
                    newPhoneNumber = scanner.nextLine();
                    System.out.print("Enter new email address: ");
                    newEmailAddress = scanner.nextLine();
                }

                contact.setPhoneNumber(newPhoneNumber);
                contact.setEmailAddress(newEmailAddress);

                System.out.println("Contact updated successfully!");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Contact not found for the name: " + searchName);
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

    private static void displayAllContacts() {
        System.out.println("\nAll contacts in the address book:");
        for (Contact contact : contacts) {
            System.out.println(contact);
            System.out.println("----------------------");
        }
    }

    private static void saveContactsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Contact contact : contacts) {
                writer.write(contact.getName() + "," + contact.getPhoneNumber() + "," + contact.getEmailAddress());
                writer.newLine();
            }
            System.out.println("Contact data has been saved to the file.");
        } catch (IOException e) {
            System.err.println("Error saving contact data to the file: " + e.getMessage());
        }
    }

    private static void readContactsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    String phoneNumber = parts[1].trim();
                    String emailAddress = parts[2].trim();
                    contacts.add(new Contact(name, phoneNumber, emailAddress));
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading contact data from the file: " + e.getMessage());
        }
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
      
        return phoneNumber.matches("\\d+");
    }

    private static boolean isValidEmailAddress(String emailAddress) {
        
        return emailAddress.contains("@");
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

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public String toString() {
            return "Name: " + name + "\nPhone Number: " + phoneNumber + "\nEmail Address: " + emailAddress;
        }
    }
}
