package dev.brkic.anniething.models;

import com.google.gson.annotations.SerializedName;

public class ChampionImage {

    @SerializedName("full")
    private String name;

    @SerializedName("w")
    private int width;

    @SerializedName("h")
    private int height;

    public String getName() {
        return name;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
