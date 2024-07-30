public class Song {
    private int songId;
    private String title;
    private String artist;
    private String album;
    private String url;

    public Song(int songId, String title, String artist, String album, String url) {
        this.songId = songId;
        this.title = title;
        this.artist = artist;
        this.album = album;
        this.url = url;
    }

    // Getters and setters
    public int getSongId() {
        return songId;
    }

    public void setSongId(int songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Song{" +
                " songId=" + songId +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", album='" + album + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
