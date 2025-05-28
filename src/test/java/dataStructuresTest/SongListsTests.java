package dataStructuresTest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataStructures.SongLists;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SongListsTests {
    private static JsonNode playlist;

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

    @Test
    public void blankComparisonListIsCreated() {
        SongLists songLists = new SongLists(playlist);
        List<List<Integer>> expectedComparisonsList = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            expectedComparisonsList.add(Arrays.asList(0,0,0,0,0,0,0));
        }
        assertEquals(6, songLists.getComparisonsList().size());
        assertEquals(expectedComparisonsList, songLists.getComparisonsList());
    }

    @Test
    public void logAComparison() {
        SongLists songLists = new SongLists(playlist);
        songLists.addComparison(0,true, 1);
        assertEquals(-1, songLists.getComparisonsList().get(0).get(1));
        songLists.addComparison(0,false, 1);
        assertEquals(+1, songLists.getComparisonsList().get(0).get(1));
    }

    //ranked list should contain one number only: 0,1,2,3,4,5, or 6
    @Test
    public void initialRankedListIsCreated() {
        SongLists songLists = new SongLists(playlist);
        assert(songLists.getRankedList().size() == 1);
        assert(songLists.getRankedList().get(0) < 7 && songLists.getRankedList().get(0) >= 0);
    }

}