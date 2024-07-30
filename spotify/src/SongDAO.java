import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SongDAO {

    public List<Song> getAllSongs() {
        List<Song> songs = new ArrayList<>();
        String query = "SELECT * FROM song";


        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("song_id");
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String album = rs.getString("album");
                String url = rs.getString("url");
                songs.add(new Song(id, title, artist, album, url));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving songs: " + e.getMessage());
        }
        return songs;
    }

    public Song getSongById(int songId) {
        String query = "SELECT * FROM song WHERE song_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, songId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String title = rs.getString("title");
                String artist = rs.getString("artist");
                String album = rs.getString("album");
                String url = rs.getString("url");
                return new Song(songId, title, artist, album, url);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving song: " + e.getMessage());
        }
        return null;
    }

    public void addSong(Song song) {
        String query = "INSERT INTO song (title, artist, album) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, song.getTitle());
            stmt.setString(2, song.getArtist());
            stmt.setString(3, song.getAlbum());
            stmt.executeUpdate();
            System.out.println("Song added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding song: " + e.getMessage());
        }
    }

    public void updateSong(Song song) {
        String query = "UPDATE song SET title = ?, artist = ?, album = ?, url = ? WHERE song_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, song.getTitle());
            stmt.setString(2, song.getArtist());
            stmt.setString(3, song.getAlbum());
            stmt.setString(4, song.getUrl());
            stmt.setInt(5, song.getSongId());
            stmt.executeUpdate();
            System.out.println("Song updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating song: " + e.getMessage());
        }
    }

    public void deleteSong(int songId) {
        String query = "DELETE FROM song WHERE song_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, songId);
            stmt.executeUpdate();
            System.out.println("Song deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting song: " + e.getMessage());
        }
    }
}