package com.example.thesun.API_request;

import android.content.Context;
import android.widget.Toast;

import com.example.thesun.R;
import com.example.thesun.interfaces.onFetchDataListener;
import com.example.thesun.models.NewsResponseAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class ManageRequest {
    Context context;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://newsapi.org/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //method to manage api calling
    public void getNewsHeadlines(onFetchDataListener listener, String category, String query)
    {
        newsApiCall NewsApiCall = retrofit.create(newsApiCall.class);
        Call<NewsResponseAPI> call = NewsApiCall.callHeadlines("us", category, query, context.getString(R.string.api_key));

        try{
            call.enqueue(new Callback<NewsResponseAPI>() {
                @Override
                public void onResponse(Call<NewsResponseAPI> call, Response<NewsResponseAPI> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(context, "Error Occured!", Toast.LENGTH_SHORT).show();
                    }
                    listener.onFetchData(response.body().getArticles(), response.message());
                }

                @Override
                public void onFailure(Call<NewsResponseAPI> call, Throwable t) {
                    listener.onError("Failed. Try Again!");
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public ManageRequest(Context context) {
        this.context = context;
    }

    //calling api
    public interface newsApiCall {
        @GET("top-headlines")
        Call<NewsResponseAPI>  callHeadlines(
                @Query("country") String country,
                @Query("category") String category,
                @Query("q") String query,
                @Query("apiKey") String apiKey
        );
    }
}
