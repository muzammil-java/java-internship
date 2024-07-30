import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ConsoleApp {
    private static final AuthService authService = new AuthService();
    private static User loggedInUser;
    private static final PlaylistDAO playlistDAO = new PlaylistDAO();
    private static final SongDAO songDAO = new SongDAO();
    private static final SongService songService = new SongService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Spotify Clone Console App ---");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    authService.register();
                    break;
                case 2:
                    loggedInUser = authService.login();
                    if (loggedInUser != null) {
                        showUserMenu();
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    System.exit(0);
            }
        }
    }

    private static void showUserMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- User Menu ---");
            System.out.println("1. Create Playlist");
            System.out.println("2. View Playlists");
            System.out.println("3. Add Song to Playlist");
            System.out.println("4. Play Song");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (option) {
                case 1:
                    createPlaylist();
                    break;
                case 2:
                    viewPlaylists();
                    break;
                case 3:
                    addSongToPlaylist();
                    break;
                case 4:
                    playSong();
                    break;
                case 5:
                    loggedInUser = null;
                    System.out.println("Logged out.");
                    return;
            }
        }
    }

    private static void createPlaylist() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter playlist name: ");
        String name = scanner.nextLine();
        Playlist playlist = new Playlist(0, name, loggedInUser.getUserId());
        playlistDAO.addPlaylist(playlist);
    }

    private static void viewPlaylists() {
        System.out.println("\n--- Your Playlists ---");
        for (Playlist playlist : playlistDAO.getAllPlaylistsByUserId(loggedInUser.getUserId())) {
            System.out.println(playlist);
        }
    }

    private static void addSongToPlaylist() {
        Scanner scanner = new Scanner(System.in);

        // Get the list of playlists
        viewPlaylists();
        System.out.print("Enter playlist ID to add a song to: ");
        int playlistId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        List<Song> songs = songDAO.getAllSongs();
        System.out.println("\n--- Available Songs ---");
        for (Song song : songs) {
            System.out.println(song);
        }

        System.out.print("Enter song ID to add to the playlist: ");
        int songId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        String insertQuery = "INSERT INTO PlaylistSong (playlist_id, song_id, position) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
            stmt.setInt(1, playlistId);
            stmt.setInt(2, songId);
            stmt.setInt(3, 1);  // Position is hardcoded to 1, can be changed as needed
            stmt.executeUpdate();
            System.out.println("Song added to playlist successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding song to playlist: " + e.getMessage());
        }
    }

    private static void playSong() {
        Scanner scanner = new Scanner(System.in);

        // List all songs
        List<Song> songs = songDAO.getAllSongs();
        System.out.println("\n--- Available Songs ---");
        for (Song song : songs) {
            System.out.println(song);
        }

        System.out.print("Enter song ID to play: ");
        int songId = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Song song = songDAO.getSongById(songId);
        if (song != null) {
            songService.playSong(song);
        } else {
            System.out.println("Song not found!");
        }
    }

    private static void playSelectedSong(Song song) {
        if (song != null) {
            songService.playSong(song);
        } else {
            System.out.println("No song selected!");
        }
    }
}
