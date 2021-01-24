package dev.brkic.anniething.models;

public class Player {
    private String platformId;
    private String accountId;
    private String summonerName;
    private String summonerId;
    private String currentPlatformId;
    private String currentAccountId;
    private String matchHistoryUri;
    private int profileIcon;

    public String getPlatformId() {
        return platformId;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getSummonerName() {
        return summonerName;
    }

    public String getSummonerId() {
        return summonerId;
    }

    public String getCurrentPlatformId() {
        return currentPlatformId;
    }

    public String getCurrentAccountId() {
        return currentAccountId;
    }

    public String getMatchHistoryUri() {
        return matchHistoryUri;
    }

    public int getProfileIcon() {
        return profileIcon;
    }
}
