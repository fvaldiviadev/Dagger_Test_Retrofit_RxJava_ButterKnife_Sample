package com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login;

public interface LoginRepository {
    void saveUser(User user);
    User getUser();
}
