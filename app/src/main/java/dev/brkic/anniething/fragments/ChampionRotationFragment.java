package dev.brkic.anniething.fragments;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dev.brkic.anniething.R;
import dev.brkic.anniething.adapters.ChampionItemAdapter;
import dev.brkic.anniething.common.NetworkUtils;
import dev.brkic.anniething.models.Champion;
import dev.brkic.anniething.models.ChampionIds;
import dev.brkic.anniething.models.ChampionRotationResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChampionRotationFragment extends Fragment {
    private RecyclerView recyclerView;
    private ChampionItemAdapter adapter;
    private Call<ChampionRotationResponse> getChampionsApiCall;
    private Call<ChampionIds> getChampionIdsApiCall;
    private static final String title = "Champion rotation";
    private String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.free_rotation, container, false);
        token=getString(R.string.token);
        recyclerView = root.findViewById(R.id.rotation_recycler_view);
        setupRecycler();
        getChampionIds();
        return root;
    }

    public static ChampionRotationFragment newInstance(String message) {
        ChampionRotationFragment fragment = new ChampionRotationFragment();
        Bundle args = new Bundle();
        args.putString(title, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rotation_recycler_view);
        setupRecycler();
        getChampionIds();
    }

    private  void setupRecycler()
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ChampionItemAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    public void setupRecyclerData(List<Champion> champions)
    {
        adapter.addData(champions);
    }

    private void showArticles(ChampionRotationResponse championRotationResponse, ChampionIds championIds) {
        //String championsToDeserialize = championRotationResponse.getChampions();
        //Map<String,Champion> targetObject = new Gson().fromJson(championsToDeserialize, Map<String,Champion>);
        List<Champion> freeChampions = new ArrayList<>();
        List<Champion> champions = new ArrayList<>(championRotationResponse.getChampions().values());
        for (Champion champion:champions) {
            if(championIds.getFreeChampionIds().contains(champion.getId())){
                freeChampions.add(champion);
            }
        }
        setupRecyclerData(freeChampions);
    }
    private void getChampions(ChampionIds championIds){
        getChampionsApiCall = NetworkUtils.getChampionApiInterface().getChampions();
        getChampionsApiCall.enqueue(new Callback<ChampionRotationResponse>() {
            @Override
            public void onResponse(Call<ChampionRotationResponse> call, Response<ChampionRotationResponse>
                    response) {
                if (response.isSuccessful() && response.body() != null) {
                    showArticles(response.body(),championIds);
                }
                else{
                    removeArticles();
                    Toast.makeText(getActivity(), "Champions not found", Toast.LENGTH_SHORT).show();
                    Log.i("Info:",response.toString());
                }
            }
            @Override
            public void onFailure(Call<ChampionRotationResponse> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }

    private void removeArticles() {
        adapter.removeData();
    }
    private void getChampionIds() {
        getChampionIdsApiCall = NetworkUtils.getLOLApiInterface().getChampionIds(token);
        getChampionIdsApiCall.enqueue(new Callback<ChampionIds>() {
            @Override
            public void onResponse(Call<ChampionIds> call, Response<ChampionIds>
                    response) {
                if (response.isSuccessful() && response.body() != null) {
                    getChampions(response.body());
                }
                else{
                    removeArticles();
                    Log.i("Info:","Free champion ids empty.");
                    Toast.makeText(getActivity(), "Champions not found.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ChampionIds> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }
}