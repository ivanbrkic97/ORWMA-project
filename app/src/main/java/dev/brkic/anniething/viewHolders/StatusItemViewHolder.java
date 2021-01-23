package dev.brkic.anniething.viewHolders;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import dev.brkic.anniething.R;
import dev.brkic.anniething.models.Status;

public class StatusItemViewHolder extends RecyclerView.ViewHolder {
    private TextView nameTextView;
    private TextView infoTextView;
    private TextView platformTextView;


    public StatusItemViewHolder(@NonNull View itemView)
    {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.statusName);
        infoTextView = itemView.findViewById(R.id.statusInfo);
        platformTextView = itemView.findViewById(R.id.statusPlatform);
    }

    public void  setArticle(Status status)
    {
        nameTextView.setText("Server:"+status.getName());
        infoTextView.setText(status.getInfo());
        platformTextView.setText("Platforms:"+status.getPlatforms());
    }
}