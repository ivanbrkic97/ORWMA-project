package dev.brkic.anniething.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import dev.brkic.anniething.R;

public class InfoFragment  extends Fragment {
    private TextView flexRank;
    private TextView flexType;
    private TextView flexPoints;
    private TextView flexWR;
    private TextView summonerName;
    private TextView level;
    private TextView soloQRank;
    private TextView soloQType;
    private TextView soloQPoints;
    private TextView soloQWR;
    private ImageView profileIcon;
    private ImageView flexIcon;
    private ImageView soloQIcon;

    private static final String BUNDLE_MESSAGE = "message";


    public static InfoFragment newInstance(String message) {
        InfoFragment fragment = new InfoFragment();
        Bundle args = new Bundle();
        args.putString(BUNDLE_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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

        profileIcon = view.findViewById(R.id.profileImageView);
        flexIcon = view.findViewById(R.id.flexImageView);
        soloQIcon = view.findViewById(R.id.soloqIcon);


    }
    public void displayMessage(String message) {
        //mMessageTextView.setText(!message.trim().isEmpty() ? message : "...");
    }
}