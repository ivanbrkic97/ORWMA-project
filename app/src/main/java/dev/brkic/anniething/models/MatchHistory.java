package dev.brkic.anniething.models;

import java.util.List;

public class MatchHistory {
    private List<Match> matches;
    private int startIndex;
    private int endIndex;
    private int totalGames;

    public List<Match> getMatches() {
        return matches;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public int getTotalGames() {
        return totalGames;
    }
}
