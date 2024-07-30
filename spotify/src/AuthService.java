import java.util.Scanner;

public class AuthService {
    private Scanner scanner = new Scanner(System.in);

    public void register() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        User newUser = new User(0, username, password, email);
        try {
            UserDAO.registerUser(newUser);
            System.out.println("Registration successful!");
        } catch (Exception e) {
            System.out.println("Error during registration: " + e.getMessage());
        }
    }

    public User login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            User user = UserDAO.loginUser(username, password);
            if (user != null) {
                System.out.println("Login successful!");
                return user;
            } else {
                System.out.println("Invalid credentials.");
            }
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
        }
        return null;
    }
}
