package com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http;


import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http.api.TwitchGames;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http.api.TwitchStreams;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface TwitchAPI {

    String CLIENT_ID="2uqcxik63ezyr12qn7qcfw8dm08src";

    @GET("games/top")
    Call<TwitchGames> getTopGames(@Header("Client-Id") String clientId);

    @GET("games/top")
    Observable<TwitchGames> getTopGamesObservable(@Header("Client-Id") String clientId);

    @GET("games")
    Observable<TwitchGames> getGame(@Query("id") String id, @Header("Client-Id") String clientId);

    @GET("streams")
    Observable<TwitchStreams> getStreamsObservable(@Header("Client-Id") String clientId);

}
