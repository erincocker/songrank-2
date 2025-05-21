package dataStructures;

import java.util.List;

public class Playlist {
    private List<Track> items;
    private String name;

    public Playlist(String playlistFilePath) {
        //here will add jackson code to convert json file to pojo!
    }

    public Playlist(List<Track> items, String name) {
        this.items = items;
        this.name = name;
    }

    public List<Track> getItems() {
        return items;
    }

    public void setItems(List<Track> items) {
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

