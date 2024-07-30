import java.util.Scanner;

public class Main {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Library Management System ---");
            System.out.println("1. Add New Book");
            System.out.println("2. Register New Patron");
            System.out.println("3. Issue Book");
            System.out.println("4. Return Book");
            System.out.println("5. Display All Books");
            System.out.println("6. Display All Patrons");
            System.out.println("7. Display All Transactions");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    addNewBook();
                    break;
                case 2:
                    registerNewPatron();
                    break;
                case 3:
                    issueBook();
                    break;
                case 4:
                    returnBook();
                    break;
                case 5:
                    library.displayBooks();
                    break;
                case 6:
                    library.displayPatrons();
                    break;
                case 7:
                    library.displayTransactions();
                    break;
                case 8:
                    System.out.println("Exiting...");
                    System.exit(0);
            }
        }
    }

    private static void addNewBook() {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        library.addBook(title, author);
    }

    private static void registerNewPatron() {
        System.out.print("Enter patron name: ");
        String name = scanner.nextLine();
        System.out.print("Enter patron contact info: ");
        String contactInfo = scanner.nextLine();
        library.registerPatron(name, contactInfo);
    }

    private static void issueBook() {
        System.out.print("Enter book ID to issue: ");
        int bookId = scanner.nextInt();
        System.out.print("Enter patron ID: ");
        int patronId = scanner.nextInt();
        library.issueBook(bookId, patronId);
    }

    private static void returnBook() {
        System.out.print("Enter transaction ID to return: ");
        int transactionId = scanner.nextInt();
        library.returnBook(transactionId);
    }
}
