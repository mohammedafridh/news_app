package com.example.thesun.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.thesun.CustomAdapter;
import com.example.thesun.API_request.ManageRequest;
import com.example.thesun.R;
import com.example.thesun.models.NewsHeadlines;
import com.example.thesun.models.NewsResponseAPI;
import com.example.thesun.interfaces.onFetchDataListener;
import com.example.thesun.interfaces.selectPageListner;

import java.util.List;

public class MainActivity extends AppCompatActivity implements selectPageListner, View.OnClickListener {


    ProgressDialog dialog;
    RecyclerView recycler;
    Button b1,b2,b3,b4,b5;
    SearchView searchView;
    CustomAdapter customAdapter;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchView =findViewById(R.id.search_view);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                dialog.setTitle("Loading");
                dialog.show();
                ManageRequest manageRequest = new ManageRequest(MainActivity.this);
                manageRequest.getNewsHeadlines(listener, "general", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        dialog =new ProgressDialog(this);

        dialog.setTitle("Loading");
        dialog.show();

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);


        b5.setOnClickListener(this);
        b4.setOnClickListener(this);
        b3.setOnClickListener(this);
        b2.setOnClickListener(this);
        b1.setOnClickListener(this);


        ManageRequest manageRequest = new ManageRequest(this);
        manageRequest.getNewsHeadlines(listener, "general", null);
    }

    private final onFetchDataListener<NewsResponseAPI> listener = new onFetchDataListener<NewsResponseAPI>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            if(list.isEmpty()){
                Toast.makeText(MainActivity.this,"Search Results Empty!", Toast.LENGTH_SHORT).show();
            }
            else {
                ShowListNews(list);
                dialog.dismiss();
            }

        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this,"Error",Toast.LENGTH_SHORT).show();
        }
    };

    private void ShowListNews(List<NewsHeadlines> list) {
        recycler = findViewById(R.id.recycler_main);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new GridLayoutManager(this, 1));
        customAdapter = new CustomAdapter(this, list, this);
        recycler.setAdapter(customAdapter);

    }

    @Override
    public void NewsClicked(NewsHeadlines newsHeadlines) {
        startActivity(new Intent(MainActivity.this, Details.class)
                .putExtra("data", newsHeadlines));
    }

    @Override
    public void onClick(View v){
        Button button = (Button) v;
        String category = button.getText().toString();
        dialog.setTitle("Loading...");
        dialog.show();
        ManageRequest manageRequest = new ManageRequest(this);
        manageRequest.getNewsHeadlines(listener, category, null);
    }
}