package dev.brkic.anniething.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import dev.brkic.anniething.R;

public class MatchesFragment  extends Fragment {
    private TextView mMessageTextView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_matches, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMessageTextView = view.findViewById(R.id.championNameTW);
    }
    public void displayMessage(String message) {
        mMessageTextView.setText(!message.trim().isEmpty() ? message : "...");
    }
}