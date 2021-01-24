package dev.brkic.anniething.common;

import java.util.List;

import dev.brkic.anniething.models.Champion;
import dev.brkic.anniething.models.ChampionIds;
import dev.brkic.anniething.models.ChampionMastery;
import dev.brkic.anniething.models.ProfileInfo;
import dev.brkic.anniething.models.Rank;
import dev.brkic.anniething.models.StatusResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface IAPIInterfaceLOL {
    @GET("platform/v3/champion-rotations")
    Call<ChampionIds> getChampionIds(@Header("X-Riot-Token") String token);

    @GET("status/v4/platform-data")
    Call<StatusResponse> getStatuses(@Header("X-Riot-Token") String token);

    @GET("summoner/v4/summoners/by-name/{summonerName}")
    Call<ProfileInfo> getProfileInfo(@Header("X-Riot-Token") String token, @Path("summonerName") String summonerName);

    @GET("champion-mastery/v4/champion-masteries/by-summoner/{encryptedSummonerId}")
    Call<List<ChampionMastery>> getChampionMasteries(@Header("X-Riot-Token") String token, @Path("encryptedSummonerId") String encryptedSummonerId);

    @GET("champion-mastery/v4/scores/by-summoner/{encryptedSummonerId}")
    Call<Integer> getMasteryPoints(@Header("X-Riot-Token") String token, @Path("encryptedSummonerId") String encryptedSummonerId);

    @GET("league/v4/entries/by-summoner/{encryptedSummonerId}")
    Call<List<Rank>> getRankedStats(@Header("X-Riot-Token") String token, @Path("encryptedSummonerId") String encryptedSummonerId);
}
