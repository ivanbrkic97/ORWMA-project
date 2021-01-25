package dev.brkic.anniething.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import dev.brkic.anniething.R;
import dev.brkic.anniething.models.Champion;
import dev.brkic.anniething.models.Mastery;
import dev.brkic.anniething.viewHolders.ChampionItemViewHolder;
import dev.brkic.anniething.viewHolders.MasteryItemViewHolder;

public class MasteryItemAdapter extends RecyclerView.Adapter<MasteryItemViewHolder> {

        private List<Mastery> dataList = new ArrayList<>();

        public MasteryItemAdapter() {
        }

        @NonNull
        @Override
        public MasteryItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View cellView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mastery, parent, false);
                return new MasteryItemViewHolder(cellView);
        }

        @Override
        public void onBindViewHolder(@NonNull MasteryItemViewHolder holder, int position) {
                holder.setArticle(dataList.get(position));
        }

        @Override
        public int getItemCount() {
                return dataList.size();
        }

        public void addData(List<Mastery> data) {
                this.dataList.clear();
                Collections.sort(data, new Comparator<Mastery>() {
                        public int compare(Mastery o1, Mastery o2) {
                                return (o2.getChampionLevel()-o1.getChampionLevel()) == 0 ? o2.getChampionLevel()-o1.getChampionLevel() : o2.getChampionPoints()-o1.getChampionPoints();
                        }
                });
                this.dataList.addAll(data);
                notifyDataSetChanged();
        }

        public void removeData() {
                this.dataList.clear();
                notifyDataSetChanged();
        }

        public void addNewCell(Mastery mastery, int position) {
                if (dataList.size() >= position) {
                        dataList.add(position, mastery);
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
