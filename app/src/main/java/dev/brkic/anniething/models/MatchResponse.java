package dev.brkic.anniething.models;

import java.util.List;

public class MatchResponse {
    private long gameId;
    private String platformId;
    private long gameCreation;
    private int gameDuration;
    private int queueId;
    private int mapId;
    private int seasonId;
    private String gameVersion;
    private String gameMode;
    private String gameType;
    private List<Team> teams;
    private List<Participant> participants;
    private List<ParticipantIdentity> participantIdentities;

    public long getGameId() {
        return gameId;
    }

    public String getPlatformId() {
        return platformId;
    }

    public long getGameCreation() {
        return gameCreation;
    }

    public int getGameDuration() {
        return gameDuration;
    }

    public int getQueueId() {
        return queueId;
    }

    public int getMapId() {
        return mapId;
    }

    public int getSeasonId() {
        return seasonId;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public String getGameMode() {
        return gameMode;
    }

    public String getGameType() {
        return gameType;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public List<ParticipantIdentity> getParticipantIdentities() {
        return participantIdentities;
    }
}
