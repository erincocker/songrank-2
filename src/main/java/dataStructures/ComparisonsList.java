package dataStructures;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// this will be practically the same as the initial list from the original songrank.
// nxn array, list[i][j] == 0 if songs i,j have not been ranked
// list[i][j] == -1 if song i is better than song j (i.e rank of i < rank of j)
// list[i][j] == 1 if song i is worse than song j (i.e rank of i > rank of j)
public class ComparisonsList {
    private List<List<Integer>> comparisons;

    public ComparisonsList(JsonNode playlist) {
        this.comparisons = new ArrayList<>();
        int total = playlist.get("total").asInt();
        for (int i = 0; i < total; i++) {
            this.comparisons.add(new ArrayList<>(Collections.nCopies(total, 0)));
        }
    }

    public List<List<Integer>> getComparisons() {
        return comparisons;
    }

    public void addComparison(int comparisonsListSong, boolean betterThan, int rankedListSong) {
        this.comparisons.get(comparisonsListSong).set(rankedListSong, betterThan ? -1 : +1);
    }

}
