import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.IOException;

public class SongService {
    private AdvancedPlayer player;
    private FileInputStream fileInputStream;
    private Thread playbackThread;

    public void playSong(Song song) {
        stopSong();  // Stop any currently playing song

        try {
            String filePath = song.getUrl().replace("file:///", "");
            fileInputStream = new FileInputStream(filePath);

            // Create a new AdvancedPlayer to play the MP3 file
            player = new AdvancedPlayer(fileInputStream);
            playbackThread = new Thread(() -> {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    System.out.println("Error playing song: " + e.getMessage());
                }
            });

            playbackThread.start();
            System.out.println("Playing: " + song.getTitle());
        } catch (JavaLayerException | IOException e) {
            System.out.println("Error playing song: " + e.getMessage());
        }
    }

    public void stopSong() {
        if (player != null) {
            player.close();
            player = null;
            System.out.println("Stopped playing.");
        }
        if (fileInputStream != null) {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
        if (playbackThread != null) {
            playbackThread.interrupt();
            playbackThread = null;
        }
    }
}
