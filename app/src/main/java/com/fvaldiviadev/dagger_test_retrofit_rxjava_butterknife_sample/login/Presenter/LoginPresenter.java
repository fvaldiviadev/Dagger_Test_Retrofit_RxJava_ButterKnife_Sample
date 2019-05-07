package com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.Presenter;

import android.support.annotation.Nullable;

import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.LoginContract;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.User;

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
}
