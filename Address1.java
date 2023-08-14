import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Address1 {

    private String name;
    private String phone_number;
    private String email_address;

    public Address1(String name, String phone_number, String email_address) {
        this.name = name;
        this.phone_number = phone_number;
        this.email_address = email_address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    @Override
    public String toString() {
        return name + " (" + phone_number + ") (" + email_address + ")";
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            
            System.out.println("Enter the contact's name:");
            String name = scanner.nextLine();
            System.out.println("Enter the contact's phone number:");
            String phone_number = scanner.nextLine();
            System.out.println("Enter the contact's email address:");
            String email_address = scanner.nextLine();

        
            Address1 contact = new Address1(name, phone_number, email_address);

          
            List<Address1> contacts = Arrays.asList(contact);
            contacts.sort(new Comparator<Address1>() {
                @Override
                public int compare(Address1 contact1, Address1 contact2) {
                    return contact1.getName().compareTo(contact2.getName());
                }
            });

            
            for (Address1 contact1 : contacts) {
                System.out.println(contact1);
            }
        }
    }
}
