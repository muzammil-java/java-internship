import java.util.Date;

public class Transaction {
    private int id;
    private Book book;
    private Patron patron;
    private Date issueDate;
    private Date returnDate;

    public Transaction(int id, Book book, Patron patron, Date issueDate, Date returnDate) {
        this.id = id;
        this.book = book;
        this.patron = patron;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    public Transaction() { }

    public Book getBook() {
        return book;
    }

    public Patron getPatron() {
        return patron;
    }

    public Date getReturnDate() {
        return returnDate;
    }



    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", book=" + book +
                ", patron=" + patron +
                ", issueDate=" + issueDate +
                ", returnDate=" + returnDate +
                '}';
    }
}

