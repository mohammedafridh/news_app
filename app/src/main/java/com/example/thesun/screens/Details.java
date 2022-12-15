package com.example.thesun.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.thesun.R;
import com.example.thesun.models.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {

    NewsHeadlines headingNews;
    TextView newsHeading, newsAuthor, newsTime,newsDesc, newsContent;
    ImageView newsImage;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        newsHeading = findViewById(R.id.title);
        newsAuthor = findViewById(R.id.author);
        newsTime = findViewById(R.id.time);
        newsDesc = findViewById(R.id.description);
        newsContent = findViewById(R.id.content);
        newsImage = findViewById(R.id.image);

        headingNews = (NewsHeadlines) getIntent().getSerializableExtra("data");

        newsHeading.setText(headingNews.getTitle());
        newsAuthor.setText(headingNews.getAuthor());
        newsTime.setText(headingNews.getPublishedAt());
        newsDesc.setText(headingNews.getDescription());
        newsContent.setText(headingNews.getContent());
        Picasso.get().load(headingNews.getUrlToImage()).into(newsImage);
    }
}