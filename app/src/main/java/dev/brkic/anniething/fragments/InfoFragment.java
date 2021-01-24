package dev.brkic.anniething.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.net.URI;

import dev.brkic.anniething.R;
import dev.brkic.anniething.models.Profile;
import dev.brkic.anniething.models.ProfileInfo;

public class InfoFragment  extends Fragment {
    private TextView flexRank;
    private TextView flexType;
    private TextView flexPoints;
    private TextView flexWR;
    private TextView summonerName;
    private TextView level;
    private TextView masteryScore;
    private TextView chest;
    private TextView soloQRank;
    private TextView soloQType;
    private TextView soloQPoints;
    private TextView soloQWR;
    private ImageView profileIcon;
    private ImageView profileBorder;
    private ImageView flexIcon;
    private ImageView soloQIcon;
    private Profile profile;

    private static final String BUNDLE_MESSAGE = "summonerName";


    public static InfoFragment newInstance() {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public  void setProfile(Profile profile){
        this.profile=profile;
    }
    @Override    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_info, container, false);
        findViews(root);
        setData();
        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);

    }

    public void findViews(@NonNull View view){
        flexRank = view.findViewById(R.id.rankTW);
        flexType = view.findViewById(R.id.typeTW);
        flexPoints = view.findViewById(R.id.pointsTW);
        flexWR = view.findViewById(R.id.wrTW);
        summonerName = view.findViewById(R.id.summonerNameTW);
        level = view.findViewById(R.id.levelTW);
        soloQRank = view.findViewById(R.id.rank1TW);
        soloQType = view.findViewById(R.id.type1TW);
        soloQPoints = view.findViewById(R.id.points1TW);
        soloQWR = view.findViewById(R.id.wr1TW);
        masteryScore = view.findViewById(R.id.masteryScoreTW);
        chest=view.findViewById(R.id.chestDroppedTW);
        profileIcon = view.findViewById(R.id.profileImageView);
        profileBorder = view.findViewById(R.id.profile_border_image_view);
        flexIcon = view.findViewById(R.id.flexImageView);
        soloQIcon = view.findViewById(R.id.soloqIcon);
    }

    public int getImage(String imageName) {

        int drawableResourceId = profileBorder.getContext().getResources().getIdentifier(imageName.replace(".png",""), "drawable", "dev.brkic.anniething");

        return drawableResourceId;
    }

    public void setData() {
        summonerName.setText(profile.getSummonerName());
        level.setText(String.valueOf(profile.getSummonerLevel()));
        masteryScore.setText("Mastery score:"+String.valueOf(profile.getMasteryScore()));
        chest.setText("Chests Acquired:"+String.valueOf(profile.getChestDropped()));
        Picasso.with(profileBorder.getContext()).load(profile.getBorder().getImage().replace("http","https")).into(profileBorder);
        Picasso.with(profileIcon.getContext()).load(getImage("icon"+String.valueOf(profile.getSummonerIcon()))).into(profileIcon);
        if(profile.getFlex() != null){
            flexType.setText("Flex 5v5");
            Picasso.with(flexIcon.getContext()).load(getImage(profile.getFlex().getTier().toLowerCase()+profile.getFlex().getRank().toLowerCase())).into(flexIcon);
            flexRank.setText(profile.getFlex().getTier()+" "+profile.getFlex().getRank());
            flexPoints.setText(String.valueOf(profile.getFlex().getLeaguePoints())+" LP");
            flexWR.setText(String.valueOf(profile.getFlex().getWins())+"/"+String.valueOf(profile.getFlex().getLosses()));
        }
        if(profile.getSolo() != null){
            soloQType.setText("Solo/Duo");
            Picasso.with(soloQIcon.getContext()).load(getImage(profile.getSolo().getTier().toLowerCase()+profile.getSolo().getRank().toLowerCase())).into(soloQIcon);
            soloQRank.setText(profile.getSolo().getTier()+" "+profile.getSolo().getRank());
            soloQPoints.setText(String.valueOf(profile.getSolo().getLeaguePoints())+" LP");
            soloQWR.setText(String.valueOf(profile.getSolo().getWins())+"/"+String.valueOf(profile.getSolo().getLosses()));
        }
    }
}