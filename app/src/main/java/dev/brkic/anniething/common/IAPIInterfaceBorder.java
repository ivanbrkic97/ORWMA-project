package dev.brkic.anniething.common;

import java.util.List;

import dev.brkic.anniething.models.Border;
import dev.brkic.anniething.models.ChampionRotationResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IAPIInterfaceBorder {
    @GET("borders.json")
    Call<List<Border>> getBorders();
}
