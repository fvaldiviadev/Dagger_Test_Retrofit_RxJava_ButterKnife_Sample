package com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http;


import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http.api.TwitchGames;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http.api.TwitchStreams;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface TwitchAPI {

    @GET("games/top")
    Call<TwitchGames> getTopGames(@Header("Client-Id") String clientId);

    @GET("games/top")
    Observable<TwitchGames> getTopGamesObservable();

    @GET("games")
    Observable<TwitchGames> getGame(@Query("id") String id);

    @GET("streams")
    Observable<TwitchStreams> getStreamsObservable();

}
