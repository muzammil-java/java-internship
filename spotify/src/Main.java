//import java.sql.*;
//import java.util.Scanner;
//
//public class Main {
//
//    public static void main(String[] args) throws SQLException {
//
////        Song song1 = new Song();
////        song1.setName("Aadat");
////        song1.setArtist("Atif Aslam");
////        song1.setType("Music");
////        song1.setYear(2000);
////
////
////        Song song2 = new Song("Rafta", "Atif Aslam", "Music", 2023);
//
//        Connection con = getDbConnection();
//
////        String query = "INSERT INTO song (name, artist, type, year) VALUES (\"Zalima\", \"Arjit\", \"Music\", 2000)";
//
//        String query = "UPDATE song SET name = \"Husn\" WHERE id = 5";
//
////        String query = "DELETE FROM song WHERE id = 5";
//
////        String query = "SELECT * from song WHERE id = 1";
//
//        Statement st = con.createStatement();
//        st.execute(query); // ExecuteUpdate for CREATE, UPDATE and DELETE
////        ResultSet rs = st.executeQuery(query);    // ExecuteQuery for READ
//
////        while(rs.next()){
////            System.out.println("Name: " + rs.getString("name"));
////            System.out.println("Artist: " + rs.getString("artist"));
////            System.out.println("Type: " + rs.getString("type"));
////            System.out.println("Year: " + rs.getInt("year"));
////        }
//
//        System.out.println("Successful");
//
//        st.close(); // close statement
//        con.close(); // close connection
//
//        System.out.println("Connection Closed....");
//    }
//
//    private static Connection getDbConnection() {
//        String url = "jdbc:mysql://localhost:3306/spotify_db"; // database details
//        String username = "root"; // MySQL credentials
//        String password = "root";
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver"); // Driver name
//            Connection con = DriverManager.getConnection(url, username, password);
//            if(con != null){
//                System.out.println("Connection Established Successfully");
//                return con;
//            } else System.out.println("Connection Not Established");
//
//        } catch (Exception e) {
//            e.getStackTrace();
//        }
//        return null;
//    }
//
//
////    private static void login() {
////        Scanner myobj = new Scanner(System.in);
////        System.out.println("Enter Username:");
////        String username = myobj.next();
////
////        System.out.println("Enter Password:");
////        String password = myobj.next();
////
////        if (USERNAME.equals(username) && PASSWORD.equals(password)) System.out.println("Login Successful");
////        else {
////            System.out.println(" Incorrect Username or Password");
////            login();
////        }
////
////    }
//}