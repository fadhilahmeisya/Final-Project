package com.example.afinal.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.afinal.data.Article;
import com.example.afinal.data.NewsRepository;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {

    private final NewsRepository repository;
    private final LiveData<List<Article>> allArticles;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        repository = new NewsRepository(application);
        allArticles = repository.getArticles();
        repository.refreshNews();
    }

    public LiveData<List<Article>> getAllArticles() {
        return allArticles;
    }

    public void refreshNews() {
        repository.refreshNews();
    }
}