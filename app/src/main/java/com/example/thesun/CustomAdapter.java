package com.example.thesun;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thesun.interfaces.selectPageListner;
import com.example.thesun.models.NewsHeadlines;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomVH> {

    private Context context;
    private List<NewsHeadlines> headlines;
    private selectPageListner listner;

    public CustomAdapter(Context context, List<NewsHeadlines> headlines, selectPageListner listner) {
        this.context = context;
        this.headlines = headlines;
        this.listner = listner;
    }

    @NonNull
    @Override
    public CustomVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CustomVH(LayoutInflater.from(context).inflate(R.layout.news_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomVH holder, @SuppressLint("RecyclerView") int position) {

        holder.newsTitle.setText(headlines.get(position).getTitle());
        holder.newsSource.setText(headlines.get(position).getSource().getName());
        holder.newsTime.setText(headlines.get(position).getPublishedAt());

        if (headlines.get(position).getUrlToImage()!=null){
            Picasso.get().load(headlines.get(position).getUrlToImage()).into(holder.newsImage);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listner.NewsClicked(headlines.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
