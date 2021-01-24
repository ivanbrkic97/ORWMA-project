package dev.brkic.anniething.models;

import java.util.Date;
import java.util.List;

public class Incident {
    private Integer id;
    private List<Title> titles;
    private String incident_severity;
    private List<String> platforms;

    public Integer getId() {
        return id;
    }

    public List<Title> getTitles() {
        return titles;
    }

    public String getIncident_severity() {
        return incident_severity;
    }

    public List<String> getPlatforms() {
        return platforms;
    }
}
