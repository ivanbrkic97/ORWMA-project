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
import java.util.Arrays;
import java.util.List;

import dev.brkic.anniething.R;
import dev.brkic.anniething.adapters.FirstPagePagerAdapter;
import dev.brkic.anniething.adapters.ProfileScreenSlidePagerAdapter;
import dev.brkic.anniething.common.NetworkUtils;
import dev.brkic.anniething.models.Border;
import dev.brkic.anniething.models.ChampionIds;
import dev.brkic.anniething.models.ChampionMastery;
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
    private static final String token = "RGAPI-9076055f-e32c-4723-ab5b-e9704f2a0773";
    private ProfileInfo profileInfo;
    private Integer masteryScore;
    private List<ChampionMastery> championMasteries;
    private List<Border> borders;
    private List<Rank> ranks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);
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
        ProfileScreenSlidePagerAdapter sectionsPagerAdapter = new ProfileScreenSlidePagerAdapter(this, getSupportFragmentManager(),newProfile);
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
                    Toast.makeText(getBaseContext(), "Not found", Toast.LENGTH_SHORT).show();
                    Log.i("Not found",response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Border>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
                Log.i("Error:",t.getMessage());
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
                    Toast.makeText(getBaseContext(), "Not found", Toast.LENGTH_SHORT).show();
                    Log.i("Not found",response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Rank>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
                Log.i("Error:",t.getMessage());
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
                    Toast.makeText(getBaseContext(), "Not found", Toast.LENGTH_SHORT).show();
                    Log.i("Not found",response.message());
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
                Log.i("Error:",t.getMessage());
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
                    Toast.makeText(getBaseContext(), "Not found", Toast.LENGTH_SHORT).show();
                    Log.i("Not found",response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ChampionMastery>> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Error", Toast.LENGTH_SHORT).show();
                Log.i("Error:",t.getMessage());
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
                        e.printStackTrace();
                    }
                    Toast.makeText(getBaseContext(), response.message(), Toast.LENGTH_SHORT).show();
                    Log.i("jejeje:",response.body().getName());
                }
                else{
                    Toast.makeText(getBaseContext(), response.message(), Toast.LENGTH_SHORT).show();
                    Log.i("jejeje:",response.message());
                }
            }

            @Override
            public void onFailure(Call<ProfileInfo> call, Throwable t) {
                Toast.makeText(getBaseContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.i("jejeje:",t.getMessage());
            }
        });
    }
}