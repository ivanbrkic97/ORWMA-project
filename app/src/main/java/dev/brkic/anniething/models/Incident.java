package dev.brkic.anniething.models;

import java.util.Date;
import java.util.List;

public class Incident {
    public Integer id;
    public List<Title> titles;
    public String incident_severity;
    public List<String> platforms;

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
