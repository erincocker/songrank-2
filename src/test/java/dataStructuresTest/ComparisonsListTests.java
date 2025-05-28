package dataStructuresTest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataStructures.ComparisonsList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComparisonsListTests {
    private static JsonNode playlist;

    @BeforeAll
    public static void beforeAll() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            playlist = objectMapper.readTree(new File("src/main/resources/testPlaylist.json"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void blankComparisonListIsCreated() {
        ComparisonsList comparisonsList = new ComparisonsList(playlist);
        List<List<Integer>> expectedList = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            expectedList.add(Arrays.asList(0,0,0,0,0,0,0));
        }
        assertEquals(7, comparisonsList.getComparisons().size());
        assertEquals(expectedList, comparisonsList.getComparisons());
    }

    @Test
    public void logAComparison() {
        ComparisonsList comparisonsList = new ComparisonsList(playlist);
        comparisonsList.addComparison(0,true, 1);
        assertEquals(-1, comparisonsList.getComparisons().get(0).get(1));
        comparisonsList.addComparison(0,false, 1);
        assertEquals(+1, comparisonsList.getComparisons().get(0).get(1));
    }
}