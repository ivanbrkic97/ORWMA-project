package dev.brkic.anniething.models;

public class Mastery {
    private int championId;
    private int championLevel;
    private int championPoints;
    private boolean chestGranted;
    private String name;
    private ChampionImage image;

    public Mastery(int championId, int championLevel, int championPoints, boolean chestGranted, String name, ChampionImage image) {
        this.championId = championId;
        this.championLevel = championLevel;
        this.championPoints = championPoints;
        this.chestGranted = chestGranted;
        this.name = name;
        this.image = image;
    }

    public int getChampionId() {
        return championId;
    }

    public int getChampionLevel() {
        return championLevel;
    }

    public int getChampionPoints() {
        return championPoints;
    }

    public boolean isChestGranted() {
        return chestGranted;
    }

    public String getName() {
        return name;
    }

    public ChampionImage getImage() {
        return image;
    }
}
