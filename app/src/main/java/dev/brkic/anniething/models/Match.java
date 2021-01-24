package dev.brkic.anniething.models;

public class Match {
    public String platformId;
    public String gameId;
    public int champion;
    public int queue;
    public int season;

    public String getPlatformId() {
        return platformId;
    }

    public String getGameId() {
        return gameId;
    }

    public int getChampion() {
        return champion;
    }

    public int getQueue() {
        return queue;
    }

    public int getSeason() {
        return season;
    }
}
