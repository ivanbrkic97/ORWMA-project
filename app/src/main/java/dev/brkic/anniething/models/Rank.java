package dev.brkic.anniething.models;

public class Rank {
    private String leagueId;
    private String queueType;
    private String tier;
    private String rank;
    private String summonerId;
    private String summonerName;
    private int leaguePoints;
    private int wins;
    private int losses;
    private boolean veteran;
    private boolean inactive;
    private boolean freshBlood;
    private boolean hotStreak;

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public void setQueueType(String queueType) {
        this.queueType = queueType;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public void setSummonerId(String summonerId) {
        this.summonerId = summonerId;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public void setLeaguePoints(int leaguePoints) {
        this.leaguePoints = leaguePoints;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public void setVeteran(boolean veteran) {
        this.veteran = veteran;
    }

    public void setInactive(boolean inactive) {
        this.inactive = inactive;
    }

    public void setFreshBlood(boolean freshBlood) {
        this.freshBlood = freshBlood;
    }

    public void setHotStreak(boolean hotStreak) {
        this.hotStreak = hotStreak;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public String getQueueType() {
        return queueType;
    }

    public String getTier() {
        return tier;
    }

    public String getRank() {
        return rank;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public int getLeaguePoints() {
        return leaguePoints;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public boolean isVeteran() {
        return veteran;
    }

    public boolean isInactive() {
        return inactive;
    }

    public boolean isFreshBlood() {
        return freshBlood;
    }

    public boolean isHotStreak() {
        return hotStreak;
    }
}
