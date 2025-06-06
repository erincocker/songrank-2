package dataStructuresTest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataStructures.SongLists;
import dataStructures.SongPair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SongListsTests {
    private static JsonNode playlist;
    private SongLists songLists;

    @BeforeAll
    public static void beforeAll() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            //this is a test playlist consisting of Seven (7) songs:
            playlist = objectMapper.readTree(new File("src/main/resources/testPlaylist.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void beforeEach() {
        songLists = new SongLists(playlist);
    }

    @Test
    public void blankComparisonListIsCreated() {
        songLists.getComparisonsList().forEach((k,v) -> assertEquals(Arrays.asList(0,0,0,0,0,0,0), v));
        assertEquals(6, songLists.getComparisonsList().size());
    }

    @Test
    public void logAComparison() {
        int rankedListSong = songLists.getRankedList().get(0);
        int comparisonsListSong = rankedListSong == 0 ? 1 : 0;

        songLists.addComparison(comparisonsListSong,true, rankedListSong);
        assertEquals(-1, songLists.getComparisonsList().get(comparisonsListSong).get(rankedListSong));
        songLists.addComparison(comparisonsListSong,false, rankedListSong);
        assertEquals(+1, songLists.getComparisonsList().get(comparisonsListSong).get(rankedListSong));
    }

    //ranked list should contain one number only: 0,1,2,3,4,5, or 6
    @Test
    public void initialRankedListIsCreated() {
        assert(songLists.getRankedList().size() == 1);
        assert(Arrays.asList(0,1,2,3,4,5,6).contains(songLists.getRankedList().get(0)));
    }

    @Test
    public void songCorrectlyAddedToRankedList() {
        int rankedListSong = songLists.getRankedList().get(0);
        int comparisonsListSong = rankedListSong == 0 ? 1 : 0;
        songLists.addComparison(comparisonsListSong, true, rankedListSong);
        songLists.tryAddSongToRankedList(comparisonsListSong);
        assert(songLists.getRankedList().get(0) == comparisonsListSong);
    }

    @Test
    public void songCorrectlyNotAddedToRankedList() {
        int rankedListSong = songLists.getRankedList().get(0);
        int comparisonsListSong = rankedListSong == 0 ? 1 : 0;
        songLists.tryAddSongToRankedList(comparisonsListSong);
        assert(songLists.getRankedList().get(0) == rankedListSong);
        assert(songLists.getRankedList().size() == 1);
    }

    @Test
    public void songCorrectlyAddedToEndOfRankedList() {
        int rankedListSong = songLists.getRankedList().get(0);
        int comparisonsListSong = rankedListSong == 0 ? 1 : 0;
        songLists.addComparison(comparisonsListSong, false, rankedListSong);
        songLists.tryAddSongToRankedList(comparisonsListSong);
        assert(songLists.getRankedList().get(1) == comparisonsListSong);
    }

    @Test
    public void songCorrectlyRemovedFromComparisonsList() {
        int rankedListSong = songLists.getRankedList().get(0);
        int comparisonsListSong = rankedListSong == 0 ? 1 : 0;
        songLists.addComparison(comparisonsListSong, true, rankedListSong);
        songLists.tryAddSongToRankedList(comparisonsListSong);
        assertNull(songLists.getComparisonsList().get(comparisonsListSong));
    }

    @Test
    public void choosePairReturnsValidSongs() {
        int rankedListSong = songLists.getRankedList().get(0);
        SongPair songPair = songLists.getPairToCompare();
        assert(songPair.rankedListSong() == rankedListSong);
        assert(songLists.getComparisonsList().containsKey(songPair.comparisonsListSong()));
    }

}