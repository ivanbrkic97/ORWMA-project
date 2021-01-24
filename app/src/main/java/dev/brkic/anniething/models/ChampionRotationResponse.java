package dev.brkic.anniething.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class ChampionRotationResponse {
    private String type;
    private String format;
    private String version;
    @SerializedName("data")
    private Map<String,Champion> champions;

    public String getType() {
        return type;
    }

    public String getFormat() {
        return format;
    }

    public String getVersion() {
        return version;
    }

    public Map<String,Champion> getChampions() {
        return champions;
    }
}
