package com.example.afinal.api;
import com.example.afinal.util.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitInstance {

    private static final Retrofit retrofit;
    public static final NewsApiService api;

    // Static block to initialize the static fields
    static {
        // Create a logging interceptor to see request and response logs
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Create an OkHttpClient and add the logging interceptor
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        // Build the Retrofit instance
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        // Create the API service instance
        api = retrofit.create(NewsApiService.class);
    }

    // Private constructor to prevent instantiation
    private RetrofitInstance() {}
}