package com.example.afinal.api;
import com.example.afinal.data.NewsResponse;
import com.example.afinal.util.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApiService {
    @GET("v2/top-headlines")
    Call<NewsResponse> getTopHeadlines(
            @Query("country") String countryCode,
            @Query("apiKey") String apiKey
    );
}