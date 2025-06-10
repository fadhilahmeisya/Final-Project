package com.example.afinal.data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.afinal.api.NewsApiService;
import com.example.afinal.api.RetrofitInstance;
import com.example.afinal.db.ArticleDao;
import com.example.afinal.db.ArticleDatabase;
import com.example.afinal.util.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    private final ArticleDao articleDao;
    private final NewsApiService newsApiService;

    public NewsRepository(Application application) {
        ArticleDatabase db = ArticleDatabase.getDatabase(application);
        this.articleDao = db.getArticleDao();
        this.newsApiService = RetrofitInstance.api;
    }

    public LiveData<List<Article>> getArticles() {
        return articleDao.getAllArticles();
    }

    public void refreshNews() {
        newsApiService.getTopHeadlines("id", Constants.API_KEY).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ArticleDatabase.databaseWriteExecutor.execute(() -> {
                        articleDao.deleteAllArticles();
                        articleDao.upsert(response.body().getArticles());
                    });
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                Log.e("NewsRepository", "Failed to fetch news", t);
            }
        });
    }
}