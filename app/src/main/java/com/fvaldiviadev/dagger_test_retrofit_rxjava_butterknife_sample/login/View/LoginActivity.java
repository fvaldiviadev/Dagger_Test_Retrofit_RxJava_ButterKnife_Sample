package com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.View;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.Action;
import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.ViewCollections;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.R;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.di.App;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http.TwitchAPI;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http.api.Game;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.LoginContract;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.StreamMostViewed;

import java.util.List;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    //Dagger

    @Inject
    Context context;

    @Inject
    LoginContract.Presenter presenter;

    @Inject
    TwitchAPI twitchAPI;


    //Butterknife

    @BindView(R.id.et_user)
    EditText editTextFirstName;

    @BindView(R.id.et_last_name)
    EditText editTextLastName;

    @BindViews({R.id.et_user, R.id.et_last_name, R.id.b_login})
    List<View> loginViews;

    @BindView(R.id.b_login)
    Button buttonLogin;

    @BindView(R.id.b_get_top_games)
    Button buttonGetTopGames;

    @BindView(R.id.b_get_most_viewed_streams)
    Button buttonMostViewedStreams;

    @BindView(R.id.tv_results)
    TextView textViewResults;

    @BindString((R.string.first_name))
    String firstName;

    @BindDrawable(R.drawable.ic_launcher_background)
    Drawable imageBackground;

    @BindColor((R.color.colorAccent))
    ColorStateList colorAccent;

    @OnClick (R.id.b_login)
    void loginClicked(Button b){
        b.setText("Sent!");
        presenter.loginButtonClicked();
    }


    private static final Action<View> DISABLED = (view, index) -> {
        view.setEnabled(false);
    };

    private static final Action<View> ENABLED = (view, index) -> {
        view.setEnabled(true);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getComponent().inject(this);

        ButterKnife.bind(this);

        ViewCollections.run(loginViews, DISABLED);
        ViewCollections.run(loginViews, ENABLED);

        //With Lambda
        buttonGetTopGames.setOnClickListener(v -> {
            textViewResults.setText("");
            presenter.getTopGames(twitchAPI);
        });

        buttonMostViewedStreams.setOnClickListener(v -> {
            textViewResults.setText("");
            presenter.getStreams(twitchAPI);

        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.getCurrentUser();
    }

    @Override
    public String getFirstName() {
        return this.editTextFirstName.getText().toString();
    }

    @Override
    public String getLastName() {
        return this.editTextLastName.getText().toString();
    }

    @Override
    public void showUserNotAvailable() {
        Toast.makeText(context, getString(R.string.error_user_not_available), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInputError() {
        Toast.makeText(context, getString(R.string.error_input), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserSaved() {
        Toast.makeText(context, getString(R.string.user_saved), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setFirstName(String firstName) {
        this.editTextFirstName.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        this.editTextLastName.setText(lastName);
    }

    @Override
    public void showTopGames(List<Game> topGames) {
        String topGamesString = "";
        for (Game game : topGames
        ) {
            topGamesString = topGamesString + "\n" + game.getName();
        }
        textViewResults.setText(topGamesString);
    }

    @Override
    public void showStreamTitle(String streamTitle) {
        textViewResults.setText(textViewResults.getText() + "\n" + getString(R.string.stream) + " " + streamTitle);
    }

    @Override
    public void showGameName(String gameName) {
        textViewResults.setText(textViewResults.getText() + "\n" + getString(R.string.game) + " " + gameName);
    }

    @Override
    public void showStream(StreamMostViewed streamMostViewed) {
        textViewResults.setText(textViewResults.getText() + "\n\n" + streamMostViewed.getStreamName() + " - " + streamMostViewed.getGameName());
    }

    @Override
    public void showErrorGettingStreams() {
        Toast.makeText(this, getString(R.string.error_connection_error_getting_streams), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorGettingGames() {
        Toast.makeText(this, getString(R.string.error_connection_error_getting_streams), Toast.LENGTH_SHORT).show();
    }


}
