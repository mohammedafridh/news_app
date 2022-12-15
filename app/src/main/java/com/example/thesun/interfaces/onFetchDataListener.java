package com.example.thesun.interfaces;

import com.example.thesun.models.NewsHeadlines;

import java.util.List;

public interface onFetchDataListener<NewsResponseAPI> {
    void onFetchData(List<NewsHeadlines> list, String message);
    void onError(String message);
}
