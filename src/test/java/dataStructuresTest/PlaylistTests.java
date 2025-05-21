package dataStructuresTest;

import dataStructures.Playlist;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlaylistTests {
    static Playlist playlist;

    @BeforeAll
    public static void beforeAll() {
        playlist = new Playlist("src/main/resources/testPlaylist.json");
    }

    @Test
    public void getFirstSongDetailsTest() {
        assertEquals("Socks", playlist.items().get(0).name());
    }
}
