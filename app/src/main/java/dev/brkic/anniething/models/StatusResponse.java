package dev.brkic.anniething.models;

import java.util.List;

public class StatusResponse {
        public String id;
        public String name;
        public List<Incident> maintenances;
        public List<Incident> incidents;

        public String getId() {
                return id;
        }

        public String getName() {
                return name;
        }

        public List<Incident> getMaintenances() {
                return maintenances;
        }

        public List<Incident> getIncidents() {
                return incidents;
        }
}
