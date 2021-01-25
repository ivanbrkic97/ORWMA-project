package dev.brkic.anniething.viewHolders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import dev.brkic.anniething.R;
import dev.brkic.anniething.models.Champion;

public class ChampionItemViewHolder extends RecyclerView.ViewHolder {
    private TextView nameTextView;
    private TextView titleTextView;
    private ImageView imageView;

    public ChampionItemViewHolder(@NonNull View itemView)
    {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.championNameTW);
        titleTextView = itemView.findViewById(R.id.championTitleTW);
        imageView = itemView.findViewById(R.id.championIcon);
    }

    public int getImage(String imageName) {

        int drawableResourceId = imageView.getContext().getResources().getIdentifier(imageName.replace(".png",""), "drawable", imageView.getContext().getText(R.string.package_string).toString());

        return drawableResourceId;
    }

    public void  setArticle(Champion article)
    {
        nameTextView.setText(article.getName());
        titleTextView.setText(article.getTitle());
        Picasso.with(imageView.getContext()).load(getImage(article.getImage().getName().toLowerCase())).into(imageView);
    }
}
