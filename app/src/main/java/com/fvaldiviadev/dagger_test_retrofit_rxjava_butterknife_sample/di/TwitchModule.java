package com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.di;

import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http.TwitchAPI;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class TwitchModule {

    public final String BASE_URL="https://api.twitch.tv/helix/";
    public final String CLIENT_ID="2uqcxik63ezyr12qn7qcfw8dm08src";

    @Provides
    public OkHttpClient provideHttpClient(){
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
                .addInterceptor(chain -> {
                    Request request = chain.request().newBuilder().addHeader("Client-Id",CLIENT_ID).build();
                    return chain.proceed(request);
                })
                .build();
    }

    @Provides
    public Retrofit provideRetrofit(String baseURL, OkHttpClient client){
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    public TwitchAPI provideTwitchAPI(){
        return provideRetrofit(BASE_URL,provideHttpClient()).create(TwitchAPI.class);
    }
}
