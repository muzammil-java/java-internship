import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    public void issueBook(int bookId, int patronId) {
        String query = "INSERT INTO Transactions (bookId, patronId, issueDate) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookId);
            stmt.setInt(2, patronId);
            stmt.setDate(3, new Date(System.currentTimeMillis()));
            stmt.executeUpdate();
            System.out.println("Issued book ID " + bookId + " to patron ID " + patronId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnBook(int transactionId) {
        String query = "UPDATE Transactions SET returnDate = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, new Date(System.currentTimeMillis()));
            stmt.setInt(2, transactionId);
            stmt.executeUpdate();
            System.out.println("Returned book for transaction ID " + transactionId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String query = "SELECT * FROM Transactions";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                BookDAO bookDAO = new BookDAO();
                PatronDAO patronDAO = new PatronDAO();
                Book book = bookDAO.getBookById(rs.getInt("bookId"));
                Patron patron = patronDAO.getPatronById(rs.getInt("patronId"));
                transactions.add(new Transaction(rs.getInt("id"), book, patron, rs.getDate("issueDate"), rs.getDate("returnDate")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    public Transaction getTransactionById(int transactionId) {
        Transaction transaction = null;
        String query = "SELECT * FROM Transactions WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, transactionId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    BookDAO bookDAO = new BookDAO();
                    PatronDAO patronDAO = new PatronDAO();
                    Book book = bookDAO.getBookById(rs.getInt("bookId"));
                    Patron patron = patronDAO.getPatronById(rs.getInt("patronId"));
                    transaction = new Transaction(rs.getInt("id"), book, patron, rs.getDate("issueDate"), rs.getDate("returnDate"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transaction;
    }

}


