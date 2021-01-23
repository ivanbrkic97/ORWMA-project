package dev.brkic.anniething.common;

import java.util.List;

import dev.brkic.anniething.models.Champion;
import dev.brkic.anniething.models.ChampionRotationResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IAPIInterfaceChampions {
    @GET("champion.json")
    Call<ChampionRotationResponse> getChampions();
}
