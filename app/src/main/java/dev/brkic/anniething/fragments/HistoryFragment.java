package dev.brkic.anniething.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.brkic.anniething.R;
import dev.brkic.anniething.adapters.HistoryItemAdapter;
import dev.brkic.anniething.adapters.MasteryItemAdapter;
import dev.brkic.anniething.models.Mastery;
import dev.brkic.anniething.models.MatchEntry;

public class HistoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private HistoryItemAdapter adapter;
    private static final String title = "Matches";
    private String token;
    List<MatchEntry> entries;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_matches, container, false);
        token=getString(R.string.token);
        recyclerView = root.findViewById(R.id.match_recycler);
        setupRecycler();
        setupRecyclerData(entries);
        return root;
    }

    public  void setMasteries(List<MatchEntry> entries){
        this.entries=entries;
    }


    public static HistoryFragment newInstance() {
        HistoryFragment fragment = new HistoryFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.match_recycler);
        setupRecycler();
        setupRecyclerData(entries);
    }

    private  void setupRecycler()
    {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HistoryItemAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
    }

    public void setupRecyclerData(List<MatchEntry> entries)
    {
        adapter.addData(entries);
    }

    private void removeArticles() {
        adapter.removeData();
    }

}