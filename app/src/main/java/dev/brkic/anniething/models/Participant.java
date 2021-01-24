package dev.brkic.anniething.models;

public class Participant {
    public int participantId;
    public int teamId;
    public int championId;
    public int spell1Id;
    public int spell2Id;
    public PlayerStats stats;

    public int getParticipantId() {
        return participantId;
    }

    public int getTeamId() {
        return teamId;
    }

    public int getChampionId() {
        return championId;
    }

    public int getSpell1Id() {
        return spell1Id;
    }

    public int getSpell2Id() {
        return spell2Id;
    }

    public PlayerStats getStats() {
        return stats;
    }
}
