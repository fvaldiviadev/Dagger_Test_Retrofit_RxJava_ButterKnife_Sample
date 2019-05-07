package com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.View;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.R;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.di.App;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.LoginContract;

import javax.inject.Inject;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    EditText editTextFirstName,editTextLastName;
    Button buttonLogin;

    @Inject
    Context context;

    @Inject
    LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getComponent().inject(this);

        editTextFirstName=findViewById(R.id.et_user);
        editTextLastName=findViewById(R.id.et_last_name);
        buttonLogin=findViewById(R.id.b_login);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginButtonClicked();
            }
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
}
