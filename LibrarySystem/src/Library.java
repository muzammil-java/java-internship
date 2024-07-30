import java.util.List;

public class Library {

    public Library() {
    }

    // Add a new book
    public void addBook(String title, String author) {
        BookDAO bookDAO = new BookDAO();
        bookDAO.addBook(title, author);
        System.out.println("Book added successfully");
    }

    // Register a new patron
    public void registerPatron(String name, String contactInfo) {
        PatronDAO patronDAO = new PatronDAO();
        patronDAO.addPatron(name,contactInfo);
        System.out.println("Patron registered successfully");
    }

    // Issue a book
    public void issueBook(int bookId, int patronId) {
        Book book = findBookById(bookId);
        Patron patron = findPatronById(patronId);
        if (book != null && patron != null && book.isAvailable()) {
            TransactionDAO transactionDAO = new TransactionDAO();
            transactionDAO.issueBook(bookId, patronId);

            BookDAO bookDAO = new BookDAO();
            bookDAO.updateBookAvailability(bookId, false);

            System.out.println("Issued book: " + book + " to patron: " + patron);
        } else {
            System.out.println("Cannot issue book. Either book is not available or patron does not exist.");
        }
    }

    // Return a book
    public void returnBook(int transactionId) {
        Transaction transaction = findTransactionById(transactionId);
        if (transaction != null && transaction.getReturnDate() == null) {
            TransactionDAO transactionDAO = new TransactionDAO();
            transactionDAO.returnBook(transactionId);

            BookDAO bookDAO = new BookDAO();
            bookDAO.updateBookAvailability(transaction.getBook().getId(), true);

            System.out.println("Returned book: " + transaction.getBook() + " by patron: " + transaction.getPatron());
        } else {
            System.out.println("Cannot return book. Transaction not found or book already returned.");
        }
    }

    // Find book by ID
    private Book findBookById(int id) {
        BookDAO bookDAO = new BookDAO();
        return bookDAO.getBookById(id);
    }

    // Find patron by ID
    private Patron findPatronById(int id) {
        PatronDAO patronDAO = new PatronDAO();
        return patronDAO.getPatronById(id);
    }

    // Find transaction by ID
    private Transaction findTransactionById(int id) {
        TransactionDAO transactionDAO = new TransactionDAO();
        return transactionDAO.getTransactionById(id);
    }

    // Display all books
    public void displayBooks() {
        System.out.println("\n--- Books ---");
        BookDAO bookDAO = new BookDAO();
        List<Book> bookList = bookDAO.getAllBooks();
        for(Book book : bookList){
            System.out.println(book.toString());
        }
    }

    // Display all patrons
    public void displayPatrons() {
        System.out.println("\n--- Patrons ---");
        PatronDAO patronDAO = new PatronDAO();
        List<Patron> patronList = patronDAO.getAllPatrons();
        for(Patron patron : patronList){
            System.out.println(patron.toString());
        }
    }

    // Display all transactions
    public void displayTransactions() {
        System.out.println("\n--- Transactions ---");
        TransactionDAO transactionDAO = new TransactionDAO();
        List<Transaction> transactionList = transactionDAO.getAllTransactions();
        for(Transaction transaction : transactionList){
            System.out.println(transaction.toString());
        }
    }
}


