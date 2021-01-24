package dev.brkic.anniething.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import dev.brkic.anniething.R;
import dev.brkic.anniething.adapters.ChampionItemAdapter;
import dev.brkic.anniething.adapters.MasteryItemAdapter;
import dev.brkic.anniething.common.NetworkUtils;
import dev.brkic.anniething.models.Champion;
import dev.brkic.anniething.models.ChampionIds;
import dev.brkic.anniething.models.ChampionRotationResponse;
import dev.brkic.anniething.models.Mastery;
import dev.brkic.anniething.models.Profile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MasteryFragment  extends Fragment {
    private RecyclerView recyclerView;
    private MasteryItemAdapter adapter;
    private static final String title = "Mastery";
    private String token;
    List<Mastery> masteries;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_mastery, container, false);
        token=getString(R.string.token);
        recyclerView = root.findViewById(R.id.mastery_recycler_view);
        setupRecycler();
        setupRecyclerData(masteries);
        return root;
    }

    public  void setMasteries(List<Mastery> masteries){
        this.masteries=masteries;
    }


    public static MasteryFragment newInstance() {
        MasteryFragment fragment = new MasteryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.mastery_recycler_view);
        setupRecycler();
        setupRecyclerData(masteries);
    }

    private  void setupRecycler()
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new MasteryItemAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    public void setupRecyclerData(List<Mastery> champions)
    {
        adapter.addData(champions);
    }

    private void removeArticles() {
        adapter.removeData();
    }

}