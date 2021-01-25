package dev.brkic.anniething.viewHolders;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import dev.brkic.anniething.R;
import dev.brkic.anniething.models.Mastery;
import dev.brkic.anniething.models.MatchEntry;

public class HistoryItemViewHolder extends RecyclerView.ViewHolder {
    private TextView queueType;
    private TextView championName;
    private TextView scoreTextView;
    private TextView level;
    private TextView multikill;
    private TextView csTW;
    private TextView kda;
    private ImageView icon;
    private View backgroundWin;

    public HistoryItemViewHolder(@NonNull View itemView)
    {
        super(itemView);
        queueType = itemView.findViewById(R.id.queue_type_match);
        championName = itemView.findViewById(R.id.champion_name_match);
        scoreTextView = itemView.findViewById(R.id.score_match);
        level = itemView.findViewById(R.id.level);
        kda = itemView.findViewById(R.id.kda_tw);
        multikill = itemView.findViewById(R.id.multikill_tw);
        csTW = itemView.findViewById(R.id.cs_tw);
        icon = itemView.findViewById(R.id.champion_icon_match);
        backgroundWin=itemView.findViewById(R.id.background_win);
    }

    public int getImage(String imageName) {

        int drawableResourceId = icon.getContext().getResources().getIdentifier(imageName.replace(".png","").replace(" ","").replace("&willump",""), "drawable", "dev.brkic.anniething");

        return drawableResourceId;
    }

    public void  setArticle(MatchEntry article)
    {
        if(article.getChampion().getName() != null){
            queueType.setText(article.getQueue());
        championName.setText(article.getChampion().getName());
        scoreTextView.setText(article.getScore());
        csTW.setText(String.valueOf(article.getTotalMinionsKilled())+" CS");
        level.setText(article.getLevel());
        kda.setText(String.valueOf(article.getKda()).subSequence(0,2)+":1 KDA");
        if(article.getLargestMultiKill() == 2){
            multikill.setText("Double Kill");
        } else if(article.getLargestMultiKill() == 3) {
            multikill.setText("Triple Kill");
        } else if(article.getLargestMultiKill() == 4) {
            multikill.setText("Quadra Kill");
        } else if(article.getLargestMultiKill() == 5) {
            multikill.setText("Penta Kill");
        }
        else {
            multikill.setText("");
        }
        Picasso.with(icon.getContext()).load(getImage(article.getChampion().getName().replace("'","").replace(" ","").toLowerCase())).into(icon);
        if(article.isWin()){
            backgroundWin.setBackgroundColor(Color.argb(255, 100, 127, 69));
        }
        else{
            backgroundWin.setBackgroundColor(Color.argb(255, 223, 97, 97));
        }
        }}
}
