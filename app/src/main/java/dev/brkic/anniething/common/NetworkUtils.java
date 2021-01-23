package dev.brkic.anniething.common;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    private static final String BASE_APILOL =
            "https://eun1.api.riotgames.com/lol/";
    private static final String BASE_APICHAMPIONS =
            "https://ddragon.leagueoflegends.com/cdn/9.3.1/data/en_US/";
    private static IAPIInterfaceChampions apiInterfaceCHAMPIONS;
    private static IAPIInterfaceLOL apiInterfaceLOL;
    public static IAPIInterfaceLOL getLOLApiInterface() {
        if (apiInterfaceLOL == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_APILOL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiInterfaceLOL = retrofit.create(IAPIInterfaceLOL.class);
        }
        return apiInterfaceLOL;
    }

    public static IAPIInterfaceChampions getChampionApiInterface() {
        if (apiInterfaceCHAMPIONS == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_APICHAMPIONS)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiInterfaceCHAMPIONS = retrofit.create(IAPIInterfaceChampions.class);
        }
        return apiInterfaceCHAMPIONS;
    }
}
