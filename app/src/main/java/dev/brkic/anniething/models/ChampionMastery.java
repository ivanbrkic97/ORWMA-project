package dev.brkic.anniething.models;

public class ChampionMastery {
    private int championId;
    private int championLevel;
    private int championPoints;
    private int championPointsSinceLastLevel;
    private int championPointsUntilNextLevel;
    private boolean chestGranted;
    private int tokensEarned;
    private String summonerId;

    public int getChampionId() {
        return championId;
    }

    public int getChampionLevel() {
        return championLevel;
    }

    public int getChampionPoints() {
        return championPoints;
    }

    public int getChampionPointsSinceLastLevel() {
        return championPointsSinceLastLevel;
    }

    public int getChampionPointsUntilNextLevel() {
        return championPointsUntilNextLevel;
    }

    public boolean isChestGranted() {
        return chestGranted;
    }

    public int getTokensEarned() {
        return tokensEarned;
    }

    public String getSummonerId() {
        return summonerId;
    }
}
