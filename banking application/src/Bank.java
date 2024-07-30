import java.util.Scanner;

public class Bank {
    static UserDAO userDAO = new UserDAO();
    private static User loggedInUser;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            loggedInUser = userDAO.login();
            if(loggedInUser == null) System.out.println("Wrong Credentials.");
        }
        while(loggedInUser == null);

        while (loggedInUser != null) {

        System.out.println("welcome to the bank of java");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transferring");
        System.out.println("5. Logout");
        option = scanner.nextInt();


            switch (option) {
                case 1:
                    showBalance();
                    break;
                case 2:
                    deposit();
                    break;
                case 3:
                    withdraw();
                    break;
                case 4:
                    transferring();
                    break;
                case 5:
                    logout();
                    break;
                default:
                    System.out.println(" Invalid option");
                    break;
            }
        }

    }

    public static long checkBalance() {
        return userDAO.checkBalanceByAccountNumber(loggedInUser.getAccount_number());
    }

    public static void showBalance() {
        System.out.println("Your current balance is Rs." + checkBalance());
    }

    public static void withdraw() {

        Scanner scanner = new Scanner(System.in);
        System.out.println(" Enter the amount to withdraw Rs.");
        long requestedAmount = scanner.nextLong();

        if (requestedAmount <= checkBalance()) {
            userDAO.updateBalanceByAccountNumber(
                    loggedInUser.getAccount_number(),
                    -requestedAmount);  // - for subtracting the withdrawal amount

            System.out.println("Rs." + requestedAmount + " has been withdraw from your account");

        } else {
            System.out.println(" insufficient funds");
        }
        showBalance();
    }

    public static void deposit(){

        Scanner scanner = new Scanner(System.in);
        System.out.println(" Enter the amount to deposit Rs.");
        long requestedAmount = scanner.nextLong();

        userDAO.updateBalanceByAccountNumber(
                loggedInUser.getAccount_number(),
                requestedAmount);

        System.out.println("Rs." + requestedAmount + " has been deposited to your account");
        showBalance();
    }

    public static void transferring(){

        Scanner scanner = new Scanner(System.in);
        System.out.println(" Enter the amount to Transfer Rs.");
        long requestedAmount = scanner.nextLong();

        if (requestedAmount <= checkBalance()) {

            System.out.println(" Enter the Account Number to Transfer Money");
            long receiverAccountNumber = scanner.nextLong();

            User receiver = userDAO.getUserByAccountNumber(receiverAccountNumber);

            if(receiver != null) {
                userDAO.updateBalanceByAccountNumber(
                        loggedInUser.getAccount_number(),
                        -requestedAmount);  // - for subtracting the transferring amount

                userDAO.updateBalanceByAccountNumber(
                        receiver.getAccount_number(),
                        requestedAmount);  // - for adding the receiving amount

                System.out.println("Rs." + requestedAmount + " has been transferred from your account");
            } else System.out.println("Receiver Account not Found on given Account Number.");

        } else {
            System.out.println(" insufficient funds");
        }
        showBalance();

    }

    public static void logout() {
        loggedInUser = null;
        System.out.println("Thank you for banking with us");

    }
}