package com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.Model;

import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.LoginContract;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.LoginRepository;
import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.User;

public class LoginModel implements LoginContract.Model{

    private LoginRepository repository;

    public LoginModel(LoginRepository repository){
        this.repository = repository;
    }

    @Override
    public void createUser(String firstName, String lastName) {
        repository.saveUser(new User(firstName,lastName));
    }

    @Override
    public User getUser() {
        return repository.getUser();
    }
}
