package dev.brkic.anniething.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.brkic.anniething.R;
import dev.brkic.anniething.models.Mastery;
import dev.brkic.anniething.models.MatchEntry;
import dev.brkic.anniething.viewHolders.HistoryItemViewHolder;
import dev.brkic.anniething.viewHolders.MasteryItemViewHolder;

public class HistoryItemAdapter extends RecyclerView.Adapter<HistoryItemViewHolder> {

        private List<MatchEntry> dataList = new ArrayList<>();

        public HistoryItemAdapter() {
        }

        @NonNull
        @Override
        public HistoryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View cellView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_match, parent, false);
                return new HistoryItemViewHolder(cellView);
        }

        @Override
        public void onBindViewHolder(@NonNull HistoryItemViewHolder holder, int position) {
                holder.setArticle(dataList.get(position));
        }

        @Override
        public int getItemCount() {
                return dataList.size();
        }

        public void addData(List<MatchEntry> data) {
                if(data != null){
                this.dataList.clear();
                this.dataList.addAll(data);
                notifyDataSetChanged();}
        }

        public void removeData() {
                this.dataList.clear();
                notifyDataSetChanged();
        }

        public void addNewCell(MatchEntry entry, int position) {
                if (dataList.size() >= position) {
                        dataList.add(position, entry);
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
