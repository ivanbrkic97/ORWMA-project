package dev.brkic.anniething.models;

import java.util.List;

public class StatusResponse {
        private String id;
        private String name;
        private List<Incident> maintenances;
        private List<Incident> incidents;

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
