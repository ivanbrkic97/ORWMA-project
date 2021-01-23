package dev.brkic.anniething.common;

import java.util.List;

import dev.brkic.anniething.models.Champion;
import dev.brkic.anniething.models.ChampionIds;
import dev.brkic.anniething.models.StatusResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface IAPIInterfaceLOL {
    @GET("platform/v3/champion-rotations")
    Call<ChampionIds> getChampionIds(@Header("X-Riot-Token") String token);

    @GET("status/v4/platform-data")
    Call<StatusResponse> getStatuses(@Header("X-Riot-Token") String token);
}
