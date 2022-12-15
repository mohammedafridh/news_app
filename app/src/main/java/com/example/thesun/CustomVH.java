package com.example.thesun;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CustomVH extends RecyclerView.ViewHolder{

    TextView newsTitle, newsSource, newsTime;
    ImageView newsImage;
    CardView cardView;

    public CustomVH(@NonNull View itemView) {
        super(itemView);

        newsTitle = itemView.findViewById(R.id.text);
        newsSource = itemView.findViewById(R.id.source);
        newsImage = itemView.findViewById(R.id.image);
        newsTime = itemView.findViewById(R.id.time);
        cardView = itemView.findViewById(R.id.main_card);
    }
}
