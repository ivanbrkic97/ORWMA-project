package dev.brkic.anniething.models;



import com.google.gson.annotations.SerializedName;

public class Champion {

    @SerializedName("key")
    private int id;

    private String name;

    private String title;

    private ChampionImage image;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public ChampionImage getImage() {
        return image;
    }
}
