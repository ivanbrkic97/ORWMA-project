package dev.brkic.anniething.viewHolders;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import dev.brkic.anniething.R;
import dev.brkic.anniething.models.Champion;
import dev.brkic.anniething.models.Mastery;

public class MasteryItemViewHolder extends RecyclerView.ViewHolder {
    private TextView nameTextView;
    private TextView levelTextView;
    private TextView scoreTextView;
    private ImageView imageView;
    private ImageView chest;

    public MasteryItemViewHolder(@NonNull View itemView)
    {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.champion_name_tw);
        levelTextView = itemView.findViewById(R.id.master_level_tw);
        scoreTextView = itemView.findViewById(R.id.mastery_score_tw);
        chest = itemView.findViewById(R.id.chest_icon);
        imageView = itemView.findViewById(R.id.champion_icon);
    }

    public int getImage(String imageName) {

        int drawableResourceId = imageView.getContext().getResources().getIdentifier(imageName.replace(".png",""), "drawable", "dev.brkic.anniething");

        return drawableResourceId;
    }

    public void  setArticle(Mastery article)
    {
        if(article.getName() != null){
        nameTextView.setText(article.getName());
        levelTextView.setText("Level "+String.valueOf(article.getChampionLevel()));
        scoreTextView.setText(String.valueOf(article.getChampionPoints())+" pts");
        Picasso.with(imageView.getContext()).load(getImage(article.getImage().getName().toLowerCase())).into(imageView);
        if(!article.isChestGranted()){
            Picasso.with(chest.getContext()).load(R.drawable.chest).fit().into(chest);
            chest.setColorFilter(Color.GRAY);
        }
        else{
            Picasso.with(chest.getContext()).load(R.drawable.chest).fit().into(chest);
            chest.clearColorFilter();
        }
        }
    }
}
