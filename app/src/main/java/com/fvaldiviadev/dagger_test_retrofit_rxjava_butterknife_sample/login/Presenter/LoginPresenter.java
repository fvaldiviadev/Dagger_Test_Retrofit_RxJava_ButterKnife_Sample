package com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.Presenter;

import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.R;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.di.TwitchModule;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http.TwitchAPI;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http.api.Game;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http.api.Stream;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http.api.TwitchGames;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.LoginContract;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.User;

import androidx.annotation.Nullable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class LoginPresenter implements LoginContract.Presenter {

    @Nullable
    private LoginContract.View view;
    private LoginContract.Model model;

    private Disposable subscription;

    public LoginPresenter(LoginContract.Model model) {
        this.model = model;
    }

    @Override
    public void setView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void loginButtonClicked() {
        if (view != null) {
            if (view.getFirstName().trim().equals("") || view.getLastName().trim().equals("")) {
                view.showInputError();
            } else {
                model.createUser(view.getFirstName().trim(), view.getLastName().trim());
                view.showUserSaved();
            }
        }
    }

    @Override
    public void getCurrentUser() {
        User user = model.getUser();
        if (user == null) {
            if (view != null) {
                view.showUserNotAvailable();
            }
        } else {
            if (view != null) {
                view.setFirstName(user.getFirstName());
                view.setLastName(user.getLastName());
            }
        }
    }

    @Override
    public void getTopGames(TwitchAPI twitchAPI) {

        twitchAPI.getTopGamesObservable()
                .flatMap(twitchGames -> Observable.fromIterable(twitchGames.getGameList()))
                .flatMap((Function<Game, Observable<String>>)
                        game -> Observable.just(game.getName()))
                .subscribeOn(Schedulers.io()) //With lambda
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(String gameName) {
                        view.showGameName(gameName);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void getStreams(TwitchAPI twitchAPI) {
        Observable streams = twitchAPI.getStreamsObservable()
                .flatMap(twitchStreams -> Observable.fromIterable(twitchStreams.getStreamList()))
                .filter(Stream -> Stream.getViewerCount() > 10000)
                .replay()
                .autoConnect(2)
                .doOnError(throwable ->
                        view.showErrorGettingStreams());

        streams.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(stream -> {
                    view.showStreamTitle(((Stream) stream).getTitle());
                });

        streams.flatMap(stream -> twitchAPI.getGame(((Stream) stream).getGameId()))
                .doOnError(throwable ->
                        view.showErrorGettingGames())
                .flatMap(twitchGames -> Observable.fromIterable(((TwitchGames) twitchGames).getGameList()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(game -> view.showGameName(((Game) game).getName()));


    }

//    public void getStreamsWithZip(TwitchAPI twitchAPI){
//        Observable streams= Observable.zip(twitchAPI.getStreamsObservable(TwitchAPI.CLIENT_ID),twitchAPI.getGame())
//    }
}
