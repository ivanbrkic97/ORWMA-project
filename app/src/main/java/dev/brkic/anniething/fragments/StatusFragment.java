package dev.brkic.anniething.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.brkic.anniething.R;
import dev.brkic.anniething.adapters.ChampionItemAdapter;
import dev.brkic.anniething.adapters.StatusItemAdapter;
import dev.brkic.anniething.common.NetworkUtils;
import dev.brkic.anniething.models.Champion;
import dev.brkic.anniething.models.ChampionIds;
import dev.brkic.anniething.models.ChampionRotationResponse;
import dev.brkic.anniething.models.Incident;
import dev.brkic.anniething.models.Status;
import dev.brkic.anniething.models.StatusResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusFragment extends Fragment {
    private RecyclerView recyclerView;
    private StatusItemAdapter adapter;
    private Call<StatusResponse> getStatusesApiCall;
    private final static String title = "Server Status";
    private String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.server_status, container, false);
        token = getString(R.string.token);
        recyclerView = root.findViewById(R.id.status_recycler_view);
        setupRecycler();
        getStatuses();
        return root;
    }

    public static StatusFragment newInstance(String message) {
        StatusFragment fragment = new StatusFragment();
        Bundle args = new Bundle();
        args.putString(title, message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.status_recycler_view);
        setupRecycler();
        getStatuses();
    }

    private  void setupRecycler()
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new StatusItemAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    public void setupRecyclerData(List<Status> statuses)
    {
        adapter.addData(statuses);
    }

    private void showArticles(StatusResponse response) {
        List<Status> statuses = new ArrayList<>();
        if(response.getIncidents() != null && !response.getIncidents().isEmpty()){
        for(Incident incident:response.getIncidents()){
            Status status = new Status(response.getName(),incident.getTitles().get(0).getContent(),incident.getPlatforms().toString());
            statuses.add(status);
        }}
        if(!response.getMaintenances().isEmpty()){
            for(Incident incident:response.getMaintenances()){
                Status status = new Status(response.getName(),incident.getTitles().get(0).getContent(),incident.getPlatforms().toString());
                statuses.add(status);}
            }
        setupRecyclerData(statuses);
    }

    private void getStatuses(){
        getStatusesApiCall = NetworkUtils.getLOLApiInterface().getStatuses(token);
        getStatusesApiCall.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse>
                    response) {
                if (response.isSuccessful() && response.body() != null) {
                    showArticles(response.body());
                }
                else{
                    removeArticles();
                    Toast.makeText(getActivity(), "No recent issues or events to report", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
    }
    private void removeArticles() {
        adapter.removeData();
    }

}