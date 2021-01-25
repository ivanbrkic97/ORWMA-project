package dev.brkic.anniething.models;

public class MatchEntry {
    private String platformId;
    private String gameId;
    private String queue;
    private int season;
    private Champion champion;
    private String score;
    private boolean win;
    private int largestMultiKill;
    private double kda;
    private String level;
    private int totalMinionsKilled;

    public int getTotalMinionsKilled() {
        return totalMinionsKilled;
    }

    public void setTotalMinionsKilled(int totalMinionsKilled) {
        this.totalMinionsKilled = totalMinionsKilled;
    }

    public void setLargestMultiKill(int largestMultiKill) {
        this.largestMultiKill = largestMultiKill;
    }

    public void setKda(double kda) {
        this.kda = kda;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getLargestMultiKill() {
        return largestMultiKill;
    }

    public double getKda() {
        return kda;
    }

    public String getLevel() {
        return level;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public int getSeason() {
        return season;
    }

    public void setSeason(int season) {
        this.season = season;
    }

    public Champion getChampion() {
        return champion;
    }

    public void setChampion(Champion champion) {
        this.champion = champion;
    }
}
