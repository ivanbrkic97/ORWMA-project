package dev.brkic.anniething.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

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
import dev.brkic.anniething.models.Queue;
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
    private Call<List<Queue>> getQueueApiCall;
    private String token;
    private ProfileInfo profileInfo;
    private Integer masteryScore;
    private List<ChampionMastery> championMasteries;
    private List<Border> borders;
    private List<Rank> ranks;
    private List<Champion> champions;
    private List<Match> matches;
    private List<MatchEntry> matcheEntries;
    private List<Queue> queues;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        token=getString(R.string.token);
        Intent intent = getIntent();
        String summonerName = intent.getStringExtra("SummonerName");
        getProfileInfo(summonerName);
    }
    private void initViews() {
        mViewPager = findViewById(R.id.profile_view_pager);
    }


    private void setProfile(ProfileInfo profile) {
        profileInfo = profile;
        getBorder();
    }

    private void setProfileData() {
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
                if(rank.getQueueType().contentEquals(getText(R.string.solo))){
                    newProfile.setSolo(rank);
                }
                if(rank.getQueueType().contentEquals(getText(R.string.flex))){
                    newProfile.setFlex(rank);
                }
            }
        }
        List<Mastery> masteries = new ArrayList<>();
        Champion champion = new Champion();
        for(ChampionMastery championMastery:championMasteries){
            for(Champion champ:champions){
                if(champ.getId() ==championMastery.getChampionId()){
                    champion=champ;
                }
            }
            Mastery mastery=new Mastery(championMastery.getChampionId(),
                    championMastery.getChampionLevel(),
                    championMastery.getChampionPoints(),
                    championMastery.isChestGranted(),
                    champion.getName(),
                    champion.getImage());
            masteries.add(mastery);
        }
        ProfileScreenSlidePagerAdapter sectionsPagerAdapter = new ProfileScreenSlidePagerAdapter(this, getSupportFragmentManager(),newProfile,masteries,matcheEntries);
        ViewPager viewPager = findViewById(R.id.profile_view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.second_page_tabs);
        tabs.setupWithViewPager(viewPager);
        progressBar.setVisibility(View.INVISIBLE);
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
                    Log.i("Info",response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Border>> call, Throwable t) {
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
                }
                else{
                    Log.i("Info",response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Rank>> call, Throwable t) {
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
                }
                else{
                    Log.i("Info:",response.message());
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
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
                    getMasteryScore(profileInfo.getId());
                }
                else{
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
                    getMatchHistory(profileInfo.getAccountId());
                    getChampionMasteries(profileInfo.getId());
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

    public void goBack(View view){
        finish();
    }


    private void setMatchData(MatchResponse response, Match match, boolean isLast){
        Integer id = 0;
        if(response != null && response.getParticipantIdentities().size() >0) {
            for (ParticipantIdentity identity : response.getParticipantIdentities()) {
                if (identity.getPlayer().getSummonerId() != null && identity.getPlayer().getSummonerId().equals(profileInfo.getId())) {
                    id = identity.getParticipantId();
                }
            }
            Participant participant = new Participant();
            for (Participant part : response.getParticipants()) {
                if (part.getParticipantId() == id) {
                    participant = part;
                }
            }
            MatchEntry entry = new MatchEntry();
            entry.setGameId(match.getGameId());
            for (Champion champion : champions) {
                if (champion.getId() == match.getChampion()) {
                    entry.setChampion(champion);
                }
            }
            entry.setScore(String.valueOf(participant.getStats().getKills()) + "/" + String.valueOf(participant.getStats().getDeaths()) + "/" + String.valueOf(participant.getStats().getAssists()));
            entry.setWin(participant.getStats().isWin());
            entry.setKda((double) (participant.getStats().getKills() + participant.getStats().getAssists()) / participant.getStats().getDeaths());
            entry.setLevel("Level " + participant.getStats().getChampLevel());
            entry.setLargestMultiKill(participant.getStats().getLargestMultiKill());
            entry.setTotalMinionsKilled(participant.getStats().getTotalMinionsKilled());
            for (Queue queue : queues) {
                if (queue.getQueueId() == match.getQueue()) {
                    entry.setQueue(queue.getDescription().replace("games", ""));
                }
            }
            matcheEntries.add(entry);
            if (isLast) {
                setProfileData();
            }
        }
    }

    private void getMatch(List<Match> matches, int i,int max) {
        getMatchResponseAPICall = NetworkUtils.getLOLApiInterface().getMatch(token,matches.get(i-1).getGameId());
        getMatchResponseAPICall.enqueue(new Callback<MatchResponse>() {

            @Override
            public void onResponse(Call<MatchResponse> call, Response<MatchResponse>
                    response) {
                if (response.isSuccessful() && response.body() != null) {
                        setMatchData(response.body(), matches.get(i-1),i==max);
                        if(i<max){
                        getMatch(matches,i+1,max);}
                }
                else{
                    Log.i("Info:",response.toString());
                }
            }

            @Override
            public void onFailure(Call<MatchResponse> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }

    public void getMatchHistoryData(MatchHistory history) {
        if (history != null && history.getMatches() != null && history.getMatches().size()>0) {
            int maxSize = 10;
            if(history.getMatches().size()<maxSize){
                maxSize=history.getMatches().size();
            }
            matches = history.getMatches().subList(0, maxSize);
            matcheEntries = new ArrayList<>();
            getMatch(matches, 1, matches.size());
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
                        setProfile(response.body());getQueues();
                    getChampions();
                }
                else{
                    progressBar.setVisibility(View.INVISIBLE);
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

    private void getQueues() {
        getQueueApiCall = NetworkUtils.getQueueInterface().getQueues();
        getQueueApiCall.enqueue(new Callback<List<Queue>>() {
            @Override
            public void onResponse(Call<List<Queue>> call, Response<List<Queue>>
                    response) {
                if (response.isSuccessful() && response.body() != null) {
                        queues = response.body();
                }
                else{
                    Log.i("Info:",response.toString());
                }
            }

            @Override
            public void onFailure(Call<List<Queue>> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }
}