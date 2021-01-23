package dev.brkic.anniething.models;

public class Status {
    String name;
    String info;
    String platforms;

    public Status(String name, String info, String platforms) {
        this.name = name;
        this.info = info;
        this.platforms = platforms;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPlatforms() {
        return platforms;
    }

    public void setPlatforms(String platforms) {
        this.platforms = platforms;
    }
}
