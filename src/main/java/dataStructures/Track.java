package dataStructures;

import java.util.List;

public class Track {
        private List<String> artists;
        private int id;
        private String image;
        private String name;
        private String spotify_id;

    public Track(List<String> artists, int id, String image, String name, String spotify_id) {
        this.artists = artists;
        this.id = id;
        this.image = image;
        this.name = name;
        this.spotify_id = spotify_id;
    }

    public List<String> getArtists() {
        return artists;
    }

    public void setArtists(List<String> artists) {
        this.artists = artists;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpotify_id() {
        return spotify_id;
    }

    public void setSpotify_id(String spotify_id) {
        this.spotify_id = spotify_id;
    }
}
