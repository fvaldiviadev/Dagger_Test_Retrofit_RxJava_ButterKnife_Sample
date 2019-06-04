package com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.View;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.R;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.di.App;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http.TwitchAPI;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http.api.Game;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.http.api.Twitch;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.LoginContract;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    EditText editTextFirstName,editTextLastName;
    Button buttonLogin, buttonGetTopGames;
    TextView textViewGetTopGames;

    @Inject
    Context context;

    @Inject
    LoginContract.Presenter presenter;

    @Inject
    TwitchAPI twitchAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getComponent().inject(this);

        editTextFirstName=findViewById(R.id.et_user);
        editTextLastName=findViewById(R.id.et_last_name);
        buttonLogin=findViewById(R.id.b_login);
        buttonGetTopGames=findViewById(R.id.b_get_top_games);
        textViewGetTopGames=findViewById(R.id.tv_get_top_games);

        //Without Lambda
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginButtonClicked();
            }
        });

        //With Lambda
        buttonGetTopGames.setOnClickListener(v -> presenter.getTopGames(twitchAPI));

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
        Toast.makeText(context,"Error, user not available",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showInputError() {
        Toast.makeText(context,"Error, input error",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserSaved() {
        Toast.makeText(context,"User saved!",Toast.LENGTH_SHORT).show();
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
        String topGamesString="";
        for (Game game:topGames
             ) {
            topGamesString=topGamesString+"\n"+game.getName();
        }
        textViewGetTopGames.setText(topGamesString);
    }
}
