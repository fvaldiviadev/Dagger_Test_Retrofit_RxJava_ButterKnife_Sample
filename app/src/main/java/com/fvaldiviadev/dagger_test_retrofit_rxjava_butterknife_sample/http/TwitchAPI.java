package com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http;


import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http.api.Twitch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface TwitchAPI {

    String CLIENT_ID="2uqcxik63ezyr12qn7qcfw8dm08src";

    @GET("games/top")
    Call<Twitch> getTopGames(@Header("Client-Id") String clientId);


}
