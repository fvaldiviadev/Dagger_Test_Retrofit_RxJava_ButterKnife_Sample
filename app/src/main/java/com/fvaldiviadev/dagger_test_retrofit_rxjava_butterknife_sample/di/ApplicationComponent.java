package com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.di;

import com.fvaldiviadev.dagger_test_retrofit_rxjava_butterknife_sample.login.View.LoginActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules= {ApplicationModule.class, LoginModule.class})
public interface ApplicationComponent {

    void inject(LoginActivity target);
}
