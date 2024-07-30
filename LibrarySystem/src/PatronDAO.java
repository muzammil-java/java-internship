import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatronDAO {
    public void addPatron(String name, String contactInfo) {
        String query = "INSERT INTO Patrons (name, contactInfo) VALUES (?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setString(2, contactInfo);
            stmt.executeUpdate();
            System.out.println("Registered new patron: " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Patron getPatronById(int patronId) {
        Patron patron = new Patron();
        String query = "SELECT * FROM Patrons where id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, patronId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                patron = (new Patron(rs.getInt("id"), rs.getString("name"), rs.getString("contactInfo")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patron;
    }

    public List<Patron> getAllPatrons() {
        List<Patron> patrons = new ArrayList<>();
        String query = "SELECT * FROM Patrons";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                patrons.add(new Patron(rs.getInt("id"), rs.getString("name"), rs.getString("contactInfo")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patrons;
    }
}

