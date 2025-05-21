package dataStructures;

import java.util.ArrayList;
import java.util.List;

public record Playlist(List<Track> items, String name) {

    public Playlist(String filePath) {
        this(new ArrayList<>(), "name");
    }
}

