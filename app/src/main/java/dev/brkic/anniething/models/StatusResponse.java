package dev.brkic.anniething.models;

import java.util.List;

public class StatusResponse {
        private String id;
        private String name;
        private List<Incident> maintenances;
        private List<Incident> incidents;

        public void setId(String id) {
                this.id = id;
        }

        public void setName(String name) {
                this.name = name;
        }

        public void setMaintenances(List<Incident> maintenances) {
                this.maintenances = maintenances;
        }

        public void setIncidents(List<Incident> incidents) {
                this.incidents = incidents;
        }

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
