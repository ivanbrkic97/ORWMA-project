package dev.brkic.anniething.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ChampionIds {

    private List<Integer> freeChampionIds;

    private List<Integer> freeChampionIdsForNewPlayers;

    private Integer maxNewPlayerLevel;

    public List<Integer> getFreeChampionIds() {
        return freeChampionIds;
    }
}
