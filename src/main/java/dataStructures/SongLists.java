package dataStructures;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.*;

// the comparisons list will be practically the same as the initial list from the original songrank.
// nxn array, list[i][j] == 0 if songs i,j have not been ranked
// list[i][j] == -1 if song i is better than song j (i.e rank of i < rank of j)
// list[i][j] == 1 if song i is worse than song j (i.e rank of i > rank of j)

//the ranked list is a list of ranked song ids, same as the master list from the original songrank
public class SongLists {
    private Map<Integer, List<Integer>> comparisonsList;
    private List<Integer> rankedList;

    public SongLists(JsonNode playlist) {
        this.comparisonsList = new HashMap<>();
        int total = playlist.get("total").asInt();
        for (int i = 0; i < total; i++) {
            this.comparisonsList.put(i, new ArrayList<>(Collections.nCopies(total, 0)));
        }
        //add a random song to the ranked list to begin with
        rankedList = new ArrayList<>();
        int rankedSong = new Random().nextInt(total);
        comparisonsList.remove(rankedSong);
        rankedList.add(rankedSong);
    }

    public void addComparison(int comparisonsListSong, boolean betterThan, int rankedListSong) {
        this.comparisonsList.get(comparisonsListSong).set(rankedListSong, betterThan ? -1 : +1);
    }

    public List<Integer> getRankedList() {
        return rankedList;
    }

    public Map<Integer, List<Integer>> getComparisonsList() {
        return comparisonsList;
    }

}
