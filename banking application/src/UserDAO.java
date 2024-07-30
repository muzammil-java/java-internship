import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserDAO {

    public void addUser(String name, long nic, long contact, long accountNumber) {
        String query = "INSERT INTO Patrons (name, nic, contact, account_number) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setLong(2, nic);
            stmt.setLong(3, contact);
            stmt.setLong(4, accountNumber);

            stmt.executeUpdate();
            System.out.println("Registered new patron: " + name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public User getUserByNic(long nic) {
        User user = new User();
        String query = "SELECT * FROM user_info where nic = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, nic);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                user = (new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getLong("nic"),
                        rs.getLong("contact"),
                        rs.getInt("pin"),
                        rs.getLong("balance"),
                        rs.getLong("account_number")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public User getUserByAccountNumber(long accountNumber) {
        User user = new User();
        String query = "SELECT * FROM user_info where account_number = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, accountNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return (new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getLong("nic"),
                        rs.getLong("contact"),
                        rs.getInt("pin"),
                        rs.getLong("balance"),
                        rs.getLong("account_number")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM Patrons";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getLong("nic"),
                        rs.getLong("contact"),
                        rs.getInt("pin"),
                        rs.getLong("balance"),
                        rs.getLong("account_number")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public long checkBalanceByAccountNumber(long accountNumber) {

        String query = "SELECT balance FROM user_info where account_number = ?";
        long balance = 0;
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, accountNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                balance = rs.getLong("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return balance;
    }

    public void updateBalanceByAccountNumber(long accountNumber, long requestedAmount){

        String query = "UPDATE user_info SET balance = ? WHERE account_number = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, checkBalanceByAccountNumber(accountNumber) + requestedAmount);
            stmt.setLong(2, accountNumber);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public User login() {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter NIC: ");
        long nic = scanner.nextLong();
        System.out.print("Enter PIN: ");
        int pin = scanner.nextInt();

        String query = "SELECT * FROM user_info where nic = ? and pin = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setLong(1, nic);
            stmt.setInt(2, pin);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return (new User(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getLong("nic"),
                        rs.getLong("contact"),
                        rs.getInt("pin"),
                        rs.getLong("balance"),
                        rs.getLong("account_number")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}
