package dev.brkic.anniething.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.brkic.anniething.R;
import dev.brkic.anniething.models.Champion;
import dev.brkic.anniething.models.Status;
import dev.brkic.anniething.viewHolders.ChampionItemViewHolder;
import dev.brkic.anniething.viewHolders.StatusItemViewHolder;

public class StatusItemAdapter extends RecyclerView.Adapter<StatusItemViewHolder> {

    private List<Status> dataList = new ArrayList<>();

    public StatusItemAdapter() {
    }

    @NonNull
    @Override
    public StatusItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View cellView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_status, parent, false);
        return new StatusItemViewHolder(cellView);
    }

    @Override
    public void onBindViewHolder(@NonNull StatusItemViewHolder holder, int position) {
        holder.setArticle(dataList.get(position));
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public void addData(List<Status> data) {
        this.dataList.clear();
        this.dataList.addAll(data);
        notifyDataSetChanged();
    }

    public void removeData() {
        this.dataList.clear();
        notifyDataSetChanged();
    }

    public void addNewCell(Status status, int position) {
        if (dataList.size() >= position) {
            dataList.add(position, status);
            notifyItemInserted(position);
        }
    }

    public void removeCell(int position) {
        if (dataList.size() > position) {
            dataList.remove(position);
            notifyItemRemoved(position);
        }
    }

}