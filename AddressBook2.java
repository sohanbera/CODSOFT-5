import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class AddressBook2 {
    private ArrayList<Contact> contacts;

    public AddressBook2() {
        contacts = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void removeContact(Contact contact) {
        contacts.remove(contact);
    }

    public Contact searchContact(String name) {
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                return contact;
            }
        }
        return null; // Contact not found
    }

    public void displayAllContacts() {
        Collections.sort(contacts, new Comparator<Contact>() {
            @Override
            public int compare(Contact contact1, Contact contact2) {
                return contact1.getName().compareToIgnoreCase(contact2.getName());
            }
        });

        for (Contact contact : contacts) {
            System.out.println(contact);
            System.out.println("----------------------");
        }
    }

    public static void main(String[] args) {
        AddressBook2 addressBook = new AddressBook2();
        Scanner scanner = new Scanner(System.in);

     
        System.out.println("Adding contacts to the address book:");
        String addMoreContacts = "Y";
        while (addMoreContacts.equalsIgnoreCase("Y")) {
            System.out.print("Enter contact name: ");
            String name = scanner.nextLine();
            System.out.print("Enter phone number: ");
            String phoneNumber = scanner.nextLine();
            System.out.print("Enter email address: ");
            String emailAddress = scanner.nextLine();

            Contact newContact = addressBook.new Contact(name, phoneNumber, emailAddress);
            addressBook.addContact(newContact);

            System.out.print("Do you want to add more contacts? (Y/N): ");
            addMoreContacts = scanner.nextLine();
        }

     
        System.out.println("All contacts in the address book:");
        addressBook.displayAllContacts();

    
        System.out.print("Enter the name of the contact to search: ");
        String searchName = scanner.nextLine();
        Contact foundContact = addressBook.searchContact(searchName);
        if (foundContact != null) {
            System.out.println("Contact found:");
            System.out.println(foundContact);
        } else {
            System.out.println("Contact not found for the name: " + searchName);
        }

       
        System.out.print("Enter the name of the contact to remove: ");
        String removeName = scanner.nextLine();
        Contact removeContact = addressBook.searchContact(removeName);
        if (removeContact != null) {
            addressBook.removeContact(removeContact);
            System.out.println("Contact removed successfully.");
        } else {
            System.out.println("Contact not found for the name: " + removeName);
        }

       
        System.out.println("All contacts in the address book after removal:");
        addressBook.displayAllContacts();

        scanner.close();
    }

    public class Contact {
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

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        @Override
        public String toString() {
            return "Name: " + name + "\nPhone Number: " + phoneNumber + "\nEmail Address: " + emailAddress;
        }
    }
}
