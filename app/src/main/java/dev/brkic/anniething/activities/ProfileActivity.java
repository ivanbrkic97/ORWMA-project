package dev.brkic.anniething.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.brkic.anniething.R;
import dev.brkic.anniething.adapters.FirstPagePagerAdapter;
import dev.brkic.anniething.adapters.ProfileScreenSlidePagerAdapter;
import dev.brkic.anniething.common.NetworkUtils;
import dev.brkic.anniething.models.Border;
import dev.brkic.anniething.models.Champion;
import dev.brkic.anniething.models.ChampionIds;
import dev.brkic.anniething.models.ChampionMastery;
import dev.brkic.anniething.models.ChampionRotationResponse;
import dev.brkic.anniething.models.Mastery;
import dev.brkic.anniething.models.Match;
import dev.brkic.anniething.models.MatchEntry;
import dev.brkic.anniething.models.MatchHistory;
import dev.brkic.anniething.models.MatchResponse;
import dev.brkic.anniething.models.Participant;
import dev.brkic.anniething.models.ParticipantIdentity;
import dev.brkic.anniething.models.Profile;
import dev.brkic.anniething.models.ProfileInfo;
import dev.brkic.anniething.models.Rank;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private View mViewPager;
    private TextView summonerNameTW;
    private Call<ProfileInfo> getProfileInfoAPICall;
    private Call<List<ChampionMastery>> getChampionMasteryAPICall;
    private Call<List<Border>> getBordersAPICall;
    private Call<List<Rank>> getRankAPICall;
    private Call<Integer> getChampionScoreAPICall;
    private Call<MatchHistory> getMatchHistoryAPICall;
    private Call<MatchResponse> getMatchResponseAPICall;
    private Call<ChampionRotationResponse> getChampionsApiCall;
    private String token;
    private ProfileInfo profileInfo;
    private Integer masteryScore;
    private List<ChampionMastery> championMasteries;
    private List<Border> borders;
    private List<Rank> ranks;
    private List<Champion> champions;
    private List<Match> matches;
    private List<MatchEntry> matcheEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);
        token=getString(R.string.token);
        Intent intent = getIntent();
        String summonerName = intent.getStringExtra("SummonerName");
        summonerNameTW = findViewById(R.id.summonerNameProfileTW);
        summonerNameTW.setText(summonerName);
        getProfileInfo(summonerName);


    }
    private void initViews() {
        mViewPager = findViewById(R.id.profile_view_pager);
    }


    private void setProfile(ProfileInfo profile) throws InterruptedException {
        profileInfo = profile;
        getBorder();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setProfileData(){
        Profile newProfile=new Profile();
        newProfile.setSummonerName(profileInfo.getName());
        newProfile.setSummonerLevel(profileInfo.getSummonerLevel());
        newProfile.setSummonerIcon(profileInfo.getProfileIconId());
        newProfile.setMasteryScore(masteryScore);
        Integer chestDropped = 0;
        if(championMasteries != null && !championMasteries.isEmpty()){
        for (ChampionMastery mastery:championMasteries){
            if(mastery.isChestGranted()){
                chestDropped++;
            }
        }}
        newProfile.setChestDropped(chestDropped);
        for(Border border:borders){
            if(profileInfo.getSummonerLevel()>border.getFrom() && profileInfo.getSummonerLevel()<=border.getTill()){
                newProfile.setBorder(border);
            }
        }
        if(ranks != null && !ranks.isEmpty()){
            for(Rank rank : ranks){
                if(rank.getQueueType().contentEquals("RANKED_SOLO_5x5")){
                    newProfile.setSolo(rank);
                }
                if(rank.getQueueType().contentEquals("RANKED_FLEX_SR")){
                    newProfile.setFlex(rank);
                }
            }
        }
        List<Mastery> masteries = new ArrayList<>();
        for(ChampionMastery championMastery:championMasteries){
            Champion champion = champions.stream()
                    .filter((champ) -> champ.getId() == championMastery.getChampionId())
                    .findFirst()
                    .orElse(new Champion());
            Mastery mastery=new Mastery(championMastery.getChampionId(),
                    championMastery.getChampionLevel(),
                    championMastery.getChampionPoints(),
                    championMastery.isChestGranted(),
                    champion.getName(),
                    champion.getImage());
            masteries.add(mastery);
        }

        ProfileScreenSlidePagerAdapter sectionsPagerAdapter = new ProfileScreenSlidePagerAdapter(this, getSupportFragmentManager(),newProfile,masteries);
        ViewPager viewPager = findViewById(R.id.profile_view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.second_page_tabs);
        tabs.setupWithViewPager(viewPager);
    }

    private void getBorder() {
        getBordersAPICall = NetworkUtils.getBorderApiInterface().getBorders();
        getBordersAPICall.enqueue(new Callback<List<Border>>() {
            @Override
            public void onResponse(Call<List<Border>> call, Response<List<Border>>
                    response) {
                if (response.isSuccessful() && response.body() != null) {
                    borders= response.body();
                    getRankedStats(profileInfo.getId());
                }
                else{
                    Toast.makeText(getBaseContext(), "Border not found", Toast.LENGTH_SHORT).show();
                    Log.i("Info",response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Border>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
                Log.e("Error:",t.getMessage());
            }
        });
    }

    private void getRankedStats(String id) {
        getRankAPICall = NetworkUtils.getLOLApiInterface().getRankedStats(token,id);
        getRankAPICall.enqueue(new Callback<List<Rank>>() {
            @Override
            public void onResponse(Call<List<Rank>> call, Response<List<Rank>>
                    response) {
                if (response.isSuccessful() && response.body() != null) {
                    ranks= response.body();
                    getChampionMasteries(profileInfo.getId());
                }
                else{
                    Toast.makeText(getBaseContext(), "Ranked stats not found.", Toast.LENGTH_SHORT).show();
                    Log.i("Info",response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Rank>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
                Log.e("Error:",t.getMessage());
            }
        });
    }

    private void getMasteryScore(String summonerId) {
        getChampionScoreAPICall = NetworkUtils.getLOLApiInterface().getMasteryPoints(token,summonerId);
        getChampionScoreAPICall.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer>
                    response) {
                if (response.isSuccessful() && response.body() != null) {
                    masteryScore= response.body();
                    setProfileData();
                }
                else{
                    Toast.makeText(getBaseContext(), "Mastery score not found.", Toast.LENGTH_SHORT).show();
                    Log.i("Info:",response.message());
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
                Log.e("Error:",t.getMessage());
            }
        });
    }

    private void getChampionMasteries(String summonerId) {
        getChampionMasteryAPICall = NetworkUtils.getLOLApiInterface().getChampionMasteries(token,summonerId);
        getChampionMasteryAPICall.enqueue(new Callback<List<ChampionMastery>>() {
            @Override
            public void onResponse(Call<List<ChampionMastery>> call, Response<List<ChampionMastery>>
                    response) {
                if (response.isSuccessful() && response.body() != null) {
                    championMasteries = response.body();
                    getChampions();
                    getMasteryScore(profileInfo.getId());
                }
                else{
                    Toast.makeText(getBaseContext(), "Champion masteries not found.", Toast.LENGTH_SHORT).show();
                    Log.i("Info:",response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ChampionMastery>> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }

    private void getChampions(){
        getChampionsApiCall = NetworkUtils.getChampionApiInterface().getChampions();
        getChampionsApiCall.enqueue(new Callback<ChampionRotationResponse>() {
            @Override
            public void onResponse(Call<ChampionRotationResponse> call, Response<ChampionRotationResponse>
                    response) {
                if (response.isSuccessful() && response.body() != null) {
                    champions = new ArrayList<>();
                    for (Champion champion:response.body().getChampions().values()) {
                        champions.add(champion);
                    }
                }
                else{
                    Log.i("Info:",response.toString());
                }
            }
            @Override
            public void onFailure(Call<ChampionRotationResponse> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setMatchData(MatchResponse response, Match match){
        MatchEntry entry = new MatchEntry();
        ParticipantIdentity identity = response.getParticipantIdentities().stream()
                .filter((stat) -> stat.getPlayer().getSummonerId() == profileInfo.getId())
                .findFirst()
                .orElse(new ParticipantIdentity());
        Integer id = identity.getParticipantId();
        Participant participant = response.getParticipants().stream()
                .filter((stat) -> stat.getParticipantId() == id)
                .findFirst()
                .orElse(new Participant());
        entry.setGameId(match.getGameId());
        entry.setChampion(champions.stream()
                .filter((champ) -> champ.getId() == championMastery.getChampionId())
                .findFirst()
                .orElse(new Champion()););
        entry.setScore(String.valueOf(participant.getStats().getKills())+"/"+String.valueOf(participant.getStats().getDeaths())+"/"+String.valueOf(participant.getStats().getAssists()));
        entry.setWin(participant.getStats().isWin());
    }

    private void getMatch(Match match) {
        getMatchResponseAPICall = NetworkUtils.getLOLApiInterface().getMatch(token,match.getGameId());
        getMatchResponseAPICall.enqueue(new Callback<MatchResponse>() {
            @Override
            public void onResponse(Call<MatchResponse> call, Response<MatchResponse>
                    response) {
                if (response.isSuccessful() && response.body() != null) {
                    setMatchData(response.body(),match);
                }
                else{
                    Toast.makeText(getBaseContext(), "Profile information not found.", Toast.LENGTH_SHORT).show();
                    Log.i("Info:",response.toString());
                }
            }

            @Override
            public void onFailure(Call<MatchResponse> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }
    public void getMatchHistoryData(MatchHistory history){
        matches=history.getMatches().subList(0,9);
        for(Match match : matches){
            getMatch(match);
        }
    }
    private void getMatchHistory(String accountId) {
        getMatchHistoryAPICall = NetworkUtils.getLOLApiInterface().getMatchHistory(token,accountId);
        getMatchHistoryAPICall.enqueue(new Callback<MatchHistory>() {
            @Override
            public void onResponse(Call<MatchHistory> call, Response<MatchHistory>
                    response) {
                if (response.isSuccessful() && response.body() != null) {
                   getMatchHistoryData(response.body());
                }
                else{
                    Toast.makeText(getBaseContext(), "Profile information not found.", Toast.LENGTH_SHORT).show();
                    Log.i("Info:",response.toString());
                }
            }

            @Override
            public void onFailure(Call<MatchHistory> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }

    private void getProfileInfo(String summonerName) {
        getProfileInfoAPICall = NetworkUtils.getLOLApiInterface().getProfileInfo(token,summonerName);
        getProfileInfoAPICall.enqueue(new Callback<ProfileInfo>() {
            @Override
            public void onResponse(Call<ProfileInfo> call, Response<ProfileInfo>
                    response) {
                if (response.isSuccessful() && response.body() != null) {
                    try {
                        setProfile(response.body());
                    } catch (InterruptedException e) {
                        Log.e("Error",e.getMessage());
                    }
                }
                else{
                    Toast.makeText(getBaseContext(), "Profile information not found.", Toast.LENGTH_SHORT).show();
                    Log.i("Info:",response.toString());
                }
            }

            @Override
            public void onFailure(Call<ProfileInfo> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }
}