package com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login;

import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http.TwitchAPI;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http.api.Game;

import java.util.List;

public interface LoginContract {

    interface Model{
        void createUser(String firstName,String lastName);
        User getUser();
    }

    interface View{
        String getFirstName();
        String getLastName();

        void showUserNotAvailable();
        void showInputError();
        void showUserSaved();

        void setFirstName(String user);
        void setLastName(String lastName);

        void showTopGames(List<Game> topGames);
        void showStreamTitle(String streamTitle);
        void showGameName(String gameName);
        void showStream(StreamMostViewed streamMostViewed);
        void showError(String error);
    }

    interface Presenter{
        void setView(LoginContract.View view);
        void loginButtonClicked();
        void getCurrentUser();
        void getTopGames(TwitchAPI twitchAPI);
        void getStreams(TwitchAPI twitchAPI);
    }
}
