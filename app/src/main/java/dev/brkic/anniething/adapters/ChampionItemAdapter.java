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
import dev.brkic.anniething.viewHolders.ChampionItemViewHolder;

public class ChampionItemAdapter extends RecyclerView.Adapter<ChampionItemViewHolder> {

        private List<Champion> dataList = new ArrayList<>();

        public ChampionItemAdapter() {
        }

        @NonNull
        @Override
        public ChampionItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View cellView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_champion, parent, false);
                return new ChampionItemViewHolder(cellView);
        }

        @Override
        public void onBindViewHolder(@NonNull ChampionItemViewHolder holder, int position) {
                holder.setArticle(dataList.get(position));
        }

        @Override
        public int getItemCount() {
                return dataList.size();
        }

        public void addData(List<Champion> data) {
                this.dataList.clear();
                this.dataList.addAll(data);
                notifyDataSetChanged();
        }

        public void removeData() {
                this.dataList.clear();
                notifyDataSetChanged();
        }

        public void addNewCell(Champion champion, int position) {
                if (dataList.size() >= position) {
                        dataList.add(position, champion);
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
