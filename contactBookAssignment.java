import java.util.Scanner;

class Contact {
    String name;
    String phoneNumber;
    String email;
    Contact next;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.next = null;
    }
}

class ContactBook {
    Contact head;

    public ContactBook() {
        this.head = null;
    }

    public void addContact(String name, String phoneNumber, String email) {
        Contact newContact = new Contact(name, phoneNumber, email);
        if (head == null) {
            head = newContact;
        } else {
            Contact current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newContact;
        }
    }

    public void deleteContact(String name) {
        if (head == null) {
            System.out.println("Contact book is empty.");
            return;
        }
        if (head.name.equalsIgnoreCase(name)) {
            head = head.next;
            System.out.println("Contact deleted successfully!");
            return;
        }

        Contact current = head;
        while (current.next != null) {
            if (current.next.name.equalsIgnoreCase(name)) {
                current.next = current.next.next;
                System.out.println("Contact deleted successfully!");
                return;
            }
            current = current.next;
        }
        System.out.println("Contact not found.");
    }

    public void searchByEmail(String email) {
        Contact current = head;
        boolean found = false;
        while (current != null) {
            if (current.email.equalsIgnoreCase(email)) {
                System.out.println("Name: " + current.name);
                System.out.println("Phone Number: " + current.phoneNumber);
                System.out.println("Email: " + current.email);
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No contact found with email: " + email);
        }
    }

    public void displayContacts() {
        Contact current = head;
        if (current == null) {
            System.out.println("Contact book is empty.");
            return;
        }
        System.out.println("Contacts:");
        while (current != null) {
            System.out.println("Name: " + current.name);
            System.out.println("Phone Number: " + current.phoneNumber);
            System.out.println("Email: " + current.email);
            System.out.println("-------------------------");
            current = current.next;
        }
    }

    public void searchContact(String name) {
        Contact current = head;
        boolean found = false;
        while (current != null) {
            if (current.name.toLowerCase().contains(name.toLowerCase())) {
                System.out.println("Name: " + current.name);
                System.out.println("Phone Number: " + current.phoneNumber);
                System.out.println("Email: " + current.email);
                System.out.println();
                found = true;
            }
            current = current.next;
        }
        if (!found) {
            System.out.println("No contact found with name: " + name);
        }
    }
}

public class contactBookAssignment {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactBook contactBook = new ContactBook();

        while (true) {
            System.out.println("***********************************");
            System.out.println("(A)dd\n(D)elete\n(E)mail Search\n(P)rint List\n(S)earch\n(Q)uit");
            System.out.println("***********************************");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "A":
                    boolean validFlag = false;
                    while (!validFlag){
                        System.out.print("Enter name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter phone number: ");
                        String phoneNumber = scanner.nextLine();
                        System.out.print("Enter email: ");
                        String email = scanner.nextLine().toLowerCase();

                        if (name.isEmpty() || phoneNumber.isEmpty() || email.isEmpty()) {
                            System.out.println("One or more fields are empty. Please fill all fields.");
                            continue;
                        }

                        contactBook.addContact(name, phoneNumber, email);
                        System.out.println("Contact added successfully!");
                        validFlag = true;
                    }

                    break;
                case "D":
                    System.out.print("Enter name to delete: ");
                    String deleteName = scanner.nextLine();
                    contactBook.deleteContact(deleteName);
                    break;
                case "E":
                    System.out.print("Enter email to search: ");
                    String searchEmail = scanner.nextLine();
                    contactBook.searchByEmail(searchEmail);
                    break;
                case "P":
                    contactBook.displayContacts();
                    break;
                case "S":
                    System.out.print("Enter name to search: ");
                    String searchName = scanner.nextLine();
                    contactBook.searchContact(searchName);
                    break;
                case "Q":
                    System.out.println("Thank you, bye-bye");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}