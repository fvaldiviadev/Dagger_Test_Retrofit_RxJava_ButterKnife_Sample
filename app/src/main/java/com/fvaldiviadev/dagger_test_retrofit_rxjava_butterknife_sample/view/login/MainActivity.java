package com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.view.login;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.R;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.di.App;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    EditText editTextUser,editTextPwd;
    Button buttonLogin;

    @Inject
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((App) getApplication()).getComponent().inject(this);

        editTextUser=findViewById(R.id.et_user);
        editTextPwd=findViewById(R.id.et_pwd);
        buttonLogin=findViewById(R.id.b_login);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Login",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
