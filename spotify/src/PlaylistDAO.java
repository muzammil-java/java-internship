import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDAO {

    public List<Playlist> getAllPlaylistsByUserId(int userId) {
        List<Playlist> playlists = new ArrayList<>();
        String query = "SELECT * FROM playlist WHERE user_id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("playlist_id");
                String name = rs.getString("playlist_name");
                playlists.add(new Playlist(id, name, userId));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving playlists: " + e.getMessage());
        }
        return playlists;
    }

    public Playlist getPlaylistById(int playlistId) {
        String query = "SELECT * FROM playlist WHERE playlist_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, playlistId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("playlist_name");
                int userId = rs.getInt("user_id");
                return new Playlist(playlistId, name, userId);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving playlist: " + e.getMessage());
        }
        return null;
    }

    public void addPlaylist(Playlist playlist) {
        String query = "INSERT INTO playlist (playlist_name, user_id) VALUES (?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, playlist.getPlaylistName());
            stmt.setInt(2, playlist.getUserId());
            stmt.executeUpdate();
            System.out.println("Playlist added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding playlist: " + e.getMessage());
        }
    }

    public void updatePlaylist(Playlist playlist) {
        String query = "UPDATE playlist SET playlist_name = ? WHERE playlist_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, playlist.getPlaylistName());
            stmt.setInt(2, playlist.getPlaylistId());
            stmt.executeUpdate();
            System.out.println("Playlist updated successfully!");
        } catch (SQLException e) {
            System.out.println("Error updating playlist: " + e.getMessage());
        }
    }

    public void deletePlaylist(int playlistId) {
        String query = "DELETE FROM playlist WHERE playlist_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, playlistId);
            stmt.executeUpdate();
            System.out.println("Playlist deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting playlist: " + e.getMessage());
        }
    }
}
