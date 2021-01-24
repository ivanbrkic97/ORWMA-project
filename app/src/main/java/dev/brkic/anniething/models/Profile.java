package dev.brkic.anniething.models;

public class Profile {
    private String summonerName;
    private Integer summonerLevel;
    private Integer masteryScore;
    private Integer chestDropped;
    private Integer summonerIcon;
    private Rank solo;
    private Rank flex;
    private Border border;

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }

    public Integer getSummonerLevel() {
        return summonerLevel;
    }

    public void setSummonerLevel(Integer summonerLevel) {
        this.summonerLevel = summonerLevel;
    }

    public Integer getMasteryScore() {
        return masteryScore;
    }

    public void setMasteryScore(Integer masteryScore) {
        this.masteryScore = masteryScore;
    }

    public Integer getChestDropped() {
        return chestDropped;
    }

    public void setChestDropped(Integer chestDropped) {
        this.chestDropped = chestDropped;
    }

    public Integer getSummonerIcon() {
        return summonerIcon;
    }

    public void setSummonerIcon(Integer summonerIcon) {
        this.summonerIcon = summonerIcon;
    }

    public Rank getSolo() {
        return solo;
    }

    public void setSolo(Rank solo) {
        this.solo = solo;
    }

    public Rank getFlex() {
        return flex;
    }

    public void setFlex(Rank flex) {
        this.flex = flex;
    }

    public Border getBorder() {
        return border;
    }

    public void setBorder(Border border) {
        this.border = border;
    }
}
