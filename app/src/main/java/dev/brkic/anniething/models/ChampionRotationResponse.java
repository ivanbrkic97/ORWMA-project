package dev.brkic.anniething.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class ChampionRotationResponse {
    String type;
    String format;
    String version;
    @SerializedName("data")
    Map<String,Champion> champions;

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
