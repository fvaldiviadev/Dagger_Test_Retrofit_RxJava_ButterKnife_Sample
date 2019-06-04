package com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.Presenter;

import android.support.annotation.Nullable;

import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http.TwitchAPI;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http.api.Game;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http.api.Twitch;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.LoginContract;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.User;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {

    @Nullable
    private LoginContract.View view;
    private LoginContract.Model model;

    public LoginPresenter(LoginContract.Model model) {
        this.model = model;
    }

    @Override
    public void setView(LoginContract.View view) {
        this.view=view;

    }

    @Override
    public void loginButtonClicked() {
        if(view!=null){
            if(view.getFirstName().trim().equals("") || view.getLastName().trim().equals("")){
                view.showInputError();
            }else{
                model.createUser(view.getFirstName().trim(),view.getLastName().trim());
                view.showUserSaved();
            }
        }
    }

    @Override
    public void getCurrentUser() {
        User user=model.getUser();
        if(user==null){
            if(view!=null){
                view.showUserNotAvailable();
            }
        }else{
            if(view!=null){
                view.setFirstName(user.getFirstName());
                view.setLastName(user.getLastName());
            }
        }
    }

    @Override
    public void getTopGames(TwitchAPI twitchAPI) {
        Call<Twitch> call=twitchAPI.getTopGames(TwitchAPI.CLIENT_ID);
        call.enqueue(new Callback<Twitch>() {
            @Override
            public void onResponse(Call<Twitch> call, Response<Twitch> response) {
                List<Game> topGames=response.body().getGame();
                view.showTopGames(topGames);
            }

            @Override
            public void onFailure(Call<Twitch> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
